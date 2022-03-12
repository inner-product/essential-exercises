package io

import cats.effect._
import cats.syntax.all._
import org.openjdk.jmh.infra.Blackhole

// The goal of these exercises is to get comfortable with the basic tools for
// parallelism in IO, such as parMapN.
object Parallelism extends IOApp.Simple {
  // 0. This object extends IOApp.Simple. This allows us to run the object. It
  // expects an IO with the name `run` which describes the program it will run.
  // So replace `run` below with whatever you want to see your programs in
  // action.
  val run = (IO(println("Left!")), IO(println("Right!"))).parTupled.void

  // 1. Blackhole.consumeCPU consumes CPU cycles, and so is a useful way to
  // create tasks that take up enough time we can actually see the benefits of
  // parallelism. Wrap it up in an IO so we can use it in our IO based programs.
  val consume = ???

  // 2. Using the `timed` method, calculate the time it takes to run consume.

  // 3. Now time how long it takes to run consume twice, first sequentially and
  // then in parallel. What, if any, speedup does parallelism get in this case?

  // 4. Time how long it takes to run the list of IOs below, first sequentially
  // and then in parallel. You should be able to solve each task with only two
  // method calls, one of which is `timed`. What, if any, speedup do you see in
  // this case?
  val listOfConsume = List.fill(24)(consume)

  // 5. Convert the list of tokens below into IOs wrapping calls to `consumeCPU`
  // and then time how long they take sequentially and in parallel. As before,
  // you should be able to achieve this in two method calls where one is
  // `timed`.
  val listofTokens =
    List.tabulate(32)(i => Math.pow(2.toDouble, i.toDouble).toLong)
}
