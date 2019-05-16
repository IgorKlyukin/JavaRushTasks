package com.javarush.task.task39.task3909;

import java.util.Arrays;
import java.util.Collections;

/*
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {
    }

    public static boolean isOneEditAway(String first, String second) {
        if (first == null || second == null)
            return false;

        if (first.equalsIgnoreCase(second))
            return true;

        int[] Di_1 = new int[second.length() + 1];
        int[] Di = new int[second.length() + 1];

        for (int j = 0; j <= second.length(); j++) {
            Di[j] = j; // (i == 0)
        }

        for (int i = 1; i <= first.length(); i++) {
            System.arraycopy(Di, 0, Di_1, 0, Di_1.length);

            Di[0] = i; // (j == 0)
            for (int j = 1; j <= second.length(); j++) {
                int cost = (first.charAt(i - 1) != second.charAt(j - 1)) ? 1 : 0;
                Di[j] = Collections.min(Arrays.asList(
                        Di_1[j] + 1,
                        Di[j - 1] + 1,
                        Di_1[j - 1] + cost
                ));
            }
        }
        return Di[Di.length - 1] == 1;
    }
}
