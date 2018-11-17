package com.javarush.task.task18.task1801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream file = new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine());
        int max = 0;
        while (file.available() > 0) {
            int a = file.read();
            if (max < a) {max = a;}
        }
        System.out.println(max);
        file.close();

    }
}
