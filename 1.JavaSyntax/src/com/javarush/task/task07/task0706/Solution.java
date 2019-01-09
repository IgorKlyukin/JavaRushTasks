package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        int[] ints = new int[15];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 15; i++) {
            ints[i] = Integer.parseInt(reader.readLine());
        }
        int a = 0, b = 0;
        for (int i = 0; i < 15; i++) {
            if (i % 2 == 0) {
                a += ints[i];
            }
            else {
                b += ints[i];
            }
        }
        System.out.println(a < b ? "В домах с нечетными номерами проживает больше жителей." : "В домах с четными номерами проживает больше жителей.");
    }
}
