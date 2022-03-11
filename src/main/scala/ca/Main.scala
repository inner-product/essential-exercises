package ca

import cats.effect.IOApp
import doodle.java2d._
import doodle.syntax.all._

object Main extends IOApp.Simple {
  val ca = ElementaryAutomata.rule30
  val results = ca.iterate(30)

  val picture = results.map(_.render).allAbove
  val run = picture.drawToIO
}
