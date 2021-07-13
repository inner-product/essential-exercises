package felines

import cats.implicits._

// These are simple exercises to get you used to using cats
object FirstLook {
  // For each of the questions below:
  //
  // 1. Guess at an answer and explain your answer to other people in your
  // group. This will force you to construct a mental model which you can then
  // test.
  //
  // 2. Try the exercise and see if your mental model is correct. If not, update
  // it accordingly.

  // What is the difference between
  Option(1) |+| Option(2)
  // and
  // Some(1) |+| Some(2)
  // ?

  // What is the difference between the *expression*
  1.some
  // and
  Some(1)
  // ?

  // What is the difference between the *expression*
  1.asRight
  // and
  Right(1)
  // ?

  // Is there a difference between
  1.some |+| 2.some
  // and
  (1.some, 2.some).mapN(_ + _)
  // ?

  // What is the difference between
  1.some |+| none[Int]
  // and
  (1.some, none[Int]).mapN(_ + _)
  // ?

  // Can you write
  (1.some, 2.some).mapN(_ + _)
  // using flatMap and map?

  // Can you write
  (List(1, 2, 3), List(4, 5, 6)).mapN(_ + _)
  // using flatMap and map?

  // What is the difference between
  (List(1, 2, 3) |+| List.empty[Int])
  // and
  (List(1, 2, 3), List.empty[Int]).mapN(_ + _)
  // ?
}
