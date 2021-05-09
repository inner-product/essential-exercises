package mapreduce

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

class MapReduceBenchmark {
  import BenchmarkState._

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
  @Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
  def basicMapReduceBenchmarkSmall(state: BenchmarkState): Unit = {
    MapReduce.mapReduce(state.small)(state.eatCpuMap)("", state.eatCpuCombine)
    ()
  }

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
  @Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
  def basicMapReduceBenchmarkMedium(state: BenchmarkState): Unit = {
    MapReduce.mapReduce(state.medium)(state.eatCpuMap)("", state.eatCpuCombine)
    ()
  }

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
  @Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
  def basicMapReduceBenchmarkLarge(state: BenchmarkState): Unit = {
    MapReduce.mapReduce(state.large)(state.eatCpuMap)("", state.eatCpuCombine)
    ()
  }
}
