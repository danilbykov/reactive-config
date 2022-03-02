package com.github.fit51.reactiveconfig.zio.typesafe

import java.nio.file.Path
import zio._
import com.github.fit51.reactiveconfig.zio.reloadable.Reloadable._
import com.github.fit51.reactiveconfig.typesafe.TypesafeUtils
import com.github.fit51.reactiveconfig.parser.ConfigParser
import zio.nio.file.{Path => ZPath, WatchService}
import java.nio.file.StandardWatchEventKinds
import com.github.fit51.reactiveconfig.zio.config.AbstractReactiveConfig
import com.github.fit51.reactiveconfig.Value
import com.github.fit51.reactiveconfig.config.ConfigState

class TypesafeReactiveConfig[D](stateRef: RefM[ConfigState[UIO, D]]) extends AbstractReactiveConfig[D](stateRef)

object TypesafeReactiveConfig {

  def live[D: Tag](path: Path)(implicit encoder: ConfigParser[D]) = (for {
    watchService <- WatchService.forDefaultFileSystem
    zpath = ZPath(path.getParent().toUri())
    _        <- zpath.register(watchService, List(StandardWatchEventKinds.ENTRY_MODIFY)).toManaged(_.cancel)
    stateRef <- parseConfig(path, -1).toManaged_.map(ConfigState[UIO, D](_, Map.empty)) >>= ZRefM.makeManaged
    _ <- watchService.stream
      .mapChunks(_ => Chunk.unit)
      .zipWithIndex
      .mapM { case (_, idx) => parseConfig(path, idx) }
      .foreach { newMap =>
        stateRef.update { state =>
          state.fireUpdates(newMap).as(state.copy(values = newMap))
        }
      }
      .fork
      .toManaged_
  } yield new TypesafeReactiveConfig(stateRef)).toLayer

  private def parseConfig[D: ConfigParser](path: Path, index: Long): Task[Map[String, Value[D]]] =
    Task.effect(TypesafeUtils.parseConfig(path)).flatMap(TypesafeUtils.parseValuesInMap[UIO, D](_, index))
}
