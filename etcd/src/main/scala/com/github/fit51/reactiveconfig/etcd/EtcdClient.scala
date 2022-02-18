package com.github.fit51.reactiveconfig.etcd

import cats.effect.{Async, ContextShift, Resource}
import com.github.fit51.reactiveconfig.etcd.gen.kv.KeyValue
import com.github.fit51.reactiveconfig.etcd.gen.rpc._
import com.typesafe.scalalogging.LazyLogging
import io.grpc.stub.StreamObserver
import monix.eval.TaskLift
import monix.execution.Scheduler
import monix.reactive.observers.Subscriber

import java.time.Clock
import java.util.concurrent.TimeUnit
import javax.net.ssl.TrustManagerFactory
import scala.concurrent.duration._

object EtcdClient {
  def apply[F[_]: Async: ContextShift](
      endpoints: String,
      credential: Credentials,
      authority: String,
      trustManagerFactory: TrustManagerFactory,
      options: ChannelOptions = ChannelOptions()
  )(implicit scheduler: Scheduler, clock: Clock): Resource[F, EtcdClient[F]] =
    Resource.make(
      Async[F].delay(
        new EtcdClient(
          ChannelManager(endpoints, credential, options, Some(authority), Some(trustManagerFactory))
        )
      )
    ) { etcdClient =>
      Async[F].delay(etcdClient.close())
    }

  def withWatch[F[_]: Async: ContextShift: TaskLift](
      channelManager: ChannelManager,
      watchErrorRetryPolicy: RetryPolicy
  )(implicit scheduler: Scheduler) =
    Resource.make(Async[F].delay(new EtcdClient(channelManager) with Watch[F] {
      override val taskLift: TaskLift[F]                              = TaskLift[F]
      override def monixToGrpc[T]: Subscriber[T] => StreamObserver[T] = GrpcMonix.monixToGrpcObserverBuffered
      override val errorRetryPolicy: RetryPolicy                      = watchErrorRetryPolicy
    })) { etcdClient =>
      Async[F].delay(etcdClient.close())
    }
}

class EtcdClient[F[_]: Async: ContextShift](val manager: ChannelManager)(implicit val scheduler: Scheduler)
    extends LazyLogging {
  import EtcdUtils._

  private lazy val kvg = KVGrpc.stub(manager.channel)

  def delete(key: String): F[DeleteRangeResponse] =
    kvg.deleteRange(DeleteRangeRequest(key.bytes)).liftToF

  def deleteRecursive(key: String): F[DeleteRangeResponse] = {
    val range = getRange(key)
    kvg.deleteRange(DeleteRangeRequest(range.start.bytes, range.end.bytes)).liftToF
  }

  def put(key: String, value: String): F[PutResponse] =
    kvg.put(PutRequest(key.bytes, value.bytes)).liftToF

  def get(key: String): F[Option[KeyValue]] =
    kvg.range(RangeRequest(key.bytes)).map(resp => resp.kvs.headOption).liftToF

  /** @return Updates since revision
    */
  def getRecursiveSinceRevision(key: String, lastRevision: Long = 0, limit: Long = 0): F[(Seq[KeyValue], Long)] =
    if (lastRevision == 0)
      getRecursive(key, limit)
    else {
      val range = getRange(key)
      kvg
        .range(RangeRequest(range.start.bytes, range.end.bytes, limit, minCreateRevision = lastRevision + 1))
        .flatMap { resp =>
          val newRevision = resp.getHeader.revision
          val createdKVS  = resp.kvs
          kvg
            .range(
              RangeRequest(
                range.start.bytes,
                range.end.bytes,
                limit,
                minModRevision = lastRevision + 1,
                maxModRevision = newRevision
              )
            )
            .map { resp =>
              val modKVS = resp.kvs
              (createdKVS ++ modKVS distinct, newRevision)
            }
        }
        .liftToF
    }

  def getRecursive(key: String, limit: Long = 0): F[(Seq[KeyValue], Long)] = {
    val range = getRange(key)
    kvg
      .range(RangeRequest(range.start.bytes, range.end.bytes, limit))
      .map { resp =>
        (resp.kvs, resp.getHeader.revision)
      }
      .liftToF
  }

  def compact(revision: Long): F[CompactionResponse] =
    kvg.compact(CompactionRequest(revision)).liftToF

  def close(timeout: FiniteDuration = 5.seconds): Unit = {
    manager.channel.shutdown()
    manager.channel.awaitTermination(timeout.toMillis, TimeUnit.MILLISECONDS)
  }
}
