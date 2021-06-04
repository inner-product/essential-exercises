package scalatest

import org.scalatest.funsuite.AnyFunSuite

class ExpressionSuite extends AnyFunSuite {

  test("Addition is parsed and printed to same value"){
    Expression.parse("(1.0 + 1.0)") match {
	  case Left(error) => fail(s"Parsing failed with reason $error")
	  case Right(value) =>
        assert(value.print == "(1.0 + 1.0)")
    }
  }
}
