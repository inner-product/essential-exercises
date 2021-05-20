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
- Generate a `String` of numeric characters
- Generate integers between 100 and 200, inclusive
- Generate strings that start with one of "a", "b", or "c"
- Generate odd integers
- Generate a list of between 3 and 5 positive integers

### More Complicated Situations

Create a `Gen[User]`, where `User` is

```tut:silent:book:
final case class User(name: String, age: Int, email: String)
```

Make justifiable decisions for the choice of generators for the fields.


## Mission 2

Take a (quick) look at `MapReduce.scala`. It contains a parallel implementation of MapReduce, which should be somewhat familiar. What properties should hold for a parallel MapReduce, compared to a sequential version?

The implementation contains bugs. Implement some property-based tests to try to pin point those bugs.


## Mission 3

Take a quick look at `Expression.scala`. This code should also be mostly familiar. New is a method to parse a `String` into an `Expression`. Use this to create properties for `print` and hence test `print`. Now come up with some properties to test `eval`.
