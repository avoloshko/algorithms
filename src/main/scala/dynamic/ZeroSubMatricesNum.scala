package dynamic

object ZeroSubMatricesNum extends App {
  println(solve(Array(Array(-8, 5, 7), Array(3, 7, -8), Array(5, -8, 9))))

  def sum(A: Array[Array[Int]], N: Int, M: Int, i: Int, j: Int): Int = {
    var sum = 0
    for (k1 <- 0 until N) {
      for (k2 <- 0 until M) {
        sum += A(i + k1)(j + k2)
      }
    }
    sum
  }

  def count(A: Array[Array[Int]], N: Int, M: Int): Int = {
    var count = 0
    for (i <- 0 to A.length - N) {
      for (j <- 0 to A(0).length - M) {
        count += (if (sum(A, N, M, i, j) == 0) 1 else 0)
      }
    }
    count
  }

  def solveBruteForce(A: Array[Array[Int]]): Int = {
    var res = 0
    for (n <- 1 to A.length) {
      for (m <- 1 to A(0).length) {
        res += count(A, n, m)
      }
    }
    res
  }

  def solve(A: Array[Array[Int]]): Int = {
    var res = 0
    for (n <- 1 to A.length) {
      for (m <- 1 to A(0).length) {
        res += count(A, n, m)
      }
    }
    res
  }
}