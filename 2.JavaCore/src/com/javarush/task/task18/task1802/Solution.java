package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream file = new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine());
        int min = 255;
        while (file.available() > 0){
            int a = file.read();
            if (min > a) {
                min = a;
            }
        }
        System.out.println(min);
        file.close();
    }
}
