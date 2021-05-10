package felines

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

  // If both options have values return a tuple of the values. Otherwise return None.
  def tupleOption(a: Option[Int], b: Option[String]): Option[(Int, String)] =
    ???

  // If both options contain values, append the two lists. Otherwise return None.
  def appendListOption(
      a: Option[List[String]],
      b: Option[List[String]]
  ): Option[List[String]] =
    ???

  // If both options contain a value, return an option containing the product of both values
  // If only one option has a value return just that option
  // If neither option has a value return None
  def multiplyIntOption(a: Option[Int], b: Option[Int]): Option[Int] =
    ???

  // This line uses Cats implicits, and hence prevents the compiler complaining
  // about an unused import before the methods above have been implemented.
  1 |+| 1
}
