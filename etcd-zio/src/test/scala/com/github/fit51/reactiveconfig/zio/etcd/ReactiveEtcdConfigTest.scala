package com.github.fit51.reactiveconfig.zio.etcd

import cats.data.NonEmptySet
import org.scalatest.{Matchers, WordSpecLike}
import scalapb.zio_grpc.ZManagedChannel
import com.github.fit51.reactiveconfig.etcd.gen.rpc.ZioRpc._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import com.github.fit51.reactiveconfig.parser.ConfigParser
import scala.util.Success
import zio._
import zio.Exit.Failure
import org.scalatest.OptionValues._
import com.github.fit51.reactiveconfig.etcd.config.Utils
import com.github.fit51.reactiveconfig.etcd.ChannelManager
import com.github.fit51.reactiveconfig.etcd.ChannelOptions
import zio.duration.{Duration => ZDuration}
import zio.clock.Clock

class ReactiveEtcdConfigTest extends WordSpecLike with Matchers {

  val intersectPrefixes1 = NonEmptySet.of("", "any.other")
  val intersectPrefixes2 = NonEmptySet.of("some.one", "some")

  val okPrefixes1 = NonEmptySet.of("some", "other")
  val okPrefixes2 = NonEmptySet.of("some.one", "some.two")

  val channel = ZManagedChannel(
    ChannelManager
      .noAuth(
        endpoints = "127.0.0.1:2379",
        options = ChannelOptions(20 seconds),
        authority = None,
        trustManagerFactory = None
      ).channelBuilder
  )

  val layers = Clock.live ++ KVClient.live(channel) ++ WatchClient.live(channel) // ) >+> EtcdClient.live

  implicit val decoder: ConfigParser[String] =
    str => Success(str)

  "ReactiveEtcdConfig" should {

    "check prefixes" in {
      Utils.doIntersect(intersectPrefixes1) shouldBe true
      Utils.doIntersect(intersectPrefixes2) shouldBe true
      Utils.doIntersect(okPrefixes1) shouldBe false
      Utils.doIntersect(okPrefixes2) shouldBe false
    }
  }
}
