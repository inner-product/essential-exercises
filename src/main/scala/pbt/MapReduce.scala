package pbt

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

import cats.Monoid
import cats.implicits._

object MapReduce {
  implicit val ec: scala.concurrent.ExecutionContext =
    scala.concurrent.ExecutionContext.global

  val partitionSize = 8

  // Rather buggy parallel map-reduce. Find the bugs!
  def mapReduce[A, B](data: Array[A])(f: A => B)(implicit m: Monoid[B]): B = {
    // If the data is too small just compute the result right now
    if (data.size < partitionSize)
      data.foldLeft(m.empty)((accum, elt) => m.combine(accum, f(elt)))
    else {
      // If the data is large enough, divide the data into partitions
      val nPartitions = data.size / partitionSize
      val futures =
        for (p <- 1.until(nPartitions)) yield {
          val offset = p * partitionSize
          // Compute each partition in parallel
          Future {
            0.until(partitionSize)
              .foldLeft(m.empty)((accum, idx) =>
                m.combine(accum, f(data(offset + idx)))
              )
          }
        }

      // Combine the results into a single result
      val futureResult =
        futures.foldLeft(Future.successful(m.empty)) { (accum, elt) =>
          for {
            total <- accum
            increment <- elt
          } yield m.combine(total, increment)
        }

      Await.result(futureResult, Duration.Inf)
    }
  }
}
