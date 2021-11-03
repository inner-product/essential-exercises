package sequencing

import kantan.csv._
import kantan.csv.ops._

object FingerExercises {
  final case class Population(country: String, year: Int, population: Int)
  implicit val decoder: RowDecoder[Population] = RowDecoder.ordered {
    (c: String, y: Int, p: Int) =>
      Population(c, y, p)
  }

  val data: List[Population] =
    this.getClass
      .getResource("/population.csv")
      .asCsvReader[Population](rfc)
      .toList
      .map(result =>
        result match {
          case Right(population) => population
          case Left(error) =>
            throw new Exception(s"Reading data failed with error $error")
        }
      )

  // Answer the following questions:
  //
  // 1. How many elements are there in the data?
  // 2. How many different countries are represented?
  // 3. Which country has the highest population?
  // 4. Which country has the lowest population?
  // 5. Which country has the highest population in 1960?
  // 6. Which country has the lowest population in 2018?
  // 7. Which country had the largest absolute growth in population from one year to the next?
  // 8. Which country had the largest relative (percentage) growth in population from one year to the next?
  // 9. Which countries had population decline since 1980?
  // 10. Which are the ten smallest countries?
}
