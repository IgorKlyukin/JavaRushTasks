package com.javarush.task.task09.task0930;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/* 
Задача по алгоритмам
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<String>();
        while (true) {
            String s = reader.readLine();
            if (s.isEmpty()) break;
            list.add(s);
        }

        String[] array = list.toArray(new String[list.size()]);
        sort(array);

        for (String x : array) {
            System.out.println(x);
        }
    }

    public static void sort(String[] array) {
        int i1, i2;
        String s;
        int a;
        for (int i = 0, n = array.length; i < n - 1; i++) {
            i1 = -1;
            i2 = -1;
            for (int j = 0; j < n - i; j++) {
                if (isNumber(array[j])){
                    if (i1 == -1){
                        i1 = j;
                    }
                    else {
                        if (Integer.parseInt(array[i1]) < Integer.parseInt(array[j])){
                            a = Integer.parseInt(array[j]);
                            array[j] = array[i1];
                            array[i1] = "" + a;
                        }
                        i1 = j;
                    }
                }
                else {
                    if (i2 == -1){
                        i2 = j;
                    }
                    else {
                        if (isGreaterThan(array[i2], array[j])){
                            s = array[j];
                            array[j] = array[i2];
                            array[i2] = s;
                        }
                        i2 = j;
                    }
                }
            }
        }
    }

    // Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }


    // Переданная строка - это число?
    public static boolean isNumber(String s) {
        if (s.length() == 0) return false;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ((i != 0 && c == '-') // есть '-' внутри строки
                    || (!Character.isDigit(c) && c != '-') // не цифра и не начинается с '-'
                    || (i == 0 && c == '-' && chars.length == 1)) // не '-'
            {
                return false;
            }
        }
        return true;
    }
}
