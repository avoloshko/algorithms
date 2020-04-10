package com.voloshko.algorithms;

import com.voloshko.algorithms.graphs.DisjointSets;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DisjointSetsTest {
  @Test
  public void test() {
    DisjointSets<String> disjointSets =
            new DisjointSets<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H"));

    assertNull(disjointSets.find("J"));

    disjointSets.union("A", "B");
    disjointSets.union("C", "D");
    disjointSets.union("E", "F");
    disjointSets.union("G", "H");
    disjointSets.union("C", "B");
    disjointSets.union("G", "D");
    disjointSets.union("E", "B");

    System.out.println(disjointSets.toString());

    assertEquals(disjointSets.find("E"), disjointSets.find("A"));
  }
}
