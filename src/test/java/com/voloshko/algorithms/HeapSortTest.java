package com.voloshko.algorithms;

import com.voloshko.algorithms.sort.HeapSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

public class HeapSortTest {
    @Test
    public void test() {
        Integer[] array = new Integer[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        Collections.shuffle(Arrays.asList(array));

        Integer[] arrayCopy = array.clone();

        new HeapSort<Integer>().sort(array);
        Arrays.sort(arrayCopy);

        assert Arrays.equals(arrayCopy, array);
    }
}
