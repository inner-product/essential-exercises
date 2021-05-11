package felines

import munit._

class ListsSuite extends FunSuite {
  import Lists._

  test("listOfOptionToOptionOfList") {
    assertEquals(
      listOfOptionToOptionOfList(List(Some(1), Some(2), Some(3))),
      Some(List(1, 2, 3))
    )
    assertEquals(listOfOptionToOptionOfList(List(None, Some(2), Some(3))), None)
    assertEquals(listOfOptionToOptionOfList(List()), Some(List()))
  }

  test("addOptionList") {
    assertEquals(addOptionList(List(Some(1), Some(2), Some(3))), Some(6))
    assertEquals(addOptionList(List(Some(1), None, Some(2))), Some(3))
    assertEquals(addOptionList(List(None)), None)
    assertEquals(addOptionList(List()), None)
  }

  test("listErrorHandling") {
    val isEven: Int => Option[Int] = x => if (x % 2 == 0) Some(x) else None
    assertEquals(listErrorHandling(List(2, 4, 6), isEven), Some(List(2, 4, 6)))
    assertEquals(listErrorHandling(List(), isEven), Some(List()))
    assertEquals(listErrorHandling(List(3, 4, 6), isEven), None)
  }
}
