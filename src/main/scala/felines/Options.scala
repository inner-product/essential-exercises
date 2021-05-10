package felines

import cats._
import cats.implicits._

object Options {
  // If both options contain a value, return an option containing the sum of both values
  // If only one option has a value return just that option
  // If neither option has a value return None
  //
  // Examples:
  //   addIntOption(Some(1), Some(3)) == Some(4)
  //   addIntOption(Some(1), None)    == Some(1)
  def addIntOption(a: Option[Int], b: Option[Int]): Option[Int] =
    ???
}
