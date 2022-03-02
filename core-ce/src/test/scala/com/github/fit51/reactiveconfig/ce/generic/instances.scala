package com.github.fit51.reactiveconfig.ce.generic

import cats.effect.{ContextShift, IO}
import com.github.fit51.reactiveconfig.Sensitive
import com.github.fit51.reactiveconfig.parser.ConfigDecoder

import scala.util.{Success, Try}
import scala.concurrent.ExecutionContext

object Decoders {

  implicit val intDecoder: ConfigDecoder[Int, String] =
    s => Try(s.toInt)

  implicit val booleanDecoder: ConfigDecoder[Boolean, String] =
    s => Try(s.toBoolean)

  implicit val doubleDecoder: ConfigDecoder[Double, String] =
    s => Try(s.toDouble)

  val sensitiveDecoder: ConfigDecoder[Sensitive, String] =
    s => Success(Sensitive(s * 2))

  implicit val plainDecoder: ConfigDecoder[Plain, String] =
    s => {
      val Array(rawInt, rawBoolean, rawDouble) = s.split(":")
      Success(Plain(rawInt.toInt, rawBoolean.toBoolean, rawDouble.toDouble))
    }
}

object instances {

  implicit val ioContextShift: ContextShift[IO] =
    new ContextShift[IO] {

      override def shift: IO[Unit] =
        IO.unit

      override def evalOn[A](ec: ExecutionContext)(fa: IO[A]): IO[A] =
        fa
    }
}
