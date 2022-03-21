package com.github.fit51.reactiveconfig.etcd

import cats.effect.{Async, ContextShift}
import com.google.protobuf.ByteString
import io.grpc.internal.GrpcUtil
import pdi.jwt.JwtClaim

import scala.collection.JavaConverters._
import scala.concurrent.Future
import scala.concurrent.duration._

case class ChannelOptions(
    //Is disabled by default, if so default os socket timeout would cause channel failure (20 seconds)
    //Min value is 10 seconds
    keepAliveTime: FiniteDuration = GrpcUtil.KEEPALIVE_TIME_NANOS_DISABLED nanos,
    // Min value is 10 seconds
    keepAliveTimeout: FiniteDuration = 20 seconds,
    // ChannelOption.CONNECT_TIMEOUT_MILLIS let's us configure reconnect timeout.
    connectTimeout: FiniteDuration = 30.seconds
)

case class Credentials(user: String, password: String)

case class KeyRange(start: String, end: String)

object EtcdUtils {
  import scala.language.implicitConversions

  final class FutureLiftOps[F[_]: Async: ContextShift, A](f: Future[A]) {
    def liftToF: F[A] = Async.fromFuture(Async[F].pure(f))
  }
  implicit def futureSyntaxLift[F[_]: Async: ContextShift, A](fa: Future[A]) = new FutureLiftOps[F, A](fa)

  implicit class BytesToString(s: String) {
    def bytes: ByteString = ByteString.copyFromUtf8(s)
  }
  implicit class StringToBytes(b: ByteString) {
    def utf8: String = b.toStringUtf8
  }

  def getRange(key: String): KeyRange =
    if (key.isEmpty)
      KeyRange(nullByte, maxByte)
    else
      KeyRange(key, key + maxByte)

  val nullByte = "\u0000"
  val maxByte  = "\uFFFF"
}

class EtcdException(m: String) extends Exception(m)

sealed trait Token {
  def value: String
}
final case class SimpleToken(override val value: String)               extends Token
final case class JwtToken(override val value: String, claim: JwtClaim) extends Token
