package com.voloshko.algorithms.graphs;

import com.voloshko.algorithms.utils.PrintableTree;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Disjoint sets
 * union: O(logN)
 * find: O(logN)
 * total: O(NlogN)
 *
 * @author avoloshko
 */
public class DisjointSets<T> {
  Map<T, T> items = new HashMap<>();
  Map<T, Integer> roots = new HashMap<>();
  Map<T, Integer> ranks = new HashMap<>();

  public DisjointSets(Collection<T> items) {
    for (T item : items) {
      roots.put(item, roots.size());
      ranks.put(item, 1);
    }
  }

  public void union(T a, T b) {
    T rootA = findRoot(a);
    T rootB = findRoot(b);

    if (rootA == rootB) {
      return;
    }

    int rankA = ranks.get(rootA);
    int rankB = ranks.get(rootB);

    if (rankA >= rankB) {
      roots.remove(rootB);
      ranks.remove(rootB);
      ranks.put(rootA, rankA + rankB);
      items.put(rootB, rootA);
    } else {
      roots.remove(rootA);
      ranks.remove(rootA);
      ranks.put(rootB, rankA + rankB);
      items.put(rootA, rootB);
    }
  }

  public Object find(T t) {
    T root = findRoot(t);
    if (root == null) {
      return null;
    }
    return roots.get(root);
  }

  T findRoot(T t) {
    T parent = items.get(t);
    if (parent != null) {
      return findRoot(parent);
    }
    Integer group = roots.get(t);
    if (group != null) {
      return t;
    }
    return null;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (T root : roots.keySet()) {
      Integer group = roots.get(root);
      builder.append(String.format("[%s]\n", group));

      Map<T, PrintableTree> tree = new HashMap<>();

      for (T item : items.keySet()) {
        PrintableTree itemNode = tree.computeIfAbsent(item, k -> new PrintableTree(item.toString()));
        T parent = items.get(item);
        PrintableTree parentNode = tree.computeIfAbsent(parent, k -> new PrintableTree(parent.toString()));
        parentNode.add(itemNode);
      }

      for (T item : roots.keySet()) {
        tree.computeIfAbsent(item, k -> new PrintableTree(item.toString()));
      }

      builder.append(tree.get(root));
    }

    return builder.toString();
  }
}
