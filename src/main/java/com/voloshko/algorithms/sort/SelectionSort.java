package com.voloshko.algorithms.sort;

/**
 * Selection sort
 * Computational complexity: O(n2)
 *
 * @author avoloshko
 */
public class SelectionSort<Type extends Comparable<Type>> {
	void swap(Type[] array, int i, int j) {
		Type temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public void sort(Type[] array) {
		for (int i = 0; i < array.length; ++i) {
			int min = i;
			for (int j = i + 1; j < array.length; ++j)
				if (array[min].compareTo(array[j]) > 0)
					min = j;
			swap(array, min, i);
		}
	}
}
