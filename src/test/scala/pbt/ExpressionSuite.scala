package pbt

import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class ExpressionSuite extends AnyFunSuite with ScalaCheckDrivenPropertyChecks {
  // Generative tests succeed after 100 successful evaluations
  minSuccessful(100)

  test("Literals are parsed") {
    forAll { (x: Double) =>
      val result = Expression.parse(x.toString)
      result match {
        case Left(value) => fail(s"Parsing failed with reason $value")
        case Right(value) =>
          assertResult(Expression.Literal(x))(value)
      }
    }
  }

  test("Addition is parsed correctly") {
    forAll { (l: Double, r: Double) =>
      val string = s"(${l.toDouble} + ${r.toDouble})"
      val result = Expression.parse(string)
      result match {
        case Left(value) => fail(s"Parsing failed with reason $value")
        case Right(value) =>
          assertResult(Expression.Literal(l) + Expression.Literal(r))(value)
      }
    }
  }

  test("Subtraction is parsed correctly") {
    forAll { (l: Double, r: Double) =>
      val string = s"(${l.toDouble} - ${r.toDouble})"
      val result = Expression.parse(string)
      result match {
        case Left(value) => fail(s"Parsing failed with reason $value")
        case Right(value) =>
          assertResult(Expression.Literal(l) - Expression.Literal(r))(value)
      }
    }
  }

  test("Multiplication is parsed correctly") {
    forAll { (l: Double, r: Double) =>
      val string = s"(${l.toDouble} * ${r.toDouble})"
      val result = Expression.parse(string)
      result match {
        case Left(value) => fail(s"Parsing failed with reason $value")
        case Right(value) =>
          assertResult(Expression.Literal(l) * Expression.Literal(r))(value)
      }
    }
  }

  test("Division is parsed correctly") {
    forAll { (l: Double, r: Double) =>
      val string = s"(${l.toDouble} / ${r.toDouble})"
      val result = Expression.parse(string)
      result match {
        case Left(value) => fail(s"Parsing failed with reason $value")
        case Right(value) =>
          assertResult(Expression.Literal(l) / Expression.Literal(r))(value)
      }
    }
  }
}
