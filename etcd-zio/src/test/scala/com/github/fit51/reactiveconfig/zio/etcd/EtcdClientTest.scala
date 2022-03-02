package com.github.fit51.reactiveconfig.zio.etcd

import com.dimafeng.testcontainers.{ForAllTestContainer, GenericContainer}
import org.scalatest.{Matchers, WordSpecLike}
import org.testcontainers.containers.wait.strategy.Wait
import zio._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scalapb.zio_grpc.ZManagedChannel
import com.github.fit51.reactiveconfig.etcd.gen.rpc.ZioRpc._
import zio.stream.ZStream
import com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest
import com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCreateRequest
import com.github.fit51.reactiveconfig.etcd._
import com.github.fit51.reactiveconfig.etcd.ChannelManager
import com.github.fit51.reactiveconfig.etcd.ChannelOptions

class EtcdClientTest extends WordSpecLike with Matchers with ForAllTestContainer {

  override val container: GenericContainer = GenericContainer(
    "bitnami/etcd:latest",
    exposedPorts = List(2379),
    env = Map("ALLOW_NONE_AUTHENTICATION" -> "yes"),
    waitStrategy = Wait.forHttp("/health")
  )

  val channel = ZManagedChannel(
    ChannelManager
      .noAuth(
        endpoints = "127.0.0.1:2379",
        options = ChannelOptions(20 seconds),
        authority = None,
        trustManagerFactory = None
      ).channelBuilder
  )

  val layer = (KVClient.live(channel) ++ WatchClient.live(channel)) >+> EtcdClient.live

  val runtime = Runtime.default

  "An EtcdClient" should {

    "put and get" in {
      val task = (for {
        etcdClient <- ZIO.service[EtcdClient]
        _          <- etcdClient.put("key", "value")
        kv         <- etcdClient.get("key")
      } yield kv.get.value.utf8 shouldBe "value").provideLayer(layer)

      runtime.unsafeRunSync(task)
    }

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
        watch      <- ZIO.service[WatchClient.ZService[Any, Any]]

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
          .mapConcatChunk(r => Chunk.fromIterable(r.events.flatMap(_.kv)))
          .map(kv => kv.key.utf8 -> kv.value.utf8)
          .take(4)
          .runCollect
          .fork
        _       <- ZIO.foreach(updates) { case (key, value) => etcdClient.put(key, value) }
        changes <- fiber.join
      } yield changes.toList should contain theSameElementsAs updates).provideLayer(layer)

      runtime.unsafeRunSync(task)
    }
  }
}
