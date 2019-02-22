package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        String s = new String();
        if (reader != null) {
            StringBuilder stringBuilder = new StringBuilder();
            int n;
            while ( (n = reader.read()) > 0)
                stringBuilder.append((char)(n + key));
            s = stringBuilder.toString();
        }
        return s;
    }
}
