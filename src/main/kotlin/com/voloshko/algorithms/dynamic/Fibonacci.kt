package com.voloshko.algorithms.dynamic

/**
 * Fibonacci numbers
 * O(n)
 *
 * @author avoloshko
 */
fun fibonacci(n: Int): Long {
    //val ss = ArrayList<Int>
    val arr = LongArray(n + 1, { i -> if (i == 1) 1 else 0 })

    for (i in 2..n) {
        arr[i] = arr[i - 1] + arr[i - 2];
    }

    return arr.last()
}