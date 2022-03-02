package com.github.fit51.reactiveconfig.ce.etcd

import com.dimafeng.testcontainers.{ForAllTestContainer, GenericContainer}
import com.github.fit51.reactiveconfig.etcd.ChannelOptions
import com.github.fit51.reactiveconfig.etcd.ChannelManager
import org.scalatest.{Matchers, WordSpecLike}
import org.testcontainers.containers.wait.strategy.Wait
import com.github.fit51.reactiveconfig.etcd._

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import cats.effect.IO
import cats.effect.ContextShift
import scala.concurrent.ExecutionContext

class EtcdClientTest extends WordSpecLike with Matchers with ForAllTestContainer {

  implicit val ioContextShift: ContextShift[IO] =
    new ContextShift[IO] {

      override def shift: IO[Unit] =
        IO.unit

      override def evalOn[A](ec: ExecutionContext)(fa: IO[A]): IO[A] =
        fa
    }

  override val container: GenericContainer = GenericContainer(
    "bitnami/etcd:latest",
    exposedPorts = List(2379),
    env = Map("ALLOW_NONE_AUTHENTICATION" -> "yes"),
    waitStrategy = Wait.forHttp("/health")
  )

  val channel = ChannelManager
    .noAuth(
      endpoints = "127.0.0.1:2379",
      options = ChannelOptions(20 seconds),
      authority = None,
      trustManagerFactory = None
    ).channelBuilder

  "An EtcdClient" should {

    "put and get" in {
      val etcdClient = EtcdClient[IO](channel.build())
      val task = for {
        _  <- etcdClient.put("key", "value")
        kv <- etcdClient.get("key")
      } yield kv.get.value.utf8 shouldBe "value"

      task.unsafeRunSync()
    }

    /*
    val keyRange = "some.key.prefix".asKeyRange

    val updates = List(
      ("some.key.prefix.key1", "v1"),
      ("some.key.prefix.key2", "v1"),
      ("some.key.prefix.key1", "v2"),
      ("some.key.prefix.key2", "v2")
    )

    "watch" in {
      val task = (for {
        etcdClient <- ZIO.service[EtcdClient]
        watch <- ZIO.service[WatchClient.ZService[Any, Any]]

        fiber <- watch
          .watch(
            ZStream(
              WatchRequest(
                WatchRequest.RequestUnion.CreateRequest(
                  WatchCreateRequest(
                    key = keyRange.start.bytes,
                    rangeEnd = keyRange.end.bytes
                  )
                )
              )
            )
          )
          .filterNot(r => r.created || r.canceled)
          .mapConcatChunk { r => Chunk.fromIterable(r.events.flatMap(_.kv)) }
          .map(kv => kv.key.utf8 -> kv.value.utf8)
          .take(4)
          .runCollect
          .fork
        _ <- ZIO.foreach(updates) { case (key, value) => etcdClient.put(key, value) }
        changes <- fiber.join
      } yield {
        changes.toList should contain theSameElementsAs updates
      }).provideLayer(layer)

      runtime.unsafeRunSync(task)
    }
     */
  }
}
