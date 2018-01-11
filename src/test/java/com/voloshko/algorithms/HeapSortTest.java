package com.voloshko.algorithms;

import com.voloshko.algorithms.dynamic.LevenshteinDistance;
import com.voloshko.algorithms.sort.HeapSort;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class HeapSortTest extends TestCase {
    public HeapSortTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(HeapSortTest.class);
    }

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
