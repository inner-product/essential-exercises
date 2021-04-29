package section3

import munit._

class MyListSuite extends FunSuite {
  // Constructors
  //
  test("empty") {
    assertEquals(MyList.empty[Int], MyList.empty[Int])
  }

  test("vararg constructor") {
    val list1 = MyList(1, 2, 3, 4)
    val list2 = MyList(1, 2, 3, 4)
    assertEquals(list1, list2)
  }

  // Querying
  //
  test("size") {
    assertEquals(MyList.empty[Int].size, 0)
    assertEquals(MyList(1).size, 1)
    assertEquals(MyList(1, 2).size, 2)
    assertEquals(MyList(1, 2, 3).size, 3)
    assertEquals(MyList(1, 2, 3, 4).size, 4)
  }

  test("contains +ve case") {
    assertEquals(MyList(1, 2, 3, 4).contains(1), true)
    assertEquals(MyList(1, 2, 3, 4).contains(2), true)
    assertEquals(MyList(1, 2, 3, 4).contains(3), true)
    assertEquals(MyList(1, 2, 3, 4).contains(4), true)
  }

  test("contains -ve case") {
    assertEquals(MyList(1, 2, 3, 4).contains(5), false)
    assertEquals(MyList(1, 2, 3, 4).contains(-1), false)
    assertEquals(MyList.empty[Int].contains(0), false)
    assertEquals(MyList.empty[Int].contains(1), false)
  }

  test("find +ve case") {
    assertEquals(MyList(1, 2, 3, 4).find(1), Some(1))
    assertEquals(MyList(1, 2, 3, 4).find(2), Some(2))
    assertEquals(MyList(1, 2, 3, 4).find(3), Some(3))
    assertEquals(MyList(1, 2, 3, 4).find(4), Some(4))
  }

  test("find -ve case") {
    assertEquals(MyList(1, 2, 3, 4).find(5), None)
    assertEquals(MyList(1, 2, 3, 4).find(-1), None)
    assertEquals(MyList.empty[Int].find(0), None)
    assertEquals(MyList.empty[Int].find(1), None)
  }


  // Combinators
  //

}
