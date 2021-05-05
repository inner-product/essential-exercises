package mapreduce

import java.util.concurrent.TimeUnit

import scala.concurrent._
import scala.concurrent.duration._

import org.openjdk.jmh.annotations._

class MapReduceFutureBenchmark {
  import BenchmarkState._

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
  @Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
  def basicMapReduceBenchmarkSmall(state: BenchmarkState): Unit = {
    val f = MapReduceFuture.mapReduce(state.small)(state.eatCpu)(0, _ + _)
    Await.result(f, Duration.Inf)
    ()
  }

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
  @Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
  def basicMapReduceBenchmarkMedium(state: BenchmarkState): Unit = {
    val f = MapReduceFuture.mapReduce(state.medium)(state.eatCpu)(0, _ + _)
    Await.result(f, Duration.Inf)
    ()
  }
}
