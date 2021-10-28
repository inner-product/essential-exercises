package section2

// This exercise continues on the theme of algebraic data types and structural
// recursion, adding in generic types (aka type variables, parametric
// polymorphism).
//
// In this exercise we're going to implement a simple library for validating
// data. The problem we're solving is this: we have some data that we want to
// check adheres to some rules. If it does, great, the data is fine (though we
// should strongly consider using a different type to represent valid data. See
// [1]). If not we want to get a list of issues represented in some applicatin
// specific way.
//
// This exercise involves some design work, so the specification below has
// several gaps.
//
// [1]: https://lexi-lambda.github.io/blog/2019/11/05/parse-don-t-validate/

// Mission 1: The Essence of Validation Rules.
//
// The simplest unit of validation is a predicate: a function from some input to
// a boolean.
//
// What is the type of a predicate in Scala?
//
// We want to generate some useful output if the predicate fails. We can
// represent this as a function from the input to a message. It's easiest, for
// now, to use a concrete type for the message. A `String` is sufficient.
//
// What is this type in Scala?
//
// Taken together these two components are sufficient to define a validation
// rule (let's call this a Rule for short):
//
// A Rule is
// - a predicate; and
// - an error reporting function.
//
// Write this in Scala code.

// Mission 2: Composing rules
//
// Think about how we could compose validation rules. What ways are there that
// you can think of?
//
// Pro tip 1: a Rule is something like a logical predicate. What ways are there
// to compose logical predicates?
//
// Pro tip 2: a Rule is something like a (very simple) finite state machine.
// What ways are there to compose finite state machines?
//
// Write down the method signatures of the composition rules you have come up
// with, but at this stage we do not want to implement them. Leave the
// implementation as `???`.

// Mission 3: Applying rules
//
// We are missing a method to apply a rule to some data. This method should take
// the data as input and return either an error or the unchanged data. Call this
// method `apply`. Write down it's signature and leave it unimplemented (with
// `???` as the body).

// Mission 4: Reification
//
// We're now going to use One Weird Trick, known as reification, to finish the
// implementation. Most of the method implementations are going to just return a
// data structure, and `apply` is going to interpret that data structure to
// actually apply the predicate.
//
// For example, suppose we had a method called `product` on `Rule`
//
// ```scala
// def product(that: Rule[A]): Rule[A]
// ```
//
// We can create a data structure (which we'll just give the same name as the
// method, for simplicity) and return an instance of that. This data structure
// takes *all* the arguments to the method *including the hidden this argument*.
//
// First we create a data structure
//
// ```scala
// final case class Product[A](this: Rule[A], that: Rule[A]) extends Rule[A]
// ```
//
// Then the implementation becomes
//
// ```scala
// def product(that: Rule[A,B]): Rule[A,B] =
//   Product(this, that)
// ```
//
// Reify every method except `apply`.

// Mission 5: Applying ourselves
//
// Implement `apply`.
//
// Pro tip: `Rule` is an algebraic data type. In `apply` we transform an
// algebraic data type to a different type. What strategy can we use?

// Mission 6: Extra Awesome
//
// Add an extra awesome. For example:
// - maybe consider normalizing rules (e.g. to conjunctive normal form) to make
// evaluation faster and error reporting clearer.
// - can we make the error type generic, so it's not restricted to `String`?
