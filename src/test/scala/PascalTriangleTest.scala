package me.aanchev

import scala.List

class PascalTriangleTest extends org.scalatest.flatspec.AnyFlatSpec {
  it should "generate triangle of depth 3" in {
    val actual = PascalTriangle.getTriangle(3)

    assert(actual === List(
      List(1),
      List(1, 1),
      List(1, 2, 1)
    ))
  }
  it should "generate triangle of depth 6" in {
    val actual = PascalTriangle.getTriangle(6)

    assert(actual === List(
      List(1),
      List(1, 1),
      List(1, 2, 1),
      List(1, 3, 3, 1),
      List(1, 4, 6, 4, 1),
      List(1, 5, 10, 10, 5, 1)
    ))
  }
}
