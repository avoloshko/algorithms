package com.voloshko.algorithms.graphs;

import com.voloshko.algorithms.utils.Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Kruskal MST
 * Computational complexity: O(ElogV)
 *
 * @author avoloshko
 */
public class KruskalMST {
  public <T, V extends Comparable<V>> Graph<T, V> find(Graph<T, V> graph) {
    List<Graph<T, V>.Edge> edges = new ArrayList<>(graph.edges());
    edges.sort(Comparator.reverseOrder());

    DisjointSets<T> disjointSets = new DisjointSets<>(graph.vertices());

    Graph<T, V> result = new Graph<>();

    for (Graph<T, V>.Edge edge : edges) {
      Object set1 = disjointSets.find(edge.u);
      Object set2 = disjointSets.find(edge.v);
      if (set1 == set2) {
        continue;
      }

      disjointSets.union(edge.u, edge.v);
      result.addEdge(edge.u, edge.v, edge.w);
    }

    return result;
  }
}
