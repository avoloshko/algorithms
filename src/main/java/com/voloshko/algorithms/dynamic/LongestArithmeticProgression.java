package com.voloshko.algorithms.dynamic;

import java.util.*;

/**
 * @author avoloshko
 */


public class LongestArithmeticProgression {

    public static class Solution {

		/*

	static public int solve(final List<Integer> A) {

		int ans = 0;

		for (int i = 0; i < A.size(); ++i) {
			for (int j = i + 1; j < A.size(); ++j) {
				int diff = A.get(j) - A.get(i);
				int len = 2, prev = A.get(j);
				for (int k = j + 1; k < A.size(); ++k) {
					if (A.get(k) - prev == diff) {
						len += 1;
						prev = A.get(k);
					}
				}
				ans = Math.max(ans, len);
			}
		}

		return ans;
	}

	static public int solve2(final List<Integer> A) {
		int[][] mem = new int[A.size() - 1][A.size() - 1];

		for (int i = 0; i < A.size(); ++i) {
			for (int j = i + 1; j < A.size(); ++j) {
				mem[i][j] = 2;
				int diff = A.get(j) - A.get(i);
				int need = 2 * A.get(i) - A.get(j);

				int pos = -1;
				for (int k = 0; i < i - 1; ++i) {
					if (A.get(k) == need) {
						pos = k;
					}
				}
			}
		}

		return 0;
	}*/

        public ArrayList<Integer> slidingMaximum(final List<Integer> A, int B) {
            ArrayList<Integer> result = new ArrayList<>();
            if (A.size() == 0) {
                return new ArrayList<>(Collections.emptyList());
            } else if (A.size() == 1) {
                return new ArrayList<>(Collections.singletonList(A.stream().max(Comparator.naturalOrder()).get()));
            }

            int max = Integer.MIN_VALUE;
            for (int i = 0; i < B; ++i) {
                int left = A.get(i);
                max = Math.max(A.get(i), max);
            }
            //	int left = A.get()

            //int left = Integer.MIN_VALUE;


            for (int i = 0; i <= A.size() - B; ++i) {
                if ((i + 1) % B == 0) {

                }
            }

            return result;
        }


        public ArrayList<Integer> slidingMaximumR(final List<Integer> A, int B) {
            ArrayList<Integer> result = new ArrayList<>();

            for (int i = 0; i <= A.size() - B; ++i) {
                int max = Integer.MIN_VALUE;
                for (int j = i; j < B + i; ++j) {
                    max = Math.max(A.get(j), max);
                }
                result.add(max);
            }
            return result;
        }

        public ArrayList<Integer> slidingMaximumH(final List<Integer> H, int B) {
            ArrayList<Integer> result = new ArrayList<>();

            PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
            for (int i = 0; i < B; ++i) {
                queue.add(H.get(i));
            }

            result.add(queue.peek());
            int max = H.size();
            queue.remove(H.get(0));
            for (int i = B; i < max; ++i) {
                queue.add(H.get(i));
                result.add(queue.peek());
                queue.remove(H.get(i - B + 1));
            }
            return result;
        }

        public ArrayList<Integer> slidingMaximumQ(final List<Integer> a, int w) {
            ArrayList<Integer> ans = new ArrayList<Integer>();
            LinkedList<Integer> q = new LinkedList<Integer>();

            q.add(2);
            q.add(3);
            q.addFirst(4);
            q.addLast(1);

            for (int i = 0; i < a.size(); i++) {
                while (!q.isEmpty() && (q.peekFirst() + w <= i
                        || a.get(q.peekFirst()) <= a.get(i))) {
                    q.pollFirst();
                }
                while (!q.isEmpty() && (q.peekLast() + w <= i
                        || a.get(q.peekLast()) <= a.get(i))) {
                    q.pollLast();
                }
                q.addLast(i);
                if (i >= w - 1) {
                    ans.add(a.get(q.peekFirst()));
                }

            }
            return ans;
        }
    }

    // A Dequeue (Double ended queue) based method for printing maixmum element of
    // all subarrays of size k
    static void printMax(int arr[], int n, int k) {
        // Create a Double Ended Queue, Qi that will store indexes of array elements
        // The queue will store indexes of useful elements in every window and it will
        // maintain decreasing order of values from front to rear in Qi, i.e.,
        // arr[Qi.front[]] to arr[Qi.rear()] are sorted in decreasing order
        Deque<Integer> Qi = new LinkedList<Integer>();

        /* Process first k (or first window) elements of array */
        int i;
        for (i = 0; i < k; ++i) {
            // For very element, the previous smaller elements are useless so
            // remove them from Qi
            while (!Qi.isEmpty() && arr[i] >= arr[Qi.peekLast()])
                Qi.removeLast();   // Remove from rear

            // Add new element at rear of queue
            Qi.addLast(i);
        }

        // Process rest of the elements, i.e., from arr[k] to arr[n-1]
        for (; i < n; ++i) {
            // The element at the front of the queue is the largest element of
            // previous window, so print it
            System.out.print(arr[Qi.peek()] + " ");

            // Remove the elements which are out of this window
            while ((!Qi.isEmpty()) && Qi.peek() <= i - k)
                Qi.removeFirst();

            // Remove all elements smaller than the currently
            // being added element (remove useless elements)
            while ((!Qi.isEmpty()) && arr[i] >= arr[Qi.peekLast()])
                Qi.removeLast();


            // Add current element at the rear of Qi
            Qi.addLast(i);

        }

        // Print the maximum element of last window
        System.out.print(arr[Qi.peek()]);
    }


    static boolean isPrime(int a) {
        if (a <= 1) return false;
        int n = (int) Math.sqrt(a);
        for (int i = 2; i <= n; ++i) {
            if (a % i == 0) {
                return false;
            }
        }

        return true;
    }

    static Set<Integer> primeSeq(int a) {

        boolean[] np = new boolean[a + 1];
        np[0] = np[1] = true;

        for (int i = 2; i < np.length; ++i) {
            if (np[i]) {
                continue;
            }
            if (i > (np.length - 1) / i) {
                break;
            }

            int next = i * i;
            for (int j = next; j < np.length; j = j + i) {
                np[j] = true;
            }
        }

        Set<Integer> res = new TreeSet<>();
        for (int i = 2; i < np.length; ++i) {
            if (!np[i]) {
                res.add(i);
            }
        }
        return res;
    }

    static public int isPower(int N) {
        if (N == 1) return 1;

        int A = 2, P = 2;
        double DN = (double) N;
        double EPS = 0.000001;

        for (double i = P; ; i += 1.0) {
            double max = Math.pow(DN, 1.0 / i) + EPS;
            for (double j = A; ; j += 1.0) {
                if (j > max) {
                    if (j == A) {
                        return 0;
                    }
                    break;
                }
                if (Math.abs(Math.pow(j, i) - N) < EPS) {
                    return 1;
                }
            }
        }

    }

    static public ArrayList<Integer> primesum(int A) {
        Set<Integer> primes = primeSeq(A);

        ArrayList<Integer> result = new ArrayList<>(Arrays.asList(0, 0));
        for (Integer prime : primes) {
            if (primes.contains(A - prime)) {
                int min = Math.min(prime, A - prime),
                        max = A - min;

                if (result.get(0) == 0
                        || min < result.get(0)
                        || max < result.get(1)) {
                    result.set(0, min);
                    result.set(1, max);
                }
            }
        }
        return result;
    }

    static int hammingDistance(int A, int B) {
        int C = A ^ B;
        int s = 0;
        int a = 'A';
        for (int i = 1; ; i = i << 1) {
            if (i > C) {
                break;
            }
            if ((i & C) == i) {
                s += 1;
            }
            if (i == (1 << 31)) {
                break;
            }
        }
        return s;
    }

    static public int titleToNumber(String A) {
        final int n = 26;
        int res = 0;
        for (int i = A.length() - 1, k = 1; i >= 0; --i, k *= 26) {
            res += k * (A.charAt(i) - 64);
        }
        return res;
    }

    static public String convertToTitle(int A) {
        final int N = 26;
        String res = "";
        while (A > 0) {
            int T = (A - 1) % 26;
            char c = (char) (T + 65);
            res = c + res;
            A = (A - 1) / 26;
        }

        return res;
    }

    static public int solve(ArrayList<Integer> A, int B, int C) {

        return 0;
    }

    public static int maximumGap(final List<Integer> A) {

        if (A.size() == 1) {
            return 0;
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (Integer a : A) {
            if (a < min) {
                min = a;
            }
            if (a > max) {
                max = a;
            }
        }

        int length = max - min;
        if (length == 0) {
            return 0;
        }
        int bucketNum = A.size() - 1;
        int bucketSize = length / bucketNum;
        if (bucketSize == 0) {
            return 1;
        }
        if (length % bucketNum != 0) {
            bucketNum += ((length - bucketNum * bucketSize) + bucketSize) / 2;
        }

        int[][] buckets = new int[bucketNum][2];
        for (int[] bucket : buckets) {
            bucket[0] = Integer.MAX_VALUE;
            bucket[1] = Integer.MIN_VALUE;
        }

        for (Integer a : A) {
            int rel = a - min;
            int bucket = (rel - 1) / bucketSize;

            buckets[bucket][0] = Math.min(buckets[bucket][0], a);
            buckets[bucket][1] = Math.max(buckets[bucket][1], a);
        }

        int res = bucketSize;
        int left = Integer.MAX_VALUE;
        for (int i = 0; i < buckets.length; ++i) {
            int bmin = buckets[i][0];
            int bmax = buckets[i][1];
            if (bmin == Integer.MAX_VALUE) {
                continue;
            } else if (left != Integer.MAX_VALUE) {
                res = Math.max(res, bmin - left);
            }
            left = bmax;
        }

        return res;
    }

    static int countBits(int a) {
        int count = 0;
        while (a != 0) {
            if ((a & 1) != 0)
                count += 1;
            a = a >> 1;
        }
        return count;
    }

    static ArrayList<Integer> countingSortBit(ArrayList<Integer> array) {
        ArrayList<ArrayList<Integer>> sorting = new ArrayList<>();
        for (int i = 0; i < 32; ++i) {
            sorting.add(new ArrayList<>());
        }

        int setbitcount = 0;
        for (int i = 0; i < array.size(); i++) {
            setbitcount = countBits(array.get(i));
            sorting.get(setbitcount).add(array.get(i));
        }

        int j = 0;  // Used as an index in final sorted array
        // Traverse through all bit counts (Note that we
        // sort array in decreasing order)
        for (int i = 31; i >= 0; i--) {
            ArrayList<Integer> v1 = sorting.get(i);
            for (Integer integer : v1) {
                array.set(j ++, integer);
            }
        }

        return array;
    }

    public static void main(String[] args) {

        //System.out.println(multiply("253", "47"));
        //System.out.println(convertToTitle(943566));
        //System.out.println(primesum(28));

        int arr[] = {1, 2, 3, 4, 5};
        int k = 3;
        // printMax(arr, arr.length,k);

        //System.out.println(new Solution().slidingMaximumR(Arrays.asList(1, 3, -1, -3, 5, 3, 6, 7), 3));
        //System.out.println(new Solution().slidingMaximumQ(Arrays.asList(1, 3, -1, -3, 5, 3, 6, 7), 3));
    }
}
