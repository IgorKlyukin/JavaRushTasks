package com.javarush.task.task07.task0705;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Один большой массив и два маленьких
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        int[] intMax = new int[20];
        int[] intMin1 = new int[10];
        int[] intMin2 = new int[10];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < intMax.length; i++) {
            intMax[i] = Integer.parseInt(reader.readLine());
        }
        for (int i = 0; i < 10; i++) {
            intMin1[i] = intMax[i];
            intMin2[i] = intMax[i + 10];
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(intMin2[i]);
        }
    }
}
