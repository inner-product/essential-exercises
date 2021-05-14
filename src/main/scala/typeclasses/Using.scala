package typeclasses

import cats._

object Using {
  // Complete the definition of this method. It should combine the given values
  // with the given Semigroup instance.
  def combine[A](a1: A, a2: A)(implicit m: Semigroup[A]): A =
    ???

  // Complete the definition of this method. It should combine all the values in
  // the list with a Monoid instance for A. (Why a Monoid instance and not a
  // Semigroup instance?)
  def combineAll[A](l: List[A]): A =
    ???

  // Complete the definition of this method. It should combine all the values in
  // the list with a Semigroup instance for A and return them wrapped in Some.
  // If there are no values in the list it should return None.
  def combineAllOpt[A](l: List[A]): Option[A] =
    ???

  // Complete the definition of this method. It should combine all the values in
  // the container A, which should have a Foldable instance, with a Monoid
  // instance for A.
  def combineAllF[F[_], A](fa: F[A]): A =
    ???

  // If you used a implicit parameter in combineAll, combineAllOpt, and
  // combineAllF above, rewrite them using a context bound. If you used a
  // context bound do the reverse.
}
