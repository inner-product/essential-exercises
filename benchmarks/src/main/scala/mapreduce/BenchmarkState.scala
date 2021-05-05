package mapreduce

import org.openjdk.jmh.annotations._

object BenchmarkState {
  @State(Scope.Benchmark)
  class BenchmarkState {
    val small = Array.fill(1000)("a")
    val medium = Array.fill(100000)("a")
    // A deterministic function that consumes a lot of CPU
    val eatCpu: String => Int = str => Md5.md5(str).size
  }
}
