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
    a |+| b

  // If both options have values return a tuple of the values. Otherwise return None.
  def tupleOption(a: Option[Int], b: Option[String]): Option[(Int, String)] =
    (a, b).tupled

  // If both options contain values, append the two lists. Otherwise return None.
  def appendListOption(
      a: Option[List[String]],
      b: Option[List[String]]
  ): Option[List[String]] =
    (a, b).mapN((l1, l2) => l1 ++ l2)

  // If both options contain a value, return an option containing the String
  // "Hi" repeated the number of times equals to the product of both values.
  // Otherwise return None
  //
  // Example:
  //   multiplyIntOption(Some(4), Some(2)) == Some("HiHiHiHiHiHiHiHi")
  //
  // Pro tip: There is a multiply method on String: "Hi" * 2 == "HiHi"
  def multiplyIntOption(a: Option[Int], b: Option[Int]): Option[String] =
    (a, b).mapN((i1, i2) => "Hi" * i1 * i2)

  // If both or either option contains a value return the sum of all the Ints
  // Other return None.
  def sumListOption(a: Option[List[Int]], b: Option[List[Int]]): Option[Int] =
    (a |+| b).map(_.sum)

  // This line uses Cats implicits, and hence prevents the compiler complaining
  // about an unused import before the methods above have been implemented.
  1 |+| 1
}
