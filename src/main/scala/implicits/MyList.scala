package implicits

sealed trait MyList[A] {
  // Implement maxOption
  // See the documentation for the same method on List for details
  // https://www.scala-lang.org/api/current/scala/collection/immutable/List.html
  def maxOption: Option[A] = ???
}
object MyList {
  final case class Pair[A](head: A, tail: MyList[A]) extends MyList[A]
  final case class Empty[A]() extends MyList[A]

  def empty[A]: MyList[A] = Empty()

  def apply[A](elts: A*): MyList[A] =
    elts.foldRight(empty[A])((elt, accum) => Pair(elt, accum))
}

object MyListExample extends App {
  val intList = MyList(1, 2, 3, 4)
  println(s"intList.max is ${intList.maxOption}, should be Some(4)")

  val doubleList = MyList(3.0, 10.0, 2.0, -3.0)
  println(s"doubleList.max is ${doubleList.maxOption}, should be Some(10.0)")
}
