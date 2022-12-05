package com.github.fit51.reactiveconfig.zio.typesafe

import org.scalatest.{Matchers, WordSpecLike}
import java.nio.file.Paths
import com.github.fit51.reactiveconfig.parser.ConfigParser
import zio._
import zio.blocking._
import com.github.fit51.reactiveconfig.parser.ConfigDecoder
import io.circe.{Decoder, Json, parser}
import io.circe.Decoder
import zio.nio.file.{Files, Path => ZPath}
import zio.duration._
import zio.clock._

case class App(net: Net)
case class Net(host: String, port: Port)
case class Port(public: Int, `private`: Int)
case class Parameter(value: Int)

class TypesafeReactiveConfigTest extends WordSpecLike with Matchers {

  implicit val configParser: ConfigParser[Json] =
    parser.parse(_).toTry

  implicit def circeDecoderToConfigDecoder[T](implicit decoder: Decoder[T]): ConfigDecoder[T, Json] =
    json => decoder.apply(json.hcursor).toTry

  implicit val portDecoder: Decoder[Port] =
    cursor =>
      for {
        public <- cursor.get[Int]("public")
        prvt   <- cursor.get[Int]("private")
      } yield Port(public, prvt)
  implicit val netDecoder: Decoder[Net] =
    cursor =>
      for {
        host <- cursor.get[String]("host")
        port <- cursor.get[Port]("port")
      } yield Net(host, port)
  implicit val appDecoder: Decoder[App] =
    _.get[Net]("net").map(App)
  implicit val parameterDecoder: Decoder[Parameter] =
    _.get[Int]("value").map(Parameter)

  val r = Runtime.default

  val path        = Paths.get("typesafe/src/test/resources/application.conf")
  val configLayer = Blocking.live >>> TypesafeReactiveConfig.live[Json](path)

  def changeable(v: Int): String =
    s"""changeable {
       |  parameter {
       |    value: $v
       |  }
       |}
    """.stripMargin

  "TypesafeConfig" should {

    "properly fetch values with different paths" in {
      r.unsafeRun((for {
        config     <- ZIO.service[TypesafeReactiveConfig[Json]]
        simplePort <- config.get[Int]("app.net.port.public")
        host       <- config.get[String]("app.net.host")
        port       <- config.get[Port]("app.net.port")
        net        <- config.get[Net]("app.net")
        app        <- config.get[App]("app")
      } yield {
        simplePort shouldBe 8080
        host shouldBe "0.0.0.0"
        port shouldBe Port(8080, 9090)
        net shouldBe Net("0.0.0.0", Port(8080, 9090))
        app shouldBe App(Net("0.0.0.0", Port(8080, 9090)))
      }).provideLayer(configLayer))
    }

    "fetch values from included config" in {
      r.unsafeRun((for {
        config      <- ZIO.service[TypesafeReactiveConfig[Json]]
        parallelism <- config.get[Int]("akka.kafka.producer.parallelism")
        hostname    <- config.get[String]("akka.remote.netty.tcp.hostname")
      } yield {
        parallelism shouldBe 10
        hostname shouldBe "0.0.0.0"
      }).provideLayer(configLayer))
    }

    "config should be able to reload case class on change" in {
      val task = ZManaged
        .service[TypesafeReactiveConfig[Json]]
        .flatMap(_.reloadable[Parameter]("changeable.parameter"))
        .use { reloadable =>
          for {
            first <- reloadable.get
            _ <- Files.writeBytes(
              ZPath.fromJava(path.getParent().resolve("changeable.conf")),
              Chunk.fromArray(changeable(2).getBytes())
            )
            _      <- ZIO.sleep(10 seconds)
            second <- reloadable.get
          } yield {
            first shouldBe Parameter(1)
            second shouldBe Parameter(2)
          }
        }
        .onExit { _ =>
          Files
            .writeBytes(
              ZPath.fromJava(path.getParent().resolve("changeable.conf")),
              Chunk.fromArray(changeable(1).getBytes())
            ).orDie
        }
        .provideLayer(Blocking.live ++ Clock.live ++ configLayer)

      r.unsafeRun(task)
    }
  }
}
