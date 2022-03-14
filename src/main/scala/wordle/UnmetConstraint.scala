package wordle

sealed abstract class UnmetConstraint extends Product with Serializable

/** This character has already been excluded from the word we're trying to guess */
final case class Excluded(char: Char) extends UnmetConstraint

/** This character has been excluded from this position in the word we're trying to guess */
final case class ExcludedFromPosition(
    suggested: Char,
    position: Int
) extends UnmetConstraint

/** This character has been excluded by an exact match as this position */
final case class ExcludedByExactMatch(
    suggested: Char,
    actual: Char,
    position: Int
) extends UnmetConstraint
