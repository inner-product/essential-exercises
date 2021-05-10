package felines

import munit._

class OptionsSuite extends FunSuite {
  import Options._

  test("addIntOption") {
    assertEquals(addIntOption(Some(1), Some(2)), Some(3))
    assertEquals(addIntOption(Some(1), None), Some(1))
    assertEquals(addIntOption(None, Some(2)), Some(2))
    assertEquals(addIntOption(None, None), None)
  }

  test("tupleOption") {
    assertEquals(tupleOption(Some(1), Some("Hi")), Some((3, "Hi")))
    assertEquals(tupleOption(Some(1), None), None)
    assertEquals(tupleOption(None, Some("Hi")), None)
    assertEquals(tupleOption(None, None), None)
  }

  test("appendListOption") {
    assertEquals(
      appendListOption(Some(List("error 1")), Some(List("error 2"))),
      Some(List("error 1", "error 2"))
    )
    assertEquals(appendListOption(Some(List("error 1")), None), None)
    assertEquals(appendListOption(None, Some(List("error 2"))), None)
    assertEquals(appendListOption(None, None), None)
  }

  test("multiplyIntOption") {
    assertEquals(multiplyIntOption(Some(1), Some(2)), Some(2))
    assertEquals(multiplyIntOption(Some(1), None), Some(1))
    assertEquals(multiplyIntOption(None, Some(2)), Some(2))
    assertEquals(multiplyIntOption(None, None), None)
  }
}
