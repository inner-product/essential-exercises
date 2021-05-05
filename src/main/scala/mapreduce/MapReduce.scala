package mapreduce

import scala.reflect.ClassTag

// Single-threaded map-reduce
object MapReduce {
  def mapReduce[A,B: ClassTag](data: Array[A])(f: A => B)(empty: B, combine: (B, B) => B): B =
    ???
}
