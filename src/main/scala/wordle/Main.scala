package wordle

import cats.effect._
import cats.effect.std.Random

object Main extends IOApp.Simple {
  def loop(game: Game): IO[Unit] = {
    IO.println("Guess a word!") *>
      IO.readLine.flatMap { guess =>
        game.guess(guess) match {
          case Correct(answer, guesses) =>
            IO.println(s"Correct! You got $answer in ${guesses.size} guesses.")
          case NoMoreGuesses(answer, _) =>
            IO.println(s"Oh no, you're out of guesses! The answer was $answer")
          case TryAgain(guess, score, game) =>
            IO.println(
              s"You scored ${score.toString} for your guess $guess. Try again!"
            ) *> loop(
              game
            )
        }
      }
  }

  val run =
    for {
      random <- Random.scalaUtilRandom[IO]
      words <- random.shuffleList(WordList.read)
      answer = words.head
      game = Game(answer, List.empty)
      _ <- loop(game)
    } yield ()
}
