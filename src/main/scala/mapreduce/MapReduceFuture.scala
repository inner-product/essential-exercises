package mapreduce

import scala.concurrent.Future

object MapReduceFuture {
  def mapReduce[A, B](data: Array[A])(
      f: A => B
  )(empty: B, combine: (B, B) => B): Future[B] =
    ???

  def parallelMap[A, B](data: Array[A])(f: A => B): Future[Array[B]] =
    ???

  def parallelReduce[A](
      data: Array[A]
  )(empty: A, combine: (A, A) => A): Future[A] =
    ???
}
