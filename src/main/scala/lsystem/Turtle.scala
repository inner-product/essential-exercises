package lsystem

import doodle.core._
import doodle.java2d._
import doodle.syntax._

object Turtle extends App {
  // This path defines a square
  val examplePath: List[PathElement] =
    List(
      PathElement.moveTo(0, 0), // It's good practice to start with a move
      PathElement.lineTo(100, 0),
      PathElement.lineTo(100, 100),
      PathElement.lineTo(0, 100),
      PathElement.lineTo(0, 0)
    )

  // Convert a list of path elements into a path that can be rendered, add some
  // styling, and draw it.
  def drawPath(path: List[PathElement]): Unit =
    OpenPath(path).path.strokeColor(Color.deepSkyBlue).strokeWidth(7.0).draw()

  drawPath(examplePath)
}
