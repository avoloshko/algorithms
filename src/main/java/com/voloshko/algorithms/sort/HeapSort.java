package com.voloshko.algorithms.sort;

/**
 * Heap sort
 * Computational complexity: O(nlogn)
 * Memory: O(n)
 *
 * @author avoloshko
 */
public class HeapSort<Type extends Comparable<Type>> {

	void swap(Type[] array, int i, int j) {
		Type temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	void ascend(Type[] array, int idx) {
		if (idx == 0) {
			return;
		}

		Type node = array[idx];
		int parentIdx = (idx + 1) / 2 - 1;
		Type parent = array[parentIdx];
		int compare = parent.compareTo(node);
		if (compare < 0) {
			swap(array, idx, parentIdx);
			ascend(array, parentIdx);
		}
	}

	void descend(Type[] array, int idx, int length) {
		Type node = array[idx];

		int childLeftIdx = idx * 2 + 1;
		int childRightIdx = childLeftIdx + 1;
		if (childRightIdx < length) {
			Type leftNode = array[childLeftIdx];
			Type rightNode = array[childRightIdx];

			if (rightNode.compareTo(leftNode) > 0) {
				if (rightNode.compareTo(node) > 0) {
					swap(array, idx, childRightIdx);
					descend(array, childRightIdx, length);
				}
			} else if (leftNode.compareTo(node) > 0) {
				swap(array, idx, childLeftIdx);
				descend(array, childLeftIdx, length);
			}
		} else if (childLeftIdx < length) {
			Type leftNode = array[childLeftIdx];

			if (leftNode.compareTo(node) > 0) {
				swap(array, idx, childLeftIdx);
				descend(array, childLeftIdx, length);
			}
		}
	}

	public void sort(Type[] array) {
		for (int i = 1; i < array.length; ++i) {
			ascend(array, i);
		}

		for (int i = array.length - 1; i > 0; --i) {
			swap(array, 0, i);
			descend(array, 0, i);
		}
	}
}
