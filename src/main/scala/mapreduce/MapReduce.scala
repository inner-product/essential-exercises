package mapreduce

import scala.reflect.ClassTag

// Single-threaded map-reduce
object MapReduce {
  def mapReduce[A, B: ClassTag](data: Array[A])(
      f: A => B
  )(empty: B, combine: (B, B) => B): B =
    // The straight forward solution: apply a map and then a reduce (or fold, as
    // we call it)
    //
    // Benchmark                                         Mode  Cnt  Score   Error  Units
    // MapReduceBenchmark.basicMapReduceBenchmarkMedium  avgt   50  1.436 ± 0.009   s/op
    // MapReduceBenchmark.basicMapReduceBenchmarkLarge   avgt   50 14.381 ± 0.073   s/op
    data.map(f).foldLeft(empty)(combine)
    //
    // I would expect his solution to be slightly more performant as we don't
    // create an intermediate data structure, but the benchmarks do not show
    // this.
    //
    // Benchmark                                         Mode  Cnt  Score   Error  Units
    // MapReduceBenchmark.basicMapReduceBenchmarkMedium  avgt   50  1.493 ± 0.035   s/op
    // MapReduceBenchmark.basicMapReduceBenchmarkLarge   avgt   50 14.892 ± 0.192   s/op
    //
    // data.foldLeft(empty)((accum, elt) => combine(accum, f(elt)))


}
