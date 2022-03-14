package wordle

/** Represents a game of Wordle */
final case class Game(answer: String, guesses: List[String]) {
  def guess(word: String): Result =
    if (guesses.size >= 6) NoMoreGuesses(answer, guesses)
    else if (word == answer) Correct(answer, word :: guesses)
    else if (guesses.size == 5) NoMoreGuesses(answer, word :: guesses)
    else
      TryAgain(
        word,
        Score(answer = answer, guess = word),
        this.copy(guesses = word :: guesses)
      )
}
