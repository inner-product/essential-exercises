package pbt

import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class MapReduceSuite extends AnyFunSuite with ScalaCheckDrivenPropertyChecks {
  // Generative tests succeed after 100 successful evaluations
  minSuccessful(100)
}
