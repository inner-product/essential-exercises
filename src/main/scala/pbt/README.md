# Property-based Testing

## Mission 1

See if you can come up with properties for the following functions:

- List length (`length` and `size` in the standard library)
- List append (`++` in the standard library)
- Sorting a list (`sorted` in the standard library)
- Monoid combine (`combine` from Cats)


## Mission 2

Take a (quick) look at `MapReduce.scala`. It contains a parallel implementation of MapReduce, which should be somewhat familiar. What properties should hold for a parallel MapReduce, compared to a sequential version?

The implementation contains bugs. Implement some property-based tests to try to pin point those bugs.

## Mission 3

Take a quick look at `Expression.scala`. This code should also be mostly familiar. New is a method to parse a `String` into an `Expression`. Use this to create properties for `print` and hence test `print`. Now come up with some properties to test `eval`.
