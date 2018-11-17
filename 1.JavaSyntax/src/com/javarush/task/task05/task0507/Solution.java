package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        float a = 0, b = 0, c;
        //for (int i = Integer.parseInt(reader.readLine()); i != -1; i = Integer.parseInt(reader.readLine()))
        int i;
        while ((i = Integer.parseInt(reader.readLine())) != -1)
        {
            a += i;
            b++;
        }
        if (b != 0){
            c = a / b;
            System.out.println(c);
        }
    }
}

