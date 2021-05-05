package mapreduce

import org.openjdk.jmh.annotations._

class MapReduceBenchmark {
  import MapReduceBenchmark._

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @Warmup(iterations = 10)
  @Measurement(iterations = 10)
  def basicMapReduceBenchmarkSmall(state: BenchmarkState): Unit = {
    MapReduce.mapReduce(state.small)(state.eatCpu)(0, _ + _)
    ()
  }

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @Warmup(iterations = 10)
  @Measurement(iterations = 10)
  def basicMapReduceBenchmarkMedium(state: BenchmarkState): Unit = {
    MapReduce.mapReduce(state.medium)(state.eatCpu)(0, _ + _)
    ()
  }
}
object MapReduceBenchmark {
  @State(Scope.Benchmark)
  class BenchmarkState {
    val small = Array.fill(1000)("a")
    val medium = Array.fill(100000)("a")
    // A deterministic function that consumes a lot of CPU
    val eatCpu: String => Int = str => Md5.md5(str).size
  }
}
