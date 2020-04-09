package com.voloshko.algorithms.dynamic;

import java.util.Arrays;

/**
 * Knapsack problem: Given a set of items,
 * each with a weight and a value, determine the number of each item to include in a collection
 * so that the total weight is less than or equal to a given limit and the total value is as large as possible.
 * Computational complexity: O(nw)
 *
 * @author avoloshko
 */
public class KnapsackProblem {
  public static int[] getIndices(int[] weights, int[] values, int maxWeight) {
    assert weights.length == values.length;

    short[][][] solutionIndices = new short[maxWeight + 1][weights.length + 1][0];
    int[][] solutions = new int[maxWeight + 1][weights.length + 1];

    for (int item = 1; item <= weights.length; ++item) {
      for (int capacity = 1; capacity <= maxWeight; ++capacity) {
        int itemWeight = weights[item - 1];
        if (capacity >= itemWeight) {
          int itemVal = values[item - 1];

          int remainingCapacity = capacity - itemWeight;
          itemVal += solutions[remainingCapacity][item - 1];

          int noItemVal = solutions[capacity][item - 1];

          if (itemVal > noItemVal) {
            solutions[capacity][item] = itemVal;

            short[] indices = solutionIndices[remainingCapacity][item - 1];
            if (indices.length == 0) {
              solutionIndices[capacity][item] = new short[]{(short) (item - 1)};
            } else {
              solutionIndices[capacity][item] = Arrays.copyOf(indices, indices.length + 1);
              solutionIndices[capacity][item][indices.length] = (short) (item - 1);
            }
          } else {
            solutions[capacity][item] = noItemVal;
            solutionIndices[capacity][item] = solutionIndices[capacity][item - 1];
          }
        } else {
          solutions[capacity][item] = solutions[capacity][item - 1];
          solutionIndices[capacity][item] = solutionIndices[capacity][item - 1];
        }
      }
    }

    short[] indices = solutionIndices[maxWeight][weights.length];
    int[] result = new int[indices.length];
    for (int i = 0; i < result.length; ++i) {
      result[i] = indices[i];
    }
    return result;
  }

  public static int getMaxWeight(int[] weights, int[] values, int maxWeight) {
    assert weights.length == values.length;

    int[][] solutions = new int[maxWeight + 1][weights.length + 1];

    for (int item = 1; item <= weights.length; ++item) {
      for (int capacity = 1; capacity <= maxWeight; ++capacity) {
        int noItemVal = solutions[capacity][item - 1];
        int itemVal = 0;

        int itemWeight = weights[item - 1];

        if (capacity >= itemWeight) {
          itemVal = values[item - 1];

          int remainingCapacity = capacity - itemWeight;
          itemVal += solutions[remainingCapacity][item - 1];
        }

        solutions[capacity][item] = Math.max(noItemVal, itemVal);
      }
    }

    return solutions[maxWeight][weights.length];
  }
}
