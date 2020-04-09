package com.voloshko.algorithms.sort;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Priority queue
 * Insertion: O(logN)
 * Retrieval: O(1)
 *
 * @author avoloshko
 */
public class PriorityQueue<Type extends Comparable<Type>> extends AbstractQueue<Type> {
  List<Type> elements = new ArrayList<>();

  @Override
  public boolean add(Type value) {
    elements.add(value);
    ascend(elements.size() - 1);
    return true;
  }

  @Override
  public boolean removeIf(Predicate<? super Type> filter) {
    return false;
  }

  @Override
  public boolean offer(Type type) {
    return false;
  }

  @Override
  public Type peek() {
    if (elements.size() == 0) {
      throw new NoSuchElementException("The queue is empty");
    }
    return elements.get(0);
  }

  @Override
  public Type poll() {
    Type result = peek();

    Type bottom = elements.remove(elements.size() - 1);
    if (size() > 0) {
      elements.set(0, bottom);
      heapify(0, elements.size());
    }

    return result;
  }

  @Override
  public Iterator<Type> iterator() {
    return elements.iterator();
  }

  @Override
  public void forEach(Consumer<? super Type> action) {
    elements.forEach(action);
  }

  @Override
  public int size() {
    return elements.size();
  }

  @Override
  public void clear() {
    elements.clear();
  }

  @Override
  public Spliterator<Type> spliterator() {
    return elements.spliterator();
  }

  @Override
  public Stream<Type> stream() {
    return elements.stream();
  }

  @Override
  public Stream<Type> parallelStream() {
    return elements.parallelStream();
  }

  void swap(int i, int j) {
    Type temp = elements.get(i);
    elements.set(i, elements.get(j));
    elements.set(j, temp);
  }

  void ascend(int index) {
    if (index == 0) {
      return;
    }

    int parent = (index + 1) / 2 - 1;

    if (elements.get(index).compareTo(elements.get(parent)) > 0) {
      swap(index, parent);
      ascend(parent);
    }
  }

  void heapify(int index, int length) {
    int left = index * 2 + 1;
    int right = left + 1;
    int largest = index;
    if (left < length && elements.get(left).compareTo(elements.get(largest)) > 0) {
      largest = left;
    }
    if (right < length && elements.get(right).compareTo(elements.get(largest)) > 0) {
      largest = right;
    }
    if (largest != index) {
      swap(largest, index);
      heapify(largest, length);
    }
  }
}
