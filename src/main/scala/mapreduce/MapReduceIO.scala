package mapreduce

import cats.effect.IO

object MapReduceIO {
  def mapReduce[A, B](data: Array[A])(
      f: A => B
  )(empty: B, combine: (B, B) => B): IO[B] =
    ???

  def parallelMap[A, B](data: Array[A])(f: A => B): IO[Array[B]] =
    ???

  def parallelReduce[A](data: Array[A])(empty: A, combine: (A, A) => A): IO[A] =
    ???
}
