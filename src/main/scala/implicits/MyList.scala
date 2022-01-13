package implicits

sealed trait MyList[A] {
  // Implement max
  // See the documentation for the same method on List for details
  // https://www.scala-lang.org/api/current/scala/collection/immutable/List.html
  def max: A = ???
}
object MyList {
  final case class Pair[A](head: A, tail: MyList[A]) extends MyList[A]
  final case class Empty[A]() extends MyList[A]

  def empty[A]: MyList[A] = Empty()

  def apply[A](elts: A*): MyList[A] =
    elts.foldRight(empty[A])((elt, accum) => Pair(elt, accum))
}

object MyListExample extends App {
  val list = MyList(1, 2, 3, 4)

  println(s"list.max is ${list.max}, should be 4")
}
