package wordle

sealed abstract class Result extends Product with Serializable

/** All the allowed guesses have been used without successfully finishing the game */
final case class NoMoreGuesses(answer: String, guesses: List[String])
    extends Result

/** The guess wasn't correct. Try again, using the updated game */
final case class TryAgain(guess: String, score: Score, game: Game)
    extends Result

/** The guess was correct and the game is won. */
final case class Correct(answer: String, guesses: List[String]) extends Result
