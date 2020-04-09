package com.voloshko.algorithms.sort;

/**
 * Heap sort
 * Computational complexity: O(nlogn)
 * Memory: O(n)
 *
 * @author avoloshko
 */
public class HeapSort<Type extends Comparable<Type>> {
  public void sort(Type[] array) {
    for (int i = array.length / 2 - 1; i >= 0; --i) {
      heapify(array, i, array.length);
    }

    for (int i = array.length - 1; i > 0; --i) {
      swap(array, i, 0);
      heapify(array, 0, i);
    }
  }

  void swap(Type[] array, int i, int j) {
    Type temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  void heapify(Type[] array, int index, int length) {
    int left = index * 2 + 1;
    int right = left + 1;
    int largest = index;
    if (left < length && array[left].compareTo(array[largest]) > 0) {
      largest = left;
    }
    if (right < length && array[right].compareTo(array[largest]) > 0) {
      largest = right;
    }
    if (largest != index) {
      swap(array, largest, index);
      heapify(array, largest, length);
    }
  }
}
