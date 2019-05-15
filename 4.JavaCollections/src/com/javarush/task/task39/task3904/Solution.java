package com.javarush.task.task39.task3904;

import java.util.Arrays;

/* 
Лестница
*/
public class Solution {
    private static int n = 70;
    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if (n == 0)
            return 1;

        if (n < 0)
            return 0;

        long a1 = 0;
        long a2 = 0;
        long a3 = 1;

        long[] a = new long[n + 3];
        a[0] = 0;
        a[1] = 0;
        a[2] = 1;
        for (int i = 3; i < n + 3; i++) {
            a[i] = a[i - 1] + a[i - 2] + a[i - 3];
        }
        return a[n + 2];
    }
}

