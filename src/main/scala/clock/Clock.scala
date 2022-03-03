package clock

// You will probably need this import
// import scala.concurrent.duration._

import java.time._

import cats.effect.{IO, IOApp}
import doodle.core._
import doodle.java2d._
import doodle.syntax.all._

object Clock extends IOApp.Simple {
  // This describes the background on which we'll render the clock
  val frame: Frame = Frame.size(800, 300).background(Color.black).title("Clock")
  // A Canvas is an area of the screen we can draw onto
  val canvas: IO[Canvas] = frame.canvas()

  /** Convert an Instant to a LocalTime */
  def instantToLocalTime(instant: Instant): LocalTime =
    instant.atZone(ZoneOffset.UTC).toLocalTime()

  def clock(canvas: Canvas): IO[Nothing] =
    // This code should repeat forever:
    //
    // 1. Sleep for one second
    // 2. Get the current time (an Instant)
    // 3. Convert the current time to a LocalTime
    // 4. Draw the clock given the LocalTime
    //
    // Note: You should use methods on IO, and methods defined here, to accomplish
    // this. You should not directly access any Java APIs.
    ???

  /** Given the current time and a canvas, draw a representation of the current time on the canvas */
  def draw(time: LocalTime, canvas: Canvas): IO[Unit] = {
    val seconds = time.getSecond()
    val minutes = time.getMinute()
    val hours = time.getHour()

    drawTimeUnit(hours, 24)
      .beside(drawTimeUnit(minutes, 60))
      .beside(drawTimeUnit(seconds, 60))
      .drawWithCanvasToIO(canvas)
  }

  /** Convert a hex string, such as "669933", to a Color */
  def hex(string: String): Color =
    Color.rgb(
      Integer.parseInt(string.substring(0, 2), 16),
      Integer.parseInt(string.substring(2, 4), 16),
      Integer.parseInt(string.substring(4, 6), 16)
    )

  // Palette from coolors.co
  val palette = Array(
    hex("f72585"),
    hex("b5179e"),
    hex("7209b7"),
    hex("560bad"),
    hex("480ca8"),
    hex("3a0ca3"),
    hex("3f37c9"),
    hex("4361ee"),
    hex("4895ef"),
    hex("4cc9f0"),
    hex("4895ef"),
    hex("4361ee"),
    hex("3f37c9"),
    hex("3a0ca3"),
    hex("480ca8"),
    hex("560bad"),
    hex("7209b7"),
    hex("b5179e"),
    hex("f72585")
  )

  def drawTimeUnit(current: Int, max: Int): Picture[Unit] = {
    val percentage = current.toDouble / max.toDouble
    val color = palette((percentage * palette.size).floor.toInt)
    circle(current.toDouble * 200 / max.toDouble)
      .strokeColor(color)
      .strokeWidth(13.0)
  }

  val run = canvas.flatMap(c => clock(c))
}
