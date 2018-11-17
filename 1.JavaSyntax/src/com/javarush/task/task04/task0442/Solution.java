package com.javarush.task.task04.task0442;


/* 
Суммирование
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;
/*
        for (int a = Integer.parseInt(reader.readLine()); a != -1; a = Integer.parseInt(reader.readLine()))
        {
            sum = sum + a;
        }
        sum = sum - 1;
        System.out.println(sum);
*/
        int a = Integer.parseInt(reader.readLine());
        while (a != -1)
        {
            sum = sum + a;
            a = Integer.parseInt(reader.readLine());
        }
        sum = sum - 1;
        System.out.println(sum);
/*
        while ((a = Integer.parseInt(reader.readLine())) != -1)
        {
            sum += a;
        }
        sum += a;
        System.out.println(sum);*/
    }
}
