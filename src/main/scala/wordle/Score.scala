package wordle

final case class Score(
    letter1: LetterScore,
    letter2: LetterScore,
    letter3: LetterScore,
    letter4: LetterScore,
    letter5: LetterScore
) {

  /** True if all the letter were guessed exactly correctly. In other words if the word was correctly guessed and the game is won. */
  def isExactlyCorrect: Boolean =
    letter1.isExactlyCorrect && letter2.isExactlyCorrect && letter3.isExactlyCorrect && letter4.isExactlyCorrect && letter5.isExactlyCorrect
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

    val score: Array[LetterScore] = Array.fill(5)(Incorrect)
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
        score(guessIdx) = PartlyCorrect
      } else findPartlyCorrectChar(char, guessIdx, answerIdx + 1)
    }

    // Look for exact matches and also record letters that are matched, to allow
    // searching for partly correct letters next
    answer.zip(guess).zipWithIndex.foreach { case ((a, g), idx) =>
      if (a == g) {
        score(idx) = ExactlyCorrect
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
