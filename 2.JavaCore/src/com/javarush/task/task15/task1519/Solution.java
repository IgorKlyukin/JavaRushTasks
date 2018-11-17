package com.javarush.task.task15.task1519;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.BufferOverflowException;

/* 
Разные методы для разных типов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (true) {
            if ((s = reader.readLine()).equals("exit")) {
                break;
            }

            while (true) {
                if (s.contains(".")) {
                    try {
                        print((Double) Double.parseDouble(s));
                        break;
                    } catch (Exception e) {
                    }
                }
                try {
                    if (Integer.parseInt(s) > 0 && Integer.parseInt(s) < 128) {
                        print((short) Integer.parseInt(s));
                        break;
                    } else if (Integer.parseInt(s) <= 0 || Integer.parseInt(s) >= 128) {
                        print((Integer) Integer.parseInt(s));
                        break;
                    }
                } catch (Exception e) {
                }
                print(s);
                break;
            }
        }
    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
