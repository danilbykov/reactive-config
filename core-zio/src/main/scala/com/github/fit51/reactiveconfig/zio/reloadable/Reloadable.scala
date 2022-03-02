package com.github.fit51.reactiveconfig.zio.reloadable

import cats.kernel.Eq
import com.github.fit51.reactiveconfig.reloadable._
import com.github.fit51.reactiveconfig.typeclasses._
import zio._
import com.typesafe.scalalogging.StrictLogging

trait Reloadable[A] extends RawReloadable[UIO, ResourceLike[*[_], *], A] { self =>

  def unsafeGet: A

  val get: UIO[A]

  def forEachF[R](f: A => URIO[R, Unit]): URIO[R, Nothing]

  def distinctByKey[K: Eq](makeKey: A => K): UManaged[Reloadable[A]]

  def map[B](
      f: A => B,
      reloadBehaviour: ReloadBehaviour[UIO, A, B] = ReloadBehaviour.simpleBehaviour[UIO, A, B]
  ): UManaged[Reloadable[B]]

  def mapF[R, E, B](
      f: A => ZIO[R, E, B],
      reloadBehaviour: ReloadBehaviour[ZIO[R, E, *], A, B] = ReloadBehaviour.simpleBehaviour[ZIO[R, E, *], A, B]
  ): ZManaged[R, E, Reloadable[B]]

  def combine[B, C](
      other: Reloadable[B],
      reloadBehaviour: ReloadBehaviour[UIO, (A, B), C] = ReloadBehaviour.simpleBehaviour[UIO, (A, B), C]
  )(f: (A, B) => C): UManaged[Reloadable[C]]

  def combineF[R, E, B, C](
      other: Reloadable[B],
      reloadBehaviour: ReloadBehaviour[ZIO[R, E, *], (A, B), C] =
        ReloadBehaviour.simpleBehaviour[ZIO[R, E, *], (A, B), C]
  )(f: (A, B) => ZIO[R, E, C]): ZManaged[R, E, Reloadable[C]]

  def mapManaged[R, E, B](f: A => ZManaged[R, E, B]): ZManaged[R, E, Reloadable[B]]

  def makeVolatile: Volatile[UIO, A] =
    new Volatile[UIO, A] {
      override def unsafeGet: A =
        self.unsafeGet

      override def get: UIO[A] =
        self.get
    }
}

class ReloadableImpl[A](
    underlying: RawReloadableImpl[UIO, ResourceLike, A]
) extends Reloadable[A] {

  import Reloadable._

  override protected[reactiveconfig] def subscribe[G[_]](subscriber: Subscriber[UIO, A])(implicit
      effect: Effect[G],
      resource: Resource[ResourceLike, G]
  ): ResourceLike[G, Unit] =
    underlying.subscribe[G](subscriber)

  override def unsafeGet: A =
    underlying.unsafeGet

  override val get: UIO[A] =
    underlying.get

  override def forEachF[R](f: A => URIO[R, Unit]): URIO[R, Nothing] =
    RIO.environment[R] >>= { env =>
      new ResourceLikeOps[Any, Nothing, Unit](underlying.forEachF(a => f(a).provide(env))).toManaged.useForever
    }

  override def distinctByKey[K: Eq](makeKey: A => K): UManaged[Reloadable[A]] =
    new ResourceLikeOps[Any, Nothing, RawReloadableImpl[UIO, ResourceLike, A]](
      underlying.distinctByKey[K](makeKey)
    ).toManaged.map(new ReloadableImpl(_))

  override def map[B](f: A => B, reloadBehaviour: ReloadBehaviour[UIO, A, B]): UManaged[Reloadable[B]] =
    new ResourceLikeOps[Any, Nothing, RawReloadableImpl[UIO, ResourceLike, B]](
      underlying.mapF(a => UIO.succeed(f(a)), reloadBehaviour)
    ).toManaged.map(new ReloadableImpl(_))

  override def mapF[R, E, B](
      f: A => ZIO[R, E, B],
      reloadBehaviour: ReloadBehaviour[ZIO[R, E, *], A, B]
  ): ZManaged[R, E, Reloadable[B]] =
    for {
      env <- ZManaged.environment[R]
      result <- new ResourceLikeOps[Any, E, RawReloadableImpl[UIO, ResourceLike, B]](
        underlying.mapF(a => f(a).provide(env), provideEnv(env, reloadBehaviour))
      ).toManaged
    } yield new ReloadableImpl(result)

  override def combine[B, C](
      other: Reloadable[B],
      reloadBehaviour: ReloadBehaviour[UIO, (A, B), C]
  )(f: (A, B) => C): UManaged[Reloadable[C]] =
    new ResourceLikeOps[Any, Nothing, RawReloadableImpl[UIO, ResourceLike, C]](
      underlying.combineF(other, reloadBehaviour)((a, b) => UIO.succeed(f(a, b)))
    ).toManaged.map(new ReloadableImpl(_))

  override def combineF[R, E, B, C](
      other: Reloadable[B],
      reloadBehaviour: ReloadBehaviour[ZIO[R, E, *], (A, B), C]
  )(f: (A, B) => ZIO[R, E, C]): ZManaged[R, E, Reloadable[C]] =
    for {
      env <- ZManaged.environment[R]
      result <- new ResourceLikeOps[Any, E, RawReloadableImpl[UIO, ResourceLike, C]](
        underlying.combineF(other, provideEnv(env, reloadBehaviour))((a, b) => f(a, b).provide(env))
      ).toManaged
    } yield new ReloadableImpl(result)

  // FIXME: need to test
  override def mapManaged[R, E, B](f: A => ZManaged[R, E, B]): ZManaged[R, E, Reloadable[B]] =
    ZManaged(for {
      env <- ZIO.environment[(R, ZManaged.ReleaseMap)]
      result <- mapF[Any, E, (ZManaged.Finalizer, B)](
        a => f(a).zio.provide(env),
        Stop((pair: (ZManaged.Finalizer, B)) => pair._1(Exit.Success(pair._2)).unit)
      ).zio
    } yield result).flatMap(_.map(_._2))
}

object Reloadable extends HugeCombines with StrictLogging {

  def root[A](initial: A): UManaged[(Reloadable[A], A => UIO[Unit])] = {
    val x = RawReloadableImpl[UIO, UIO, ResourceLike, A, Nothing](UIO.succeed(initial), Simple())
    new ResourceLikeOps[Any, Nothing, RawReloadableImpl[UIO, ResourceLike, A]](x).toManaged.map { r =>
      val sub = new MappedSubscriber[UIO, A, A](identity, r.modifyCurrentValue)
      (new ReloadableImpl(r), sub.onNext)
    }
  }

  def const[A](a: A): Reloadable[A] =
    new ConstReloadable(a)

  implicit def resource[R, E]: Resource[ResourceLike, ZIO[R, E, *]] =
    new Resource[ResourceLike, ZIO[R, E, *]] {
      override def pure[A](a: A): ResourceLike[ZIO[R, E, *], A] =
        Allocate(UIO.succeed((a, UIO.unit)))

      override def liftF[A](fa: ZIO[R, E, A]): ResourceLike[ZIO[R, E, *], A] =
        Allocate(fa.map(a => (a, UIO.unit)))

      override def make[A](acquire: ZIO[R, E, A])(release: A => ZIO[R, E, Unit]): ResourceLike[ZIO[R, E, *], A] =
        Allocate(acquire.map(a => (a, release(a))))

      override def map[A, B](ra: ResourceLike[ZIO[R, E, *], A])(f: A => B): ResourceLike[ZIO[R, E, *], B] =
        FlatMap(ra, (a: A) => Allocate(UIO.succeed((f(a), UIO.unit))))

      override def flatMap[A, B](
          ra: ResourceLike[ZIO[R, E, *], A]
      )(f: A => ResourceLike[ZIO[R, E, *], B]): ResourceLike[ZIO[R, E, *], B] =
        FlatMap(ra, f)
    }

  implicit def zioEffect[R, E]: Effect[ZIO[R, E, *]] =
    new Effect[ZIO[R, E, *]] {
      override def pure[A](a: A): ZIO[R, E, A] =
        UIO.succeed(a)

      override def sync[A](thunk: () => A): ZIO[R, E, A] =
        ZIO.effectTotal(thunk())

      override def async[A](cb: (A => Unit) => ZIO[R, E, Unit]): ZIO[R, E, A] =
        ZIO.effectAsyncM { innerCb =>
          cb(a => innerCb(UIO.succeed(a)))
        }

      override def map[A, B](fa: ZIO[R, E, A])(f: A => B): ZIO[R, E, B] =
        fa.map(f)

      override def flatMap[A, B](fa: ZIO[R, E, A])(f: A => ZIO[R, E, B]): ZIO[R, E, B] =
        fa.flatMap(f)

      override def fireAndForget[A](fa: ZIO[R, E, A]): ZIO[R, E, Unit] =
        fa.forkDaemon.unit

      override def parallelTraverse(fas: List[ZIO[R, E, Unit]]): ZIO[R, E, Unit] =
        ZIO.foreachPar_(fas)(identity)

      override def info(message: String): ZIO[R, E, Unit] =
        UIO.effectTotal(logger.info(message))

      override def warn(message: String): ZIO[R, E, Unit] =
        UIO.effectTotal(logger.warn(message))

      override def warn(message: String, e: Throwable): ZIO[R, E, Unit] =
        UIO.effectTotal(logger.warn(message, e))
    }

  implicit class ResourceLikeOps[R, E, A](private val resource: ResourceLike[ZIO[R, E, *], A]) extends AnyVal {
    def toManaged: ZManaged[R, E, A] = {
      def go[B](resource: ResourceLike[ZIO[R, E, *], B]): ZManaged[R, E, B] =
        resource match {
          case Allocate(resource) =>
            // FIXME: uncacellable
            Managed.makeReserve(resource.map { case (b, release) =>
              Reservation(ZIO.succeed(b), _ => release.catchAll(_ => UIO.unit))
            })
          case FlatMap(resource, func) =>
            go(resource).flatMap(a => go(func(a)))
        }

      go(resource)
    }
  }

  implicit def handleTo[E]: HandleTo[IO[E, *], UIO, E] =
    new HandleTo[IO[E, *], UIO, E] {

      override def handleErrorWith[A](fa: IO[E, A])(f: E => UIO[A]): UIO[A] =
        fa.catchAll(f)

      override def mapK[A](ga: UIO[A]): ZIO[Any, E, A] =
        ga
    }

  def provideEnv[R, E, A, B](env: R, behaviour: ReloadBehaviour[ZIO[R, E, *], A, B]): ReloadBehaviour[IO[E, *], A, B] =
    behaviour match {
      case simple: Simple[ZIO[R, E, *], A, B] =>
        simple.asInstanceOf[ReloadBehaviour[IO[E, *], A, B]]
      case Stop(stop) =>
        Stop(b => stop(b).provide(env))
      case Restart(restart, stop) =>
        Restart((a, b) => restart(a, b).provide(env), b => stop(b).provide(env))
    }
}
