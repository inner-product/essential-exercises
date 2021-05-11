package felines

import cats.implicits._

object Lists {
  // If all the options contain values return a Some containing a list of values.
  // Otherwise return None.
  def listOfOptionToOptionOfList(l: List[Option[Int]]): Option[List[Int]] =
    l.sequence

  // Sum all the values within the options, returning a Some containing the sum
  // or None if all the options are None.
  def addOptionList(l: List[Option[Int]]): Option[Int] =
    l.combineAll

  // Given a list of A, and function from A => Option[B], if all the results of
  // calling the function are Some return a Some containing a list of values.
  // Otherwise return None.
  def listErrorHandling[A, B](l: List[A], f: A => Option[B]): Option[List[B]] =
    l.traverse(f)

  // This line uses Cats implicits, and hence prevents the compiler complaining
  // about an unused import before the methods above have been implemented.
  1 |+| 1
}
