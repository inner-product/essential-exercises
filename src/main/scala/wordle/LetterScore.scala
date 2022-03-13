package wordle

sealed abstract class LetterScore extends Product with Serializable {
  def isExactlyCorrect: Boolean =
    this match {
      case ExactlyCorrect => true
      case PartlyCorrect  => false
      case Incorrect      => false
    }
}

/** Letter is in the word and in the correct location */
case object ExactlyCorrect extends LetterScore

/** Letter is in the word but not in this location */
case object PartlyCorrect extends LetterScore

/** Letter is not in the word */
case object Incorrect extends LetterScore
