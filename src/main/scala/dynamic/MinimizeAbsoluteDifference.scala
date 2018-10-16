package dynamic

object MinimizeAbsoluteDifference extends App {
  println(solve(Array(1, 2), Array(6, 7), Array(1)))

  def solve(A: Array[Int], B: Array[Int], C: Array[Int]): Int = {
    var (i, j, k) = (A.length - 1, B.length - 1, C.length - 1)
    var res = Int.MaxValue
    while (true) {
      var (a, b, c) = (A(i), B(j), C(k))
      val (min, max) = (Math.min(a, Math.min(b, c)), Math.max(a, Math.max(b, c)))
      res = Math.min(res, max - min)

      if (max == a) {
        if (i > 0) {
          i -= 1
        } else {
          if (b >= c && j > 0) {
            j -= 1
          } else if (k > 0) {
            k -= 1
          } else {
            return res
          }
        }
      } else if (max == b) {
        if (j > 0) {
          j -= 1
        } else {
          if (a >= c && i > 0) {
            i -= 1
          } else if (k > 0) {
            k -= 1
          } else {
            return res
          }
        }
      } else {
        if (k > 0) {
          k -= 1
        } else {
          if (b >= a && j > 0) {
            j -= 1
          } else if (i > 0) {
            i -= 1
          } else {
            return res
          }
        }
      }
    }

    res
  }

  /*
  def merge(A: Array[Int], B: Array[Int]): Array[Int] = {
    val res = new Array[Int](A.length + B.length)

    var (i, j, k) = (0, 0, 0)

    while (k < res.length) {
      if (i < A.length && j < B.length && A(i) < B(j)) {
        res(k) = A(i)
        i += 1
      } else if (j < B.length) {
        res(k) = B(j)
        j += 1
      }
      k += 1
    }
    res
  }*/
}