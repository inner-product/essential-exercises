package methods

import cats.effect.IOApp
import doodle.core.Color
import doodle.image._
import doodle.java2d._
import doodle.syntax.all._
object Pictures extends IOApp.Simple {
  // Task 1: Read this method and guess what it does. Discuss with your team.
  // ONLY THEN run the method and check if your guess was correct.
  def mystery(color: Color): Image = {
    val c1 = color
    val c2 = color.lightenBy(0.2.normalized)
    val c3 = c2.lightenBy(0.2.normalized)

    Image
      .regularPolygon(3, 200)
      .strokeColor(c1)
      .beside(Image.regularPolygon(5, 200).strokeColor(c2))
      .beside(Image.regularPolygon(7, 200).strokeColor(c3))
      .strokeWidth(7.0)
  }

  val run = mystery(Color.crimson).compile.drawToIO

}
