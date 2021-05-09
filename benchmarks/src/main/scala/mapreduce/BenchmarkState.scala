package mapreduce

import org.openjdk.jmh.annotations._

object BenchmarkState {
  @State(Scope.Benchmark)
  class BenchmarkState {
    val small  = Array.fill(1000)("a")
    val medium = Array.fill(100000)("a")
    val large  = Array.fill(1000000)("a")
    // A deterministic functions that consumes a lot of CPU
    val eatCpuMap: String => String = str => Md5.md5(str)
    val eatCpuCombine: (String, String) => String = (str1, str2) => {
      val d = Md5.digest
      d.update(str1.getBytes())
      d.update(str2.getBytes())
      d.digest().map("%02x".format(_)).mkString
    }
  }
}
