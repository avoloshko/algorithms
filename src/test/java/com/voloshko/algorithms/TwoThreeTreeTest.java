package com.voloshko.algorithms;

import com.voloshko.algorithms.sort.HeapSort;
import com.voloshko.algorithms.sort.TwoThreeTree;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

public class TwoThreeTreeTest extends TestCase {
	public TwoThreeTreeTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(TwoThreeTreeTest.class);
	}

	public void test() {
		TwoThreeTree<Integer, Integer> tree = new TwoThreeTree<>();

		Integer[] array = new Integer[1000000];
		for (int i = 0; i < array.length; i++) {
			array[i] = array.length - i;
		}

		Collections.shuffle(Arrays.asList(array));

		for (Integer integer : array) {
			tree.get(integer, integer * 2);
		}

		for (Integer integer : array) {
			assert tree.find(integer) == integer * 2;
		}

		assert tree.maxHeight() < 20;
	}
}
