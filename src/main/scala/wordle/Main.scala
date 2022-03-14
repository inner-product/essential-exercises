package wordle

import cats.effect._
import cats.effect.std.Random

object Main extends IOApp.Simple {
  def formatLetterScore(letterScore: LetterScore): fansi.Str =
    letterScore match {
      case ExactlyCorrect(ch) =>
        fansi.Back.Green(fansi.Color.White(ch.toString))
      case PartlyCorrect(ch) =>
        fansi.Back.Yellow(fansi.Color.White(ch.toString))
      case Incorrect(ch) => fansi.Back.Black(fansi.Color.White(ch.toString))
    }

  def formatScore(score: Score): fansi.Str =
    formatLetterScore(score.letter1) ++
      formatLetterScore(score.letter2) ++
      formatLetterScore(score.letter3) ++
      formatLetterScore(score.letter4) ++
      formatLetterScore(score.letter5)

  def loop(game: Game): IO[Unit] = {
    IO.readLine.flatMap { guess =>
      game.guess(guess) match {
        case Correct(answer, guesses) =>
          IO.println(s"Correct! You got $answer in ${guesses.size} guesses.")
        case NoMoreGuesses(answer, _) =>
          IO.println(s"Oh no, you're out of guesses! The answer was $answer")
        case TryAgain(_, score, game) =>
          IO.println(
            formatScore(score).toString
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
      _ <- IO.println("Guess a word! You have six guesses.")
      _ <- loop(game)
    } yield ()
}
