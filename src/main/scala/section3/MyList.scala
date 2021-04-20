package essentialscala.section3


// MyList[A] is either
// - Empty OR
// - Pair
//
// Pair is a head of type A and a tail of MyList[A]
sealed trait MyList[A] {
  import MyList._

  def size: Int =
    ???

  def contains(x: A): Boolean =
    ???

  def find(x: A): Option[A] =
    ???

  def map[B](f: A => B): MyList[B] =
    ???

  def append(that: MyList[A]): MyList[A] =
    ???

  def flatMap[B](f: A => MyList[B]) =
    ???

  def foldRight[B](z: B)(f: (B, A) => B): B =
    ???
}
object MyList {
  final case class Empty[A]() extends MyList[A]
  final case class Pair[A](head: A, tail: MyList[A]) extends MyList[A]

  def empty[A]: MyList[A] = ???

  def apply[A](elts: A*): MyList[A] =
    ???
}
