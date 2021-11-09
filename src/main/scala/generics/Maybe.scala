package generics

// In this exercise we'll implement the equivalent of the built-in Option.
//
// Maybe is Just or Empty
// Just is a value of type A
// What strategy / concept is Maybe an example of?
sealed trait Maybe[+A]
{
  def contains[AA >: A](x: AA): Boolean =
    ???

  def getOrElse[AA >: A](default: AA): A =
    ???

  def map[B](f: A => B): Maybe[B] =
    ???

  def flatMap[B](f: A => Maybe[B]) =
    ???

  def foldRight[B](z: B)(f: (B, A) => B): B =
    ???
}
object Maybe {
  final case class Just[A](value: A) extends Maybe[A]
  final case object Empty extends Maybe[Nothing]
  //final case class Empty[A]() extends Maybe[A]

  def empty[A]: Maybe[A] = Empty

  def apply[A](x: A): Maybe[A] = Just(x)
}
