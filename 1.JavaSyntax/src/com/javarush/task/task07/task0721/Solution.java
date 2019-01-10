package com.javarush.task.task07.task0721;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Минимаксы в массивах
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int maximum;
        int minimum;

        //напишите тут ваш код
        int[] arrayList = new int[20];
        for (int i = 0; i < 20; i++)
            arrayList[i] = Integer.parseInt(reader.readLine());

        maximum = arrayList[0];
        minimum = arrayList[0];
        for (int i = 0; i < 20; i++) {
            maximum = Integer.max(maximum, arrayList[i]);
            minimum = Integer.min(minimum, arrayList[i]);
        }
        System.out.print(maximum + " " + minimum);
    }
}
