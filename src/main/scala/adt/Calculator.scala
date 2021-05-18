package adt

// Your mission is to implement a simple calculator
//
// Your missions:
//
// Mission the First is to implement a data structure to represent arithmetic expressions.
//
// An Expression is:
//
// - A Literal, which has a Double value
// - An Addition, which has a left and right Expression
// - A Division, which has a left and right Expression
// - A Multiplication, which has a left and right Expression
// - A Subtraction, which has a left and right Expression
//
//
// Mission the Second is to implement two "interpreters". The first interpreter
// will take an expression and produce a Double using the usual rules of
// mathematics. The second interpreter will take an expression and produce a
// String representing that expression, with correct parentheses to indicate
// precedence.
//
// Learning points:
// - Appreciate the utility of algebraic data types and structural recursion. Most
// of the code should follow from the programming strategies.
//
// - Understand how representing the expressions as data ("reifying" them)
// decouples the data representation for the functions that act on it, and
// allows us to implement different interpreters.
object Calculator extends App {
  sealed trait Expression
  final case class Literal(value: Double) extends Expression
  final case class Addition(left: Expression, right: Expression)
      extends Expression
  final case class Division(left: Expression, right: Expression)
      extends Expression
  final case class Multiplication(left: Expression, right: Expression)
      extends Expression
  final case class Subtraction(left: Expression, right: Expression)
      extends Expression

  object ArithmeticInterpreter {
    def eval(expr: Expression): Double =
      expr match {
        case Literal(value)              => value
        case Addition(left, right)       => eval(left) + eval(right)
        case Division(left, right)       => eval(left) / eval(right)
        case Multiplication(left, right) => eval(left) * eval(right)
        case Subtraction(left, right)    => eval(left) - eval(right)
      }
  }

  object PrintingInterpreter {
    def eval(expr: Expression): String =
      expr match {
        case Literal(value)              => value.toString
        case Addition(left, right)       => s"(${eval(left)} + ${eval(right)})"
        case Division(left, right)       => s"(${eval(left)} / ${eval(right)})"
        case Multiplication(left, right) => s"(${eval(left)} * ${eval(right)})"
        case Subtraction(left, right)    => s"(${eval(left)} - ${eval(right)})"
      }
  }

  val example =
    Multiplication(Literal(23.42), Addition(Literal(60.0), Literal(7.0)))
  println(ArithmeticInterpreter.eval(example))
  println(PrintingInterpreter.eval(example))
}
