package com.javarush.task.task05.task0531;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Совершенствуем функциональность
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());
        int d = Integer.parseInt(reader.readLine());
        int i = Integer.parseInt(reader.readLine());

        int minimum = min(a, b, c, d, i);


        System.out.println("Minimum = " + minimum);
    }


    public static int min(int a, int b, int c, int d, int i) {
        int m;
        if (a < b)
            m = a;
        else
            m = b;
        if (c < m)
            m = c;
        if (d < m)
            m = d;
        if (i < m)
            m = i;
        return m;
    }
}
