package io

import cats.effect._

// The goal of these exercises is to introduce some of the basics of using IO:
//
// - constructing IO
// - doing one thing after another (sequencing)
// - combining multiple independent IOs
//
// and additionally get to the fundamental design principle of IO: the
// separation between descrptions and action
object Basics extends IOApp.Simple {
  // 0. This object extends IOApp.Simple. This allows us to run the object. It
  // expects an IO with the name `run` which describes the program it will run.
  // So replace `run` below with whatever you want to see your programs in
  // action.
  val run = IO(println("Hello!"))

  // 1. You can create an IO using IO(...) and IO.pure(...). (The former is the
  // apply method on the IO companion object, so can also be written as
  // IO.apply(...))
  //
  // What is the difference between these methods? Can you write a program that
  // demonstrates the difference?

  // 2. flatMap allows us to do one thing after another, where the thing we do
  // second depends on the result of the thing we do first.
  //
  // IO.realTime gives the time since the start of the epoch. Using flatMap
  // write a gets the time and prints it on the console.

  // 3. mapN allows us to combine two or more IOs that don't depend on each
  // other. Here's an example:
  //
  // (IO(1), IO(2)).mapN((x, y) => x + y)
  //
  // When this program is run, are the IOs always evaluated in a particular
  // order (e.g. left to right) or is it non-deterministic?

  // 4. The following program attempts to add logging before and after an IO
  // runs. Does it do this correctly?
  def log[A](io: IO[A]): Unit = {
    println("Starting the IO")
    val _ = io
    println("Ending the IO")
  }

  // 5. Write a method that adds logging before and after an IO

  // 6. What do the *> and <* methods do? Could you use them in the logging
  // method you just wrote?
}
