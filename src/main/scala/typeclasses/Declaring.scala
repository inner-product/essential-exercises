package typeclasses

object Declaring {
  // A Configuration value of type A is
  // - A Value containing a value of type A OR
  // - Default
  //
  // This is an example of what pattern?
  // Implement it in Scala.

  // Configuration can have some fun methods. Implement the below:
  // - map
  // - flatMap

  // Implement a Monoid instance for Configuration. If two Configurations are a
  // Value the right one should be chosen (so that later configuration overrides
  // earlier configuration). If one is Default and the other a Value the result
  // should be the Value. In other words, Default is the identity for
  // Configuration.

  // Implement a Monad instance for Configuration.

  // Having implemented a Monad instance do you need to implement a Semigroupal
  // instance?

  // Optional: Configuration has the same structure (is isomorphic to) a type in the
  // standard library. What type is that?

}
