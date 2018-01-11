package com.voloshko.algorithms;

import com.voloshko.algorithms.dynamic.LevenshteinDistance;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class LevenshteinDistanceTest extends TestCase {
    public LevenshteinDistanceTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(LevenshteinDistanceTest.class);
    }

    public void test() {
        assertTrue(LevenshteinDistance.findDistance("aazzzaaarrryy", "aatzzzbbarryy") == 4);
    }
}
