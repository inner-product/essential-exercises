package wordle

import munit.FunSuite

class ScoreSuite extends FunSuite {
  test("Scoring is correct for guesses that exactly match the answer") {
    assert(Score("katsu", "katsu").isExactlyCorrect)
    assert(Score("total", "total").isExactlyCorrect)
    assert(Score("vivid", "vivid").isExactlyCorrect)
    assert(Score("fairy", "fairy").isExactlyCorrect)
    assert(Score("cynic", "cynic").isExactlyCorrect)
  }

  test("Scoring is correct for guesses that are partly correct") {
    assertEquals(
      Score("katsu", "aksut"),
      Score(
        PartlyCorrect('a'),
        PartlyCorrect('k'),
        PartlyCorrect('s'),
        PartlyCorrect('u'),
        PartlyCorrect('t')
      )
    )
    assertEquals(
      Score("total", "otalt"),
      Score(
        PartlyCorrect('o'),
        PartlyCorrect('t'),
        PartlyCorrect('a'),
        PartlyCorrect('l'),
        PartlyCorrect('t')
      )
    )
    assertEquals(
      Score("vivid", "ividv"),
      Score(
        PartlyCorrect('i'),
        PartlyCorrect('v'),
        PartlyCorrect('i'),
        PartlyCorrect('d'),
        PartlyCorrect('v')
      )
    )
    assertEquals(
      Score("fairy", "airyf"),
      Score(
        PartlyCorrect('a'),
        PartlyCorrect('i'),
        PartlyCorrect('r'),
        PartlyCorrect('y'),
        PartlyCorrect('f')
      )
    )
    assertEquals(
      Score("cynic", "ycicn"),
      Score(
        PartlyCorrect('y'),
        PartlyCorrect('c'),
        PartlyCorrect('i'),
        PartlyCorrect('c'),
        PartlyCorrect('n')
      )
    )
  }

  test(
    "Scoring is correct for guesses that are a mixture of fully and partly correct"
  ) {
    assertEquals(
      Score("katsu", "tasku"),
      Score(
        PartlyCorrect('t'),
        ExactlyCorrect('a'),
        PartlyCorrect('s'),
        PartlyCorrect('k'),
        ExactlyCorrect('u')
      )
    )
    assertEquals(
      Score("fairy", "rafiy"),
      Score(
        PartlyCorrect('r'),
        ExactlyCorrect('a'),
        PartlyCorrect('f'),
        PartlyCorrect('i'),
        ExactlyCorrect('y')
      )
    )
    assertEquals(
      Score("saint", "nasit"),
      Score(
        PartlyCorrect('n'),
        ExactlyCorrect('a'),
        PartlyCorrect('s'),
        PartlyCorrect('i'),
        ExactlyCorrect('t')
      )
    )
    assertEquals(
      Score("rogue", "goure"),
      Score(
        PartlyCorrect('g'),
        ExactlyCorrect('o'),
        PartlyCorrect('u'),
        PartlyCorrect('r'),
        ExactlyCorrect('e')
      )
    )
  }

  test(
    "Scoring is correct for guesses that are a mixture of all cases"
  ) {
    // Note we repeat correct letters in an incorrect position in some of the
    // tests below, to check that case.
    assertEquals(
      Score("katsu", "taynu"),
      Score(
        PartlyCorrect('t'),
        ExactlyCorrect('a'),
        Incorrect('y'),
        Incorrect('n'),
        ExactlyCorrect('u')
      )
    )
    assertEquals(
      Score("fairy", "rakey"),
      Score(
        PartlyCorrect('r'),
        ExactlyCorrect('a'),
        Incorrect('k'),
        Incorrect('e'),
        ExactlyCorrect('y')
      )
    )
    assertEquals(
      Score("rogue", "goeae"),
      Score(
        PartlyCorrect('g'),
        ExactlyCorrect('o'),
        Incorrect('e'),
        Incorrect('a'),
        ExactlyCorrect('e')
      )
    )
    assertEquals(
      Score("adieu", "eduru"),
      Score(
        PartlyCorrect('e'),
        ExactlyCorrect('d'),
        Incorrect('u'),
        Incorrect('r'),
        ExactlyCorrect('u')
      )
    )
  }
}
