package com.voloshko.algorithms.dynamic;

/**
 * Levenshtein distance: a string metric for measuring the difference between two sequences.
 * Computational complexity: O(nm)
 *
 * @author avoloshko
 */
public class LevenshteinDistance {
  static public int findDistance(String s1, String s2) {
    int[][] result = new int[s1.length() + 1][s2.length() + 1];

    for (int i = 0; i <= s1.length(); ++i) {
      result[i][0] = i;
    }
    for (int j = 0; j <= s2.length(); ++j) {
      result[0][j] = j;
    }

    for (int i = 0; i < s1.length(); ++i) {
      for (int j = 0; j < s2.length(); ++j) {
        if (s1.charAt(i) == s2.charAt(j))
          result[i + 1][j + 1] = result[i][j];
        else
          result[i + 1][j + 1] = Math.min(Math.min(result[i + 1][j], result[i][j + 1]), result[i][j]) + 1;
      }
    }

    return result[s1.length()][s2.length()];
  }
}
