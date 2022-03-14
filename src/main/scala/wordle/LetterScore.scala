package wordle

sealed abstract class LetterScore extends Product with Serializable {
  def char: Char

  def isExactlyCorrect: Boolean =
    this match {
      case _: ExactlyCorrect => true
      case _: PartlyCorrect  => false
      case _: Incorrect      => false
    }
}

/** Letter is in the word and in the correct location */
final case class ExactlyCorrect(char: Char) extends LetterScore

/** Letter is in the word but not in this location */
final case class PartlyCorrect(char: Char) extends LetterScore

/** Letter is not in the word */
final case class Incorrect(char: Char) extends LetterScore
