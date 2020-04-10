package com.voloshko.algorithms;

import com.voloshko.algorithms.graphs.KruskalMST;
import com.voloshko.algorithms.utils.Graph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KruskalMSTTest {
  @Test
  public void test() {
    Graph<Integer, Integer> graph = new Graph<>();

    Graph<Integer, Integer>.Edge e1 = graph.addEdge(0, 1, 1);
    Graph<Integer, Integer>.Edge e2 = graph.addEdge(1, 2, 1);
    Graph<Integer, Integer>.Edge e3 = graph.addEdge(2, 3, 1);
    Graph<Integer, Integer>.Edge e4 = graph.addEdge(3, 4, 1);
    Graph<Integer, Integer>.Edge e5 = graph.addEdge(3, 5, 5);
    Graph<Integer, Integer>.Edge e6 = graph.addEdge(2, 5, 4);
    Graph<Integer, Integer>.Edge e7 = graph.addEdge(1, 5, 3);
    Graph<Integer, Integer>.Edge e8 = graph.addEdge(0, 5, 2);

    Graph<Integer, Integer> result = new KruskalMST().find(graph);

    assertEquals(result.vertices(), graph.vertices());
    assertEquals(result.edges().size(), 5);
    assertEquals(result.edges().get(0), e5);
    assertEquals(result.edges().get(1), e6);
    assertEquals(result.edges().get(2), e7);
    assertEquals(result.edges().get(3), e8);
    assertEquals(result.edges().get(4), e4);
    assertEquals(((Integer) result.edges().stream().mapToInt(it -> it.w).sum()), 15);
  }
}
