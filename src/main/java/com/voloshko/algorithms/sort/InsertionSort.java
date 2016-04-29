package com.voloshko.algorithms.sort;

/**
 * Insertion sort
 * Computational complexity: O(n2)
 *
 * @author avoloshko
 */
public class InsertionSort<Type extends Comparable<Type>> {
	void swap(Type[] array, int i, int j) {
		Type temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public void sort(Type[] array) {
		for (int i = 1; i < array.length; ++i) {
			for (int j = i; j > 0 && array[j].compareTo(array[j - 1]) < 0; --j) {
				swap(array, j, j - 1);
			}
		}
	}
}
