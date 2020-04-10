package com.voloshko.algorithms.graphs;

import com.voloshko.algorithms.sort.PriorityQueue;
import com.voloshko.algorithms.utils.Graph;

/**
 * Prims MST
 * Computational complexity: O((E+V)logE)
 *
 * @author avoloshko
 */
public class PrimsMST {
  public <T, V extends Comparable<V>> Graph<T, V> find(Graph<T, V> graph) {
    Graph<T, V> result = new Graph<>();
    if (!graph.vertices().iterator().hasNext()) {
      return result;
    }

    PriorityQueue<Graph<T, V>.Edge> queue = new PriorityQueue<>();

    T vertex = graph.vertices().iterator().next();

    DisjointSets<T> disjointSets = new DisjointSets<>(graph.vertices());

    queue.addAll(graph.edges(vertex));

    while (!queue.isEmpty()) {
      Graph<T, V>.Edge edge = queue.poll();
      Object set1 = disjointSets.find(edge.u);
      Object set2 = disjointSets.find(edge.v);
      if (set1 == set2) {
        continue;
      }
      disjointSets.union(edge.u, edge.v);
      result.addEdge(edge.u, edge.v, edge.w);

      queue.addAll(graph.edges(edge.u));
      queue.addAll(graph.edges(edge.v));
    }

    return result;
  }
}
