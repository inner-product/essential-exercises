package essentialscala.section3

// In this exercise we'll implement the equivalent of the built-in Option.
//
sealed trait Maybe[A] {
  def contains(x: A): Boolean =
    ???

  def getOrElse(default: A): A =
    ???

  def map[B](f: A => B): Maybe[B] =
    ???

  def flatMap[B](f: A => Maybe[B]) =
    ???

  def foldRight[B](z: B)(f: (B, A) => B): B =
    ???
}
object Maybe {
  def empty[A]: Maybe[A] = ???

  def apply[A](x: A): Maybe[A] =
    ???
}
