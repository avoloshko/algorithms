package com.voloshko.algorithms.utils;

import java.util.*;

public class Graph<T, V extends Comparable<V>> {
  public class Edge implements Comparable<Edge> {
    public final T u;
    public final T v;
    public final V w;

    Edge(T u, T v, V w) {
      this.u = u;
      this.v = v;
      this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
      return w.compareTo(o.w);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Edge edge = (Edge) o;
      return Objects.equals(u, edge.u) &&
              Objects.equals(v, edge.v) &&
              Objects.equals(w, edge.w);
    }

    @Override
    public int hashCode() {
      return Objects.hash(u, v, w);
    }
  }

  Map<T, Map<T, Edge>> adjacencyList = new HashMap<>();
  List<Edge> edges = new ArrayList<>();

  public Edge addEdge(T u, T v, V w) {
    Edge edge = new Edge(u, v, w);
    edges.add(edge);

    adjacencyList.computeIfAbsent(u, k -> new HashMap<>()).put(v, edge);
    adjacencyList.computeIfAbsent(v, k -> new HashMap<>()).put(u, edge);

    return edge;
  }

  public List<Edge> edges() {
    return Collections.unmodifiableList(edges);
  }

  public List<Edge> edges(T u) {
    return Collections.unmodifiableList(new ArrayList<>(adjacencyList.get(u).values()));
  }

  public Set<T> vertices() {
    return Collections.unmodifiableSet(adjacencyList.keySet());
  }
}
