package com.javarush.task.task04.task0416;

/* 
Переходим дорогу вслепую
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        float a = Float.parseFloat(reader.readLine());
        a = a % 5;
        if (a < 3)
            System.out.println("зелёный");
        else if (a < 4)
            System.out.println("жёлтый");
        else
            System.out.println("красный");
    }
}