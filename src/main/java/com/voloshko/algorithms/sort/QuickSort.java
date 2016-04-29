package com.voloshko.algorithms.sort;

import java.util.Random;

/**
 * Quick sort
 * Computational complexity: O(nlogn)
 *
 * @author avoloshko
 */
public class QuickSort<Type extends Comparable<Type>> {
	private Random random = new Random();

	void swap(Type[] array, int i, int j) {
		Type temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	int[] randomPartition(Type[] array, int l, int h) {
		int[] result = { l, h };
		Type median = array[random.nextInt(h) + l];
		do {
			while (result[0] <= h && array[result[0]].compareTo(median) < 0)
				result[0]++;
			while (result[1] >= 0 && array[result[1]].compareTo(median) > 0)
				result[1]--;

			if (result[0] <= result[1]) {
				result[0]++;
				result[1]--;
			}

		} while (result[0] <= result[1]);
		return result;
	}

	void sort_r(Type[] array, int l, int h) {
		if (l < h) {
			int[] p = randomPartition(array, l, h);
			sort_r(array, l, p[0]);
			sort_r(array, p[1], h);
		}
	}

	public void sort(Type[] array) {
		sort_r(array, 0, array.length - 1);
	}
}
