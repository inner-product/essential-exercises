# Property-based Testing

## Mission 0

See if you can come up with properties for the following functions:

- List length (`length` and `size` in the standard library)
- List append (`++` in the standard library)
- Sorting a list (`sorted` in the standard library)
- Monoid combine (`combine` from Cats)


## Mission 1

### Finger Exercisess

These exercises are all about learning to use the API effectively.

- Generate a `String` of lowercase alphabetic characters
  `Gen.alphaLower`
- Generate a `String` of numeric characters
  `Gen.numStr`
- Generate integers between 100 and 200, inclusive
  `Gen.choose(100, 200)`
- Generate strings that start with one of "a", "b", or "c"
  `Gen.alphaStr.filter(str => str.head == "a" || str.head == "b" || str.head == "c")`
  Gen.oneOf("a", "b", "c") // Gen[String]
  Gen.alphaNumStr // Gen[String]
  (prefix, str) => prefix ++ str // (String, String) => String
  
  (Gen[String] Gen[String]).mapN((String, String) => String) == Gen[String]
  F[A] F[A] (A, A) => A == F[A]
  F[A] F[B] (A, B) => C == F[C] What is this?
  
   
  Hint: you may need to import org.scalacheck.implicits._ to get the type class instance
  
  for {
    prefix <- Gen.oneOf(...)
    str <- Gen.alphaStr
  } yield prefix ++ str
  
  Gen.oneOf(...).flatMap(prefix => Gen.alphaStr.map(str => prefix ++ str))
  
  Gen.zip(Gen.oneOf(...), Gen.alphaStr).map{ case (prefix, str) => prefix ++ str }
- Generate odd integers
  `Gen.chooseNum(Int.MinValue, Int.MaxValue).filter(_ % 2 == 1)`
  `Gen.chooseNum(Int.MinValue, Int.MaxValue).map(x => x * 2 + 1)`
- Generate a list of between 3 and 5 positive integers
  ```scala
  for {
    n <- Gen.chooseNum(3, 5)
    lst <- Gen.listOfN(n, Gen.posNum)
  } yield lst
  
  Gen.chooseNum(3,5).flatMap(n => Gen.listOfN(n, Gen.posNum))
  ```

### More Complicated Situations

Create a `Gen[User]`, where `User` is

```scala
final case class User(name: String, age: Int, email: String)
```

Make justifiable decisions for the choice of generators for the fields.

genName: Gen[String]
genAge: Gen[Int]
genEmail: Gen[String]

(String, Int, String) => User // This is User.apply I.e. the constructor

Gen[String], Gen[Int], Gen[String]  (String, Int, String) => User == Gen[User]
(F[A], F[B], F[C]) mapN (A, B, C) => D == F[D]

import cats.implicits._
import org.scalacheck.implicits._

for {
  name <- genName
  age <- genAge
  email <- genEmail
} yield User(name, age, email)

genName.flatMap(name => getAge.flatMap(age => getEmail.map(email => User(name, age, email))))

Gen.zip(genName, genAge, getEmail).map{ case (name, age, email) => User(name, age, email) }


## Mission 2

Take a (quick) look at `MapReduce.scala`. It contains a parallel implementation of MapReduce, which should be somewhat familiar. What properties should hold for a parallel MapReduce, compared to a sequential version?

The implementation contains bugs. Implement some property-based tests to try to pin point those bugs.


## Mission 3

Take a quick look at `Expression.scala`. This code should also be mostly familiar. New is a method to parse a `String` into an `Expression`. Use this to create properties for `print` and hence test `print`. Now come up with some properties to test `eval`.
