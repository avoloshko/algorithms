package com.voloshko.algorithms.crypo;

import java.security.SecureRandom;
import java.util.*;

public class DiffieHellman {

    private int prime;
    private int num;
    private int secret;

    private DiffieHellman(int prime, int mod, int secret) {
        init(prime, mod, secret);
    }

    public DiffieHellman(int prime, int mod) {
        int secret = new Random().nextInt(mod - 2) + 2;
        init(prime, mod, secret);
    }

    public DiffieHellman(int prime) {
        int mod = new Random().nextInt(prime + 1) - 1;
        int secret = new Random().nextInt(mod - 2) + 2;
        init(prime, mod, secret);
    }

    public DiffieHellman() {
        int prime = randomPrime();
        int mod = new Random().nextInt(prime - 1) - 1;
        int secret = new Random().nextInt(mod - 2) + 2;
        init(prime, mod, secret);
    }

    private void init(int prime, int num, int secret) {
        if (!isPrime(prime)) {
            throw new IllegalArgumentException("A prime number is required");
        }
        this.prime = prime;

        if (num >= prime) {
            throw new IllegalArgumentException("mod must be less than the prime number");
        }
        this.num = num;

        if (secret >= prime) {
            throw new IllegalArgumentException("the secret must be less than the prime number");
        }
        this.secret = secret;
    }

    public int getPublic() {
        return pow(num, secret, prime);
    }

    public int getPrime() {
        return prime;
    }

    public int getNum() {
        return num;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;

        for (int i = (int) Math.sqrt(n); i >= 2; --i) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static NavigableSet<Integer> primeNumbersTo(int n) {
        if (n <= 2) {
            throw new IllegalArgumentException("A positive number required");
        }

        BitSet cache = new BitSet(n);
        cache.set(0);
        cache.set(1);

        for (int i = 2; i <= n; ++i) {
            if (cache.get(i)) {
                continue;
            }
            if (i > n / i) break;

            for (int j = i * i; j <= n; j += i) {
                cache.set(j);
            }
        }

        TreeSet<Integer> result = new TreeSet<>();

        for (int i = 0; i < n; ++i) {
            if (!cache.get(i)) {
                result.add(i);
            }
        }

        if (isPrime(n)) {
            result.add(n);
        }

        return result;
    }

    public static int randomPrime() {
        return randomPrime(Integer.MAX_VALUE / 100000);
    }

    public static int randomPrime(int max) {
        Set<Integer> primes = primeNumbersTo(max);
        int i = new SecureRandom().nextInt(primes.size());

        for (Integer prime : primes) {
            if (i-- == 0) {
                return prime;
            }
        }

        return -1;
    }

    int pow(int a, int n, int m) {
        if (n == 1) return a % m;

        int p = pow(a, n / 2, m);

        if (n % 2 == 0) {
            return (p * p) % m;
        }

        return ((p * p) % m * a) % m;
    }
}
