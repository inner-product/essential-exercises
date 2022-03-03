package lsystem

import cats.effect.IOApp
import doodle.core._
import doodle.java2d._
import doodle.syntax.all._

object Turtle extends IOApp.Simple {
  // This path defines a square
  val examplePath: List[PathElement] =
    List(
      PathElement.moveTo(0, 0), // It's good practice to start with a move
      PathElement.lineTo(100, 0),
      PathElement.lineTo(100, 100),
      PathElement.lineTo(0, 100),
      PathElement.lineTo(0, 0)
    )

  // Convert a list of path elements into a path that can be rendered, and add
  // some styling.
  def drawPath(path: List[PathElement]): Picture[Unit] =
    OpenPath(path).path
      .strokeColor(Color.deepSkyBlue)
      .strokeWidth(7.0)

  val run = drawPath(examplePath).drawToIO
}
