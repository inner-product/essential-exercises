package wordle

import scala.io.Source

object WordList {
  def read: List[String] =
    Source
      .fromInputStream(getClass.getResourceAsStream("/wordle.txt"))
      .getLines()
      .toList
}
