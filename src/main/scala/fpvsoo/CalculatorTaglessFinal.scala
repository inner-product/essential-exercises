package fpvsoo

// Mission One: Implement a calculator in tagless final style.
//
// We need a single generic type that will represent both the expression type
// and the output type---we're making them the same. This loses us some
// flexibility in implementation (we cannot algebraically manipulate expressions
// to simplify them, for example) but we avoid allocating an intermediate data
// structure.
//
// Concretely, this calculator should be a trait with a single type parameter
// and abstract methods that are similar to the methods you implemented for the
// OO calculator.
trait CalculatorTaglessFinal[A] {
  def literal(v: Double): A
  def add(l: A, r: A): A
  def multiply(l: A, r: A): A
}

// Mission Two: implement some concrete instances of your calculator where the
// generic type is Double and String
trait DoubleCalculatorTaglessFinal extends CalculatorTaglessFinal[Double]
// object DoubleCalculatorTaglessFinal extends DoubleCalculatorTaglessFinal

trait PrettyPrintCalculatorTaglessFinal extends CalculatorTaglessFinal[String]
// object PrettyPrintCalculatorTaglessFinal extends PrettyPrintCalculatorTaglessFinal

// Mission Three: show that we can extend the combinators by implementing a
// calculator trait that adds sin and cos to the calculator
trait TrigCalculatorTaglessFinal[A] extends CalculatorTaglessFinal[A]

// Mission Four: implement an interpreter for the trig calculator
trait DoubleTrigCalculatorTaglessFinal extends TrigCalculatorTaglessFinal[Double]
// object DoubleTrigCalculatorTaglessFinal extends DoubleTrigCalculatorTaglessFinal
