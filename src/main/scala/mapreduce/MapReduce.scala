package mapreduce

// Single-threaded map-reduce
object MapReduce {
  def mapReduce[A, B](data: Array[A])(
      f: A => B
  )(empty: B, combine: (B, B) => B): B =
    // The straight forward solution: apply a map and then a reduce (or fold, as
    // we call it)
    //
    // Benchmarks:
    // MapReduceBenchmark.basicMapReduceBenchmarkMedium  avgt   50  1.545 ±  0.062   s/op
    // data.map(f).foldLeft(empty)(combine)
    //
    // This solution is slightly more performant as we don't create an
    // intermediate data structure. We also don't need the ClassTag any more.
    data.foldLeft(empty)((accum, elt) => combine(accum, f(elt)))

}
