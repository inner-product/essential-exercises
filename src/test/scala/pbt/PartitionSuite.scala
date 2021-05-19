package pbt

import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class PartitionSuite extends AnyFunSuite with ScalaCheckDrivenPropertyChecks {
  // Generative tests succeed after 100 successful evaluations
  minSuccessful(100)

  test("partition is within range") {
    forAll(Arbitrary.arbInt.arbitrary, Gen.choose(1, 100)) { (id, partitions) =>
      val partition = Partition.partition(id, partitions)

      assert(partition >= 0)
      assert(partition < partitions)
    }
  }

  test("absolute value is non-negative") {
    forAll { (x: Int) =>
      assert(Partition.abs(x) >= 0)
    }
  }
}
