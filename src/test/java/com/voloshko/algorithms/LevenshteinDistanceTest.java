package com.voloshko.algorithms;

import com.voloshko.algorithms.dynamic.LevenshteinDistance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevenshteinDistanceTest {
    @Test
    public void test() {
        assertEquals(4, LevenshteinDistance.findDistance("aazzzaaarrryy", "aatzzzbbarryy"));
    }
}
