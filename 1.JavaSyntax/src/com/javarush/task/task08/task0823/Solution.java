package com.javarush.task.task08.task0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

/* 
Омовение Рамы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();

        //напишите тут ваш код
        StringBuilder stringBuilder = new StringBuilder();
        for (String str :
                s.split("\\s+")) {
            if (str.length() > 0)
                stringBuilder.append(str.substring(0, 1).toUpperCase()).append(str.substring(1)).append(" ");
        }
        System.out.println(stringBuilder.toString().trim());
    }
}
