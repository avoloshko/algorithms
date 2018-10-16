package com.voloshko.algorithms;

import com.voloshko.algorithms.crypo.DiffieHellman;
import com.voloshko.algorithms.sort.HeapSort;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

public class DiffieHellmanTest extends TestCase {
    public DiffieHellmanTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(DiffieHellmanTest.class);
    }

    public void test() {
        DiffieHellman actor1 = new DiffieHellman();
        int pub1 = actor1.getPublic();

        DiffieHellman actor2 = new DiffieHellman(actor1.getPrime(), actor1.getNum());
        int pub2 = actor2.getPublic();

        int a = 2;
        a = 5;
    }
}
