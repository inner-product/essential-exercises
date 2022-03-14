package wordle

final case class Score(
    letter0: LetterScore,
    letter1: LetterScore,
    letter2: LetterScore,
    letter3: LetterScore,
    letter4: LetterScore
) {

  /** Get the word represented by this score */
  def guess: String =
    letter0.char.toString :+ letter1.char :+ letter2.char :+ letter3.char :+ letter4.char

  /** True if all the letter were guessed exactly correctly. In other words if the
    * word was correctly guessed and the game is won.
    */
  def isExactlyCorrect: Boolean =
    letter0.isExactlyCorrect &&
      letter1.isExactlyCorrect &&
      letter2.isExactlyCorrect &&
      letter3.isExactlyCorrect &&
      letter4.isExactlyCorrect
}
object Score {
  def apply(answer: String, guess: String): Score = {
    assert(
      answer.size == 5 && guess.size == 5,
      s"""
       |Cannot score the guess against the given answer.
       |
       |Guess must be a word with exactly five characters. Was given \"${guess}\", which has ${guess.size} characters.
       |Answer must be a word with exactly five characters. Was given \"${answer}\", which has ${answer.size} characters.
       """.stripMargin
    )

    val score: Array[LetterScore] =
      Array.tabulate(5)(idx => Incorrect(guess(idx)))
    // True if the letter at the given index in the answer has been matched
    // against a letter in the guess, and therefore cannot be used to match
    // against a different letter in the guess.
    val answerMatched: Array[Boolean] = Array.fill(5)(false)
    // True if the letter at the given index in the guess has been matched
    // against a letter in the answer.
    val guessMatched: Array[Boolean] = Array.fill(5)(false)

    // Utility that will find partly correct characters and update all our
    // state.
    def findPartlyCorrectChar(
        char: Char,
        guessIdx: Int,
        answerIdx: Int
    ): Unit = {
      if (answerIdx >= answerMatched.size) ()
      else if (!answerMatched(answerIdx) && answer(answerIdx) == char) {
        guessMatched(guessIdx) = true
        answerMatched(answerIdx) = true
        score(guessIdx) = PartlyCorrect(char)
      } else findPartlyCorrectChar(char, guessIdx, answerIdx + 1)
    }

    // Look for exact matches and also record letters that are matched, to allow
    // searching for partly correct letters next
    answer.zip(guess).zipWithIndex.foreach { case ((a, g), idx) =>
      if (a == g) {
        score(idx) = ExactlyCorrect(g)
        answerMatched(idx) = true
        guessMatched(idx) = true
      }
    }
    guess.zipWithIndex.foreach { case (g, idx) =>
      if (!guessMatched(idx)) findPartlyCorrectChar(g, idx, 0)
    }

    Score(score(0), score(1), score(2), score(3), score(4))
  }
}
