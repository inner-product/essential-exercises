package methods

import doodle.core.Color
import doodle.syntax._
import doodle.image._
import doodle.image.syntax._
import doodle.java2d._
object Pictures extends App {
  // Task 1: Read this method and guess what it does. Discuss with your team.
  // ONLY THEN run the method and check if your guess was correct.
  def mystery(color: Color): Image = {
    val c1 = color
    val c2 = color.lightenBy(0.2.normalized)
    val c3 = c2.lightenBy(0.2.normalized)

    Image.regularPolygon(3, 200, 0.degrees).strokeColor(c1)
      .beside(Image.regularPolygon(5, 200, 0.degrees).strokeColor(c2))
      .beside(Image.regularPolygon(7, 200, 0.degrees).strokeColor(c3))
      .strokeWidth(7.0)
  }

  mystery(Color.crimson).draw()

}
