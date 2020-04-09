package com.voloshko.algorithms;

import com.voloshko.algorithms.sort.PriorityQueue;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriorityQueueTest {
    @Test
    public void test() {
        PriorityQueue<Integer> tested = new PriorityQueue<>();
        java.util.PriorityQueue<Integer> original = new java.util.PriorityQueue<>(Comparator.reverseOrder());

        for (int k = 0; k < 10000; ++k) {
            Random random = new Random(k);

            for (int i = 0; i < 7; ++i) {
                Integer next = random.nextInt(1024);
                tested.add(next);
                original.add(next);
            }

            while (tested.size() > 0) {
                assertEquals(tested.poll(), original.poll());
            }
        }
    }
}
