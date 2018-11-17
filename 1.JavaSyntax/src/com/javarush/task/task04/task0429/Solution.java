package com.javarush.task.task04.task0429;

/* 
Положительные и отрицательные числа
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());

        int n = 0, m = 0;
        if (a > 0)
            n++;
        else if (a < 0)
            m++;
        if (b > 0)
            n++;
        else if (b < 0)
            m++;
        if (c > 0)
            n++;
        else if (c < 0)
            m++;
        System.out.println("количество отрицательных чисел: " + m);
        System.out.println("количество положительных чисел: " + n);
    }
}
