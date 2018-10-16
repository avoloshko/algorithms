package dynamic

import scala.collection.mutable.ArrayBuffer

object NumberMultiplier extends App {
  println(multiply("007", "9"))
  def multiply(A: String, B: String): String = {
    val arr = new ArrayBuffer[Int]
    def getVal(i: Int): Int = {
      while (arr.length <= i)
        arr += 0
      arr(i)
    }

    def setVal(i: Int, v: Int): Unit = {
      while (arr.length <= i)
        arr += 0
      arr(i) = v
    }

    def addVal(i: Int, v: Int): Unit = {
      var j = i
      setVal(j, getVal(j) + v)
      while (getVal(j) >= 10) {
        setVal(j + 1, getVal(j + 1) + getVal(j) / 10)
        setVal(j, getVal(j) % 10)
        j += 1
      }
    }

    for (i <- 0 until A.length) {
      var ch1 = A.charAt(A.length - 1 - i) - 48
      for (j <- 0 until B.length) {
        val ch2 = B.charAt(B.length - 1 - j) - 48
        addVal(i + j, ch1 * ch2)
      }
    }

    while (arr.last == 0 && arr.length > 1) {
      arr.remove(arr.length - 1)
    }
    arr.reverse.mkString("")
  }
}