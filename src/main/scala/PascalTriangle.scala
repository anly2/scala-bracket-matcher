package me.aanchev


object PascalTriangle {
  def getTriangle(depth: Int): List[List[Int]] = {
    getTriangle().take(depth).toList
  }

  def getTriangle(): LazyList[List[Int]] = {
    LazyList.iterate(List(1))(getNextTriangleRow)
  }

  def getNextTriangleRow(previousRow: List[Int]): List[Int] = {
    previousRow.zipWithIndex
      .map({ case (n, i) => n + (if (i > 0) previousRow(i - 1) else 0) })
      .appended(1)
  }
}