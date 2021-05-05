package section3

// MyList[A] is either
// - Empty OR
// - Pair
//
// Pair is a head of type A and a tail of MyList[A]
sealed trait MyList[A] {
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

  def flatMap[B](f: A => MyList[B]): MyList[B] =
    ???

  def foldRight[B](z: B)(f: (A, B) => B): B =
    ???
}
object MyList {
  def empty[A]: MyList[A] = ???

  def apply[A](elts: A*): MyList[A] =
    ???
  //elts.foldRight(empty)((elt, myList) => Pair(elt, myList))
}
