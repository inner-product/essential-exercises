package implicits

object ParametersAndValues {
  trait Adder {
    def add(x: Int, y: Int): Int
  }

  // Complete the definition of this method. It should have an implicit
  // parameter that is an `Adder` (see above) and use that to combine the two
  // values passed to it.
  def combine(x: Int, y: Int): Int =
    ???

  // Make this call work by defining an implicit value of type `Adder`. It
  // *must* be an implicit value. Do not pass a value explicitly.
  println(combine(1, 2))

  // Create another implicit value of type `Adder`. Does your code continue to
  // work? If so, why? If not, why not?
}
