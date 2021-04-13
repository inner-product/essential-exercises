package essentialscala.section1

object Types {
  // What is the type of the expressions a, b, and c below? What value do they evaluate to?
  //
  // For each:
  // - Work out your answer before running any code
  // - Write code to validate your answer
  //
  // Be ready to explain your answer, how you could reason to a solution, and
  // how you could verify your solution with code.


  val a = 41 + 1

  val b = 3 / 2

  // This is necessary to stop the compiler complaining about 5 / 0.
  val zero = 0
  val c = 5 / zero
}
