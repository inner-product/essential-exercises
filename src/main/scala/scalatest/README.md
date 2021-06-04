# ScalaTest

In these exercises we'll get used to using ScalaTest.


## ScalaTest Model

In ScalaTest, tests consist of

- test cases, which test a specific condition; and
- test suites, which aggregate test cases into meaningful groups.

A test suite corresponds to a file. A test case is a declaration within that file.

When using sbt test suites are located in directories under `<project-root>/src/test/scala/`


## Creating Test Suites

ScalaTest provides many different ways to write test suites. They all achieve the same goal so the choice between them in arbitrary. We're going to use `FunSuite`.

We declare a test suite as follows:

``` scala
import org.scalatest.funsuite.AnyFunSuite

class ExampleSuite extends AnyFunSuite {
  test("An example test") {
    assert(1 + 1 == 2)
  }
}
```


## Running Tests

Use the `test` command from sbt, or run them from your IDE.


## Mission One: Partition

We're going to test the code in `Partition.scala`. 

- Start by creating a test suite (`PartitionSuite`)
- Add a test that, when calling `partition`, if the `id` is non-negative and less than the `numberOfPartitions`, the result is the same as `id`.
- Add other tests that you think make sense. You should have at least four tests.

Note: there is a bug in this code, but it is very obscure. You'll be doing well if you find it!


## Mission Two: Expression

In this exercise we're going to test some more complicated code. In `Expressions.scala` there is code to that represents arithmetic expressions as a data structure. There are methods to

- parse an arithmetic expression (e.g. `"(1 + 1)"`) from a `String` into an `Expression`; and
- evaluate an `Expression` to a `Double` (the method `eval`) and print an `Expression` to a `String` (the method `print`).

Here are some examples:

``` scala
val either = Expression.parse("(1 + 2)")

eithe match {
  case Left(err) => 
    // We couldn't parse the expression
    println(s"Parsing failed with reason $err")
  case Right(expr) =>  
    // Demonstrate printing the evaluating an `Expression`
    println(s"The value of ${expr.print} in ${expr.eval}")
}
```

There in an example test in `ExpressionSuite.scala` that should get you started.

Think of some other tests and write them in ScalaTest. You'll probably find some things inconvenient to write. ScalaTest comes with lots of utilities. The ones that are likely to be the most useful to you are:

- [`EitherValues`]( https://www.scalatest.org/scaladoc/3.2.9/org/scalatest/EitherValues.html);
- [Matchers](https://www.scalatest.org/user_guide/using_matchers)
