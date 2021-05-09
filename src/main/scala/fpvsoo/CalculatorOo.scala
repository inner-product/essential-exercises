package fpvsoo

// Your missions:
//
// Mission One:
//
// Define an interface to represent arithmetric expressions, with the following methods:
//
// - literal, which accepts a Double and returns a Double
// - add, which accepts two Doubles and returns a Double
// - subtract, which accepts two Doubles and returns a Double
// - divide, which accepts two Doubles and returns a Double
// - multiply, which accepts two Doubles and returns a Double
//
//
// Mission Two:
//
// Define an interface that extends the interface you defined above and adds
// trigonometric functions: sin and cos.
object CalculatorOo {
  trait Expression {
    def literal(v: Double): Double = v
    def add(l: Double, r: Double): Double = l + r
    def subtract(l: Double, r: Double): Double = l - r
    def multiply(l: Double, r: Double): Double = l * r
    def divide(l: Double, r: Double): Double = l / r
  }

  trait TrigExpression extends Expression {
    def sin(v: Double): Double = Math.sin(v)
    def cos(v: Double): Double = Math.cos(v)
  }
}
