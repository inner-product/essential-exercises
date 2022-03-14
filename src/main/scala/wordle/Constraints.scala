package wordle

import cats.data.NonEmptyChain
import cats.syntax.all._

/** Represents the constraints that are generated when solving a wordle game. There three two types of constraints:
  *
  * - we know a letter at an exact location
  * - we know a letter is in the word and the positions it could potentially be in, but not its exact location
  * - we know a letter is not in the word
  */
final case class Constraints(
    exact: Map[Int, Char],
    potential: Map[Char, Set[Int]],
    excluded: Set[Char]
) {
  def charIsNotExcluded(
      char: Char
  ): Either[NonEmptyChain[UnmetConstraint], Unit] =
    if (excluded.contains(char)) NonEmptyChain.one(Excluded(char)).asLeft
    else ().asRight

  def charIsPotential(
      char: Char,
      position: Int
  ): Either[NonEmptyChain[UnmetConstraint], Unit] =
    potential.get(char) match {
      case None => ().asRight
      case Some(positions) =>
        if (positions.contains(position)) ().asRight
        else NonEmptyChain.one(ExcludedFromPosition(char, position)).asLeft
    }

  def charIsExactIfKnown(
      char: Char,
      position: Int
  ): Either[NonEmptyChain[UnmetConstraint], Unit] =
    exact.get(position) match {
      case None => ().asRight
      case Some(c) =>
        if (char == c) ().asRight
        else NonEmptyChain.one(ExcludedByExactMatch(char, c, position)).asLeft
    }

  def charIsAcceptable(
      char: Char,
      position: Int
  ): Either[NonEmptyChain[UnmetConstraint], Unit] =
    (
      charIsNotExcluded(char),
      charIsPotential(char, position),
      charIsExactIfKnown(char, position)
    ).parTupled.void

  /** True if the word meets the constraints */
  def isAcceptable(
      word: String
  ): Either[NonEmptyChain[UnmetConstraint], String] = {
    assert(
      word.size == 5,
      s"Word must be exactly five characters. Was given ${word} which is ${word.size} characters long."
    )

    word.zipWithIndex.toList
      .traverse { case (ch, idx) => charIsAcceptable(ch, idx) }
      .as(word)
  }

  def update(
      letterScore: LetterScore,
      position: Int,
      // How many times the given Char occurred in the guess
      nOccurrences: Map[Char, Int],
      // The locations in which the given Char occurred in the guess
      locations: Map[Char, Set[Int]]
  ): Constraints =
    letterScore match {
      case ExactlyCorrect(char) => this.copy(exact = exact + (position -> char))
      case Incorrect(char)      =>
        // The char must be in the count of occurrences so we use the unsafe
        // apply method
        val count = nOccurrences(char)

        // If the count > 1 this tells us that the incorrect only means there
        // are no more instances of this character in the word. We'll have
        // another letter score that has more information so we should ignore
        // this one.
        if (count == 1) this.copy(excluded = excluded + char)
        else this

      case PartlyCorrect(char) =>
        // The char must be in the map so we use the unsafe apply method
        val count = nOccurrences(char)
        val charPotential = potential.get(char)
        val charLocations = locations.get(char)

        // This is the only occurrence so we can just use this information directly
        // It can be in any other position that has not been excluded
        if (count == 1)
          this.copy(potential =
            potential + (char -> charPotential
              .map(set => set - position)
              .getOrElse(Set(0, 1, 2, 3, 4) - position))
          )
        // This is not the only occurrence, so it can't be in any of the other
        // locations we've guessed
        else {
          val cannotBe = charLocations.getOrElse(Set.empty) + position

          this.copy(potential =
            potential + (char -> charPotential
              .map(set => set -- cannotBe)
              .getOrElse(Set(0, 1, 2, 3, 4, 5) -- cannotBe))
          )
        }
    }

  def update(score: Score): Constraints = {
    // We cannot process each letter independently as we have to account for
    // multiple letters in a word.
    //
    // For example, if the answer is "frond" and we guess "foody" we must
    // account for the Incorrect letter score of the first 'o' meaning there are
    // not two o's in the word, rather than meaning there are not any o's in the
    // word.
    val guess = score.guess
    val nOccurrences: Map[Char, Int] = guess.foldLeft(Map.empty[Char, Int]) {
      (accum, ch) =>
        accum.get(ch) match {
          case None        => accum + (ch -> 1)
          case Some(count) => accum + (ch -> (count + 1))
        }
    }
    val locations: Map[Char, Set[Int]] =
      guess.zipWithIndex.foldLeft(Map.empty[Char, Set[Int]]) { (accum, elt) =>
        val (ch, idx) = elt
        accum.get(ch) match {
          case None      => accum + (ch -> Set(idx))
          case Some(set) => accum + (ch -> (set + idx))
        }
      }

    List(
      score.letter0,
      score.letter1,
      score.letter2,
      score.letter3,
      score.letter4
    ).zipWithIndex.foldLeft(this) { (accum, elt) =>
      val (score, idx) = elt
      accum.update(score, idx, nOccurrences, locations)
    }
  }

}
object Constraints {
  def empty: Constraints = Constraints(Map.empty, Map.empty, Set.empty)
}
