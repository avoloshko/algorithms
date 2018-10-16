package com.voloshko.algorithms.dynamic

import kotlin.math.min

enum class Weight(val value: Int) {
    ADD(1),
    DEL(1),
    REP(1)
}

fun levenshteinDistance(from: String, to: String): Int {

    val mat = Array(from.length + 1, { IntArray(to.length + 1) })

    for (i in 0..from.length) mat[i][0] = i;
    for (i in 0..to.length) mat[0][i] = i;

    for (i in 1..from.length) {
        for (j in 1..to.length) {
            mat[i][j] = if (from[i - 1] == to[j - 1]) mat[i - 1][j - 1]
            else min(mat[i - 1][j - 1]
                    + Weight.REP.value, min(mat[i][j - 1]
                    + Weight.DEL.value, mat[i - 1][j] + Weight.ADD.value))
        }
    }

    return mat[from.length][to.length];
}