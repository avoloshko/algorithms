package com.voloshko.algorithms.dynamic

import kotlin.math.max
import kotlin.math.sign

/**
 * Length of Longest Subsequence
 *
 * Given an array of integers, find the length of longest subsequence which is first increasing then decreasing.
 *
 * O(n)
 *
 * @author avoloshko
 */

fun longestSubsequence(seq: IntArray): Int {
    if (seq.size <= 2) return 0

    val inc = IntArray(seq.size)
    val dec = IntArray(seq.size)

    inc[0] = 1
    dec[seq.size - 1] = 1;

    for (i in 1 until seq.size) {
        for (j in i - 1 downTo 0) {
            if (seq[j] < seq[i]) {
                inc[i] = max(inc[i], inc[j] + 1)
            }
        }
    }

    for (i in seq.size - 2 downTo 0) {
        for (j in i + 1 until seq.size) {
            if (seq[j] < seq[i]) {
                dec[i] = max(dec[i], dec[j] + 1)
            }
        }
    }

    var res = 0
    for (i in 0 until seq.size) {
        res = max(res, inc[i] + dec[i] - 1);
    }

    return res
}