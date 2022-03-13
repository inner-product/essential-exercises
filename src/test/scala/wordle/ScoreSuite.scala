package wordle

import munit.FunSuite

class ScoreSuite extends FunSuite {
  test("Scoring is correct for guesses that exactly match the answer") {
    val expected = Score(
      ExactlyCorrect,
      ExactlyCorrect,
      ExactlyCorrect,
      ExactlyCorrect,
      ExactlyCorrect
    )

    assertEquals(Score("katsu", "katsu"), expected)
    assertEquals(Score("total", "total"), expected)
    assertEquals(Score("vivid", "vivid"), expected)
    assertEquals(Score("fairy", "fairy"), expected)
    assertEquals(Score("cynic", "cynic"), expected)
  }

  test("Scoring is correct for guesses that are partly correct") {
    val expected = Score(
      PartlyCorrect,
      PartlyCorrect,
      PartlyCorrect,
      PartlyCorrect,
      PartlyCorrect
    )

    assertEquals(Score("katsu", "aksut"), expected)
    assertEquals(Score("total", "otalt"), expected)
    assertEquals(Score("vivid", "ividv"), expected)
    assertEquals(Score("fairy", "airyf"), expected)
    assertEquals(Score("cynic", "ycicn"), expected)
  }

  test(
    "Scoring is correct for guesses that are a mixture of fully and partly correct"
  ) {
    val expected = Score(
      PartlyCorrect,
      ExactlyCorrect,
      PartlyCorrect,
      PartlyCorrect,
      ExactlyCorrect
    )

    assertEquals(Score("katsu", "tasku"), expected)
    assertEquals(Score("fairy", "rafiy"), expected)
    assertEquals(Score("saint", "nasit"), expected)
    assertEquals(Score("rogue", "goure"), expected)
    assertEquals(Score("adieu", "edaiu"), expected)
  }

  test(
    "Scoring is correct for guesses that are a mixture of all cases"
  ) {
    val expected = Score(
      PartlyCorrect,
      ExactlyCorrect,
      Incorrect,
      Incorrect,
      ExactlyCorrect
    )

    // Note we repeat correct letters in an incorrect position in some of the
    // tests below, to check that case.
    assertEquals(Score("katsu", "taynu"), expected)
    assertEquals(Score("fairy", "rakey"), expected)
    assertEquals(Score("saint", "najmt"), expected)
    assertEquals(Score("rogue", "goeae"), expected)
    assertEquals(Score("adieu", "eduru"), expected)
  }
}
