package wordle

import cats.data.NonEmptyChain
import cats.syntax.all._
import munit.FunSuite

class ConstraintsSuite extends FunSuite {
  test("any word is acceptable to empty constraints") {
    assert(Constraints.empty.isAcceptable("abcde").isRight)
    assert(Constraints.empty.isAcceptable("fancy").isRight)
    assert(Constraints.empty.isAcceptable("saint").isRight)
    assert(Constraints.empty.isAcceptable("adieu").isRight)
    assert(Constraints.empty.isAcceptable("conch").isRight)
  }

  test("constraints rejects guesses that do not meet constraints") {
    val score = Score("eagle", "egret")
    val constraints = Constraints.empty.update(score)
    assert(constraints.isAcceptable("eggos").isLeft)
    assert(constraints.isAcceptable("paint").isLeft)
    assert(constraints.isAcceptable("raven").isLeft)
    assert(constraints.isAcceptable("easel").isLeft)
    assert(constraints.isAcceptable("legal").isLeft)
  }

  test("constraints accepts guesses that do meet constraints") {
    val score = Score("eagle", "egret")
    val constraints = Constraints.empty.update(score)
    assertEquals(
      constraints.isAcceptable("eagel"),
      "eagel".asRight[NonEmptyChain[UnmetConstraint]]
    )
    assertEquals(
      constraints.isAcceptable("evils"),
      "evils".asRight[NonEmptyChain[UnmetConstraint]]
    )
    assertEquals(
      constraints.isAcceptable("elope"),
      "elope".asRight[NonEmptyChain[UnmetConstraint]]
    )
    assertEquals(
      constraints.isAcceptable("epens"),
      "epens".asRight[NonEmptyChain[UnmetConstraint]]
    )
  }
}
