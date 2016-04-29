package com.voloshko.algorithms.sort;

/**
 * Merge sort
 * Computational complexity: O(nlogn)
 * Memory: O(n)
 *
 * @author avoloshko
 */
public class MergeSort<Type extends Comparable<Type>> {

	void swap(Type[] array, int i, int j) {
		Type temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	void sort(Type[] array, int l, int r, Type[] copy) {
		int length = r - l + 1;
		if (length == 2) {
			if (array[l].compareTo(array[r]) > 0)
				swap(array, l, r);
		} else if (length > 2) {
			int median = length / 2 + l;

			sort(array, l, median, copy);
			sort(array, l + median, r, copy);

			merge(array, l, median, l + median, r, copy);
		}
	}

	void merge(Type[] array, int li, int ri, int lj, int rj, Type[] copy) {
		int k = 0;

		for (; li <= ri && lj <= rj;) {
			int c = array[li].compareTo(array[lj]);
			if (c <= 0)
				copy[k] = array[li++];
			else
				copy[k++] = array[lj++];
		}

		while (li <= ri) {
			copy[k++] = array[li++];
		}

		while (lj <= rj) {
			copy[k++] = array[lj++];
		}

		for (int r = 0; r < (k - 1); ++r) {
			array[li + r] = copy[r];
		}
	}

	public void sort(Type[] array) {
		Type[] copy = array.clone();
		sort(array, 0, array.length - 1, copy);
	}
}
