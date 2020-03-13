package com.voloshko.algorithms;

import com.voloshko.algorithms.dynamic.KnapsackProblem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnapsackProblemTest {
  @Test
  public void test() {
    int[] weights = new int[12];
    int[] values = new int[weights.length];
    int testCount = 10000;
    Random random = new Random(0);
    int maxWeight = 250;

    for (int i = 0; i < testCount; ++i) {
      for (int j = 0; j < weights.length; ++j) {
        weights[j] = random.nextInt(maxWeight / weights.length);
      }
      for (int j = 0; j < weights.length; ++j) {
        values[j] = Math.abs(random.nextInt() % 1000);
      }

      assertArrayEquals(bruteForce(weights, values, maxWeight),
        KnapsackProblem.getIndices(weights, values, maxWeight));
    }
  }

  @Test
  public void testPerformance() {
    Random random = new Random(0);

    for (int count = 0; count < 200; ++count) {
      int[] weights = new int[count];
      int[] values = new int[weights.length];
      int maxWeight = 1000;
      for (int j = 0; j < weights.length; ++j) {
        weights[j] = 1;
      }
      for (int j = 0; j < weights.length; ++j) {
        values[j] = Math.abs(random.nextInt() % 1000);
      }

      long time1 = System.currentTimeMillis();
      int result1 = KnapsackProblem.getMaxWeight(weights, values, maxWeight);
      time1 = System.currentTimeMillis() - time1;

      long time2 = System.currentTimeMillis();
      int[] indexes = KnapsackProblem.getIndices(weights, values, maxWeight);
      time2 = System.currentTimeMillis() - time2;
      int result2 = IntStream.of(indexes).map(i -> values[i]).sum();

      System.out.println(String.format("Perf[%d]: %d/%d", count, time1, time2));

      assertEquals(result1, result2);
    }
  }

  private static int[] bruteForce(int[] weights, int[] values, int maxWeight) {
    List<Integer> maxVector = Collections.emptyList();
    int maxValue = 0;
    for (int i = 0; i < (1 << weights.length); i++) {
      List<Integer> vector = new ArrayList<>();
      for (int j = 0; j < weights.length; j++) {
        if ((i & (1 << j)) > 0) {
          vector.add(j);
        }
      }

      int totalWeight = 0;
      int totalValue = 0;
      for (Integer item : vector) {
        totalWeight += weights[item];
        totalValue += values[item];
      }

      if (totalWeight <= maxWeight && totalValue > maxValue) {
        maxValue = totalValue;
        maxVector = vector;
      }
    }

    return maxVector.stream().mapToInt(i -> i).toArray();
  }
}
