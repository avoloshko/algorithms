package com.voloshko.algorithms.dynamic;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Levenshtein distance: a string metric for measuring the difference between two sequences.
 * Computational complexity: O(nm)
 *
 * @author avoloshko
 */
public class Test {

    public int findDistance(int rank) {
        return 0;
    }

    static int substrings(String input) {
        //new Test().substrings("three lions and three lionesses");
        int res = 0;
        input = input.replaceAll(" ", "");

        Map<String, Integer> repeat = new HashMap<>();

        for (int i = 0; i < input.length(); ++i) {
            for (int j = i + 1; j < input.length(); ++j) {
                char c1 = input.charAt(i);
                char c2 = input.charAt(j);
                if (c1 == c2) {
                    int s = 1;
                    for (int k1 = i + 1, k2 = j + 1; k1 < input.length() &&
                            k2 < input.length(); ++k1, ++k2) {
                        if (input.charAt(k1) != input.charAt(k2)) {
                            break;
                        }
                        s += 1;
                        if (s > 4) {
                            break;
                        }
                    }
                    if (s == 4) {
                        String str = input.substring(i, i + 4);
                        Integer prev = repeat.get(str);
                        if (prev != null) {
                            repeat.put(str, prev + 1);
                        } else {
                            repeat.put(str, 1);
                        }
                    }
                }
            }
        }

        int sum = 0;
        for (Integer integer : repeat.values()) {
            sum += integer;
        }

        return sum;
    }

    static String reproduceString(String input) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);
            switch (c) {
                case 'A':
                case 'a':
                case 'E':
                case 'e':
                case 'I':
                case 'i':
                case 'O':
                case 'o':
                case 'U':
                case 'u':
                case ' ':
                    break;
                default:
                    result.append(c);
            }
        }

        if (result.length() == 0) {
            return "-";
        }

        return result.toString();
    }

    static int IPaddresses(int input) {

        while (input < 0) {

        }

        return 0;
    }

    static long maxNumber(int[] input) {
        if (input.length == 0) {
            return 0;
        }

        String result = "";
        ArrayList<String> list = new ArrayList<>();
        for (int i : input) {
            list.add(Integer.toString(i));
        }

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                int min = Math.min(o1.length(), o2.length());
                for (int i = 0; i < min; ++i) {
                    char ch1 = i < o1.length() ? o1.charAt(i) : 47;
                    char ch2 = i < o2.length() ? o2.charAt(i) : 47;
                    if (ch1 != ch2) {
                        return ch2 - ch1;
                    }
                }
                return 0;
            }
        });

        for (String s : list) {
            result += s;
        }

        return Long.parseLong(result);
    }

    static public int removeDuplicates(ArrayList<Integer> a) {
        if (a.size() == 0) return 0;
        int i = 0, j = 1, s = 0, k = 1;
        while (j < a.size()) {
            if (!a.get(i).equals(a.get(j))) {
                a.set(i + 1, a.get(j));
                i += 1;
                k = 1;
            } else if (k > 0) {
                a.set(i + 1, a.get(j));
                i += 1;
                k -= 1;
            } else {
                s += 1;
            }
            j += 1;
        }
        return a.size() - s;
    }

    static class Character implements Comparable<Character> {

        public static final Character NONE = new Character('\0', -1);

        Character(char ch, int index) {
            this.ch = ch;
            this.index = index;
        }

        @Override
        public int compareTo(@NotNull Character o) {
            return ch - o.ch;
        }

        char ch;
        int index;
    }

    static public int anytwo(String A) {
        List<Character> arr = IntStream.range(0, A.length())
                .mapToObj(x -> new Character(A.charAt(x), x))
                .sorted()
                .collect(Collectors.toList());
        Map<java.lang.Character, List<Integer>> map = new HashMap<>();
        for (Character ch : arr) {
            List<Integer> indexes = map.computeIfAbsent(ch.ch, k -> new ArrayList<>());
            indexes.add(ch.index);
        }
        map = map.entrySet().stream().filter(x -> x.getValue().size() > 1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        List<List<Integer>> il = new ArrayList<>(map.values());
        for (int i = 0; i < il.size(); ++i) {
            if (il.get(i).stream().mapToInt(x -> x).sum() > 3) {
                return 1;
            }
            for (int j = i + 1; j < il.size(); ++j) {
                if (anytwo(il.get(i), il.get(j))) {
                    return 1;
                }
            }
        }


        return 0;
    }

    private static boolean anytwo(List<Integer> l1, List<Integer> l2) {
        int abab = 0;
        int aabb = 0;
        int aaaa = 0;
        Boolean prevLeft = null;
        int i = 0, j = 0;
        while (true) {
            int ch1 = i < l1.size() ? l1.get(i) : Integer.MAX_VALUE;
            int ch2 = j < l2.size() ? l2.get(j) : Integer.MAX_VALUE;
            if (ch1 == ch2) {
                break;
            }
            boolean left = ch1 < ch2;
            // abab
            if (prevLeft != null) {
                if (left != prevLeft) {
                    abab += 1;
                    if (abab == 3) {
                        return true;
                    }

                    if (aabb == 1) {
                        aabb = 2;
                    }

                } else {
                    if (aabb == 0
                            || aabb == 2) {
                        aabb += 1;
                    }
                    if (aabb == 3) {
                        return true;
                    }
                }
            }
            prevLeft = left;

            if (left) {
                i += 1;
            } else {
                j += 1;
            }
        }

        return false;
    }

    public static class Solution {
        // DO NOT MODIFY THE LIST. IT IS READ ONLY
        public ArrayList<ArrayList<Integer>> anagrams(final List<String> A) {
            Integer[] indices = new Integer[A.size()];
            for (int i = 0; i < indices.length; i++) {
                indices[i] = i;
            }
            Integer[] val = new Integer[A.size()];
            for (int i = 0; i < indices.length; i++) {
                char[] chs = A.get(i).toCharArray();
                Arrays.sort(chs);
                int n = 0;
                for (int j = 0; j < chs.length; j++) {
                    j]
                }
            }
            /*Arrays.sort(indices, new Comparator<Integer>() {
                public int compare(Integer i1, Integer i2) {
                    return A.get(i1).length() - A.get(i2).length();
                }
            });*/


            return new ArrayList<>();
        }
    }


    public static void main(String[] args) throws Exception {

        System.out.println(new Solution().anagrams(Arrays.asList("cat", "dog", "god", "tca")));

        /*Merkle merkle = new Merkle(Merkle.HashType.SHA256);
        merkle.makeTree(new ArrayList<>(Arrays.asList(new byte[] {1, 2 ,3}, new byte[] {4, 5 ,6}, new byte[] {7, 8 ,9})));

        merkle.any(new byte[] {1, 2, 3});*/

        System.out.println(anytwo("aaaa"));

        removeDuplicates(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3)));
        synchronized (args) {
            args.notifyAll();
        }

        //System.out.println(new Test().maxNumber(new int[]{23, 76, 343, 75, 342, 43, 54, 86, 4, 34, 4, 75, 344, 74, 24, 35, 346, 436, 43, 34, 4, 6, 9, 5, 3, 234}));
        System.out.println(new Test().IPaddresses(155315627));
        //new Test().substrings("three lions and three lionesses");
        // System.out.println(new Test().substrings("threelionsandthreelionesses"));
    }
}
