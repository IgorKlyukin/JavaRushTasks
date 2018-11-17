package com.javarush.task.task04.task0427;

/* 
Описываем числа
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        String s ;
        if (a > 0 && a < 1000)
        {
            if (a % 2 == 0)
                s = "четное ";
            else
                s = "нечетное ";
            if (a < 10)
                s += "однозначное число";
            else if (a < 100)
                s += "двузначное число";
            else
                s += "трехзначное число";
            System.out.println(s);
        }

    }
}
