package com.voloshko.algorithms.dynamic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Levenshtein distance: a string metric for measuring the difference between two sequences.
 * Computational complexity: O(nm)
 *
 * @author avoloshko
 */
public class Blockchain {

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


    public static void main(String[] args) {

        //System.out.println(new Test().maxNumber(new int[]{23, 76, 343, 75, 342, 43, 54, 86, 4, 34, 4, 75, 344, 74, 24, 35, 346, 436, 43, 34, 4, 6, 9, 5, 3, 234}));
        System.out.println(new Blockchain().IPaddresses(155315627));
        //new Test().substrings("three lions and three lionesses");
       // System.out.println(new Test().substrings("threelionsandthreelionesses"));
    }
}
