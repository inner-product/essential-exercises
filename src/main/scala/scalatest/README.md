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
    assertEqual(1 + 1, 2)
  }
}
```


## Running Tests

Use the `test` command from sbt, or run them from your IDE.


## Mission One: Partition

We're going to test the code in `Partition.scala`. 

- Start by creating a test suite (`PartitionSuite`)
- Add a test that, when calling `partition`, if the `id` is less than the `numberOfPartition`, the result is the same as `id`.
- Add other tests that you think make sense. You should have at least four tests.

Note: there is a bug in this code, but it is very obscure. You'll be doing well if you find it!


## Mission Two: Expression
