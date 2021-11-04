# Scalding Code Reading Exercise

The goals of this exercise are to:

- practice identifying examples of some of the programming strategies we've learned;
- see some of the details that go into production quality; and
- develop more shared understanding of Scala concepts and techniques.

In the questions below you're asked to identify the motivation of several code snippets taken from the [Scalding](https://twitter.github.io/scalding/) project. You probably won't know the answer to all of them. Don't spend too long try to find an answer if you don't already know as we'll go through everyone as a group. You can also be efficient by dividing the tasks amongst your team.


## SqlType

In [ColumnDefiner] there is the following code.

```scala
sealed trait SqlType
case object BIGINT extends SqlType
case object INT extends SqlType
case object SMALLINT extends SqlType
case object TINYINT extends SqlType
case object BOOLEAN extends SqlType
case object VARCHAR extends SqlType
case object DATE extends SqlType
case object DATETIME extends SqlType
case object TEXT extends SqlType
case object BLOB extends SqlType
case object DOUBLE extends SqlType
```


### Questions

1. What strategy is this an example of?
2. Why do you think this strategy is used? What does this code tell you?
3. Why is a `case object` used in this code?


## IsNullable

In the same file there is this code.

``` scala
sealed abstract class IsNullable(val toStr: String)
case object Nullable extends IsNullable("NULL")
case object NotNullable extends IsNullable("NOT NULL")
```


### Questions

1. What strategy is this an example of?
2. Why do we see a `sealed abstract class` in `IsNullable` but a `sealed trait` in `SqlType`?
3. Why do you think this strategy is used? What does this code tell you?


## SizeHint

An excerpt of the [SizeHint] code is below.

``` scala
object SizeHint {
  // ...
}
sealed abstract class SizeHint {
  def +(other: SizeHint): SizeHint

  // ...
}
case object NoClue extends SizeHint {
  def +(other: SizeHint) = NoClue

  // ...
}
final case class FiniteHint(rows: BigInt = -1L, cols: BigInt = -1L) extends SizeHint {
  def +(other: SizeHint) =
    other match {
      case NoClue => NoClue
      // In this case, a hint on one side, will overwrite lack of knowledge (-1L)
      case FiniteHint(orows, ocols) => FiniteHint(rows.max(orows), cols.max(ocols))
      case sp @ SparseHint(_, _, _) => (sp + this)
    }
    
  // ...
}
final case class SparseHint(sparsity: Double, rows: BigInt, cols: BigInt) extends SizeHint {
  def +(other: SizeHint): SizeHint =
    other match {
      case NoClue           => NoClue
      case FiniteHint(r, c) => (this + SparseHint(1.0, r, c))
      case SparseHint(sp, r, c) => {
        // if I occupy a bin with probability p, and you q, then either: p + q - pq
        if ((sparsity == 1.0) || (sp == 1.0)) {
          FiniteHint(rows.max(r), cols.max(c))
        } else {
          val newSp = sparsity + sp - sp * sparsity
          SparseHint(newSp, rows.max(r), cols.max(c))
        }
      }
    }

  // ...
}
```

1. What strategy is this an example of?
2. What is the relationship between the object `SizeHint` and the `sealed abstract class` `SizeHint`?
3. What are objects like `SizeHint` used for in Scala?
4. How can we have an object and a type with the same name?
5. What strategy is used to implement the `+` method on `FiniteHint` and `SparseHint`?


## Op

The code for [Op] looks like:

``` scala
sealed trait Op[+O] {
  def result(implicit cec: ConcurrentExecutionContext): Future[ArrayBuffer[_ <: O]]

  def map[O1](fn: O => O1): Op[O1] =
    Op.MapOp(this, fn)

  def filter(fn: O => Boolean): Op[O] =
    Op.Filter(this, fn)

  // ...
}
object Op {
  def source[I](i: Iterable[I]): Op[I] = Source(_ => Future.successful(i.iterator))
  def empty[I]: Op[I] = source(Nil)
  
  final case class Source[I](input: ConcurrentExecutionContext => Future[Iterator[I]]) extends Op[I] {
    // ...
  }

  final case class MapOp[I, O](input: Op[I], fn: I => O) extends Op[O] {
    // ...
  }

  final case class Filter[I](input: Op[I], fn: I => Boolean) extends Op[I] {
    // ...
  }

  // ...
}
```

1. What is the role of the methods `source` and `empty` in the object `Op`?
2. Why are `Source`, `Materialize`, and `Concat` defined inside the object `Op`?
3. Why are the methods `map` and `filter` defined the way they are?
4. What is the difference between `result` and, `map` and `filter`?
5. What does `[+O]` declare? What does the `+` in front of `O` mean?


## TypedPipe

In [TypedPipe] there is a lot of code. Too much to excerpt!

1. What is the role of the methods in the object `TypedPipe`?
2. Why are case classes defined inside the object `TypedPipe`?
3. What is the implementation strategy for the method `eqFn` on line 430?
4. Why does `sealed abstract class TypedPipe` (line 549) `extend` `Serializable` and `Product`?
5. What is the implementation strategy of the method `map` (line 734)? Can you find other methods that use this implementation strategy?

[ColumnDefiner]: https://github.com/twitter/scalding/blob/b0ba993ac817e6b1e52126e8b1cfb1054cc00dad/scalding-db/src/main/scala/com/twitter/scalding/db/ColumnDefiner.scala

[SizeHint]: https://github.com/twitter/scalding/blob/b0ba993ac817e6b1e52126e8b1cfb1054cc00dad/scalding-core/src/main/scala/com/twitter/scalding/mathematics/SizeHint.scala

[Op]: https://github.com/twitter/scalding/blob/b0ba993ac817e6b1e52126e8b1cfb1054cc00dad/scalding-core/src/main/scala/com/twitter/scalding/typed/memory_backend/Op.scala

[TypedPipe]: https://github.com/twitter/scalding/blob/b0ba993ac817e6b1e52126e8b1cfb1054cc00dad/scalding-core/src/main/scala/com/twitter/scalding/typed/TypedPipe.scala
