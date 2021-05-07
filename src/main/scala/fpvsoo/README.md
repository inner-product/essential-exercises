# FP vs OO

In this exercise we investigate the relationship between FP and OO code, and learn how we can convert between the two.

## Mission One

Implement the calculator described in `CalculatorFp.scala`

- Is it easy to extend the Expression data type without modifying the existing code?
- Is it easy to make new functions that work on Expression without modifying the existing code?


## Mission Two

Implement the calculator described in `CalculatorOo.scala`

- Is it easy to add new methods without modifying the existing code?
- Is it easy to change the result type without modifying the existing code?


## Mission Three

Implement the calculator in `CalculatorTaglessFinal.scala`

We can understand the two implementations above with the following mental model:

There are three different classes of functions (or methods; same thing):

- constructors (aka introduction forms), which go from some type `A` to some internal representation `Expression`
- combinators, which combine `Expressions` to produce new `Expressions`
- interpreters (aka eliminiation forms), which go from `Expression` to some type `B`

In the OO implementation we made the `Expression` type the same as the output type `B`. This means we lost the ability to interpret into different types, which we could do in the FP implementation. However by avoiding building an internal representation we avoid some memory allocation. We can regain this ability by making this type generic.
