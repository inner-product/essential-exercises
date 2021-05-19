package pbt

import cats.parse._

sealed trait Expression {
  import Expression._

  def +(that: Expression): Expression =
    Addition(this, that)

  def -(that: Expression): Expression =
    Subtraction(this, that)

  def *(that: Expression): Expression =
    Multiplication(this, that)

  def /(that: Expression): Expression =
    Division(this, that)

  def eval: Double =
    this match {
      case Literal(value)              => value
      case Addition(left, right)       => left.eval + right.eval
      case Subtraction(left, right)    => left.eval - right.eval
      case Multiplication(left, right) => left.eval * right.eval
      case Division(left, right)       => left.eval * right.eval
    }

  def print: String =
    this match {
      case Literal(value)              => value.toString
      case Addition(left, right)       => s"(${left.print} + ${right.print})"
      case Subtraction(left, right)    => s"(${left.print} * ${right.print})"
      case Multiplication(left, right) => s"(${left.print} * ${right.print})"
      case Division(left, right)       => s"(${left.print} / ${right.print})"
    }
}
object Expression {
  final case class Literal(value: Double) extends Expression
  final case class Addition(left: Expression, right: Expression)
      extends Expression
  final case class Subtraction(left: Expression, right: Expression)
      extends Expression
  final case class Multiplication(left: Expression, right: Expression)
      extends Expression
  final case class Division(left: Expression, right: Expression)
      extends Expression

  val parser: Parser[Expression] = Parser.recursive[Expression] { recurse =>
    val whitespace0: Parser0[Unit] = Parser.charIn(" \t\r\n").void.rep0.void
    val literal = Numbers.jsonNumber.map(str => Literal(str.toDouble))

    val operator = Parser.charIn('+', '-', '*', '/').surroundedBy(whitespace0)

    val openParen = Parser.char('(').surroundedBy(whitespace0)
    val closeParen = Parser.char(')').surroundedBy(whitespace0)

    val binaryOperation =
      (recurse ~ operator ~ recurse).between(openParen, closeParen).map {
        case ((e1, op), e2) =>
          op match {
            case '+' => Addition(e1, e2)
            case '-' => Subtraction(e1, e2)
            case '*' => Multiplication(e1, e2)
            case '/' => Division(e1, e2)
          }
      }

    Parser.oneOf(
      binaryOperation :: literal :: Nil
    )
  }

  /** Read a string into an Expression, or fail with an Error */
  def parse(string: String): Either[Parser.Error, Expression] =
    parser.parseAll(string)
}
