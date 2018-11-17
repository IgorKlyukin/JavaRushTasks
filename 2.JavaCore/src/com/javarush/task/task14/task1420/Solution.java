package com.javarush.task.task14.task1420;

/* 
НОД
*/

import com.sun.org.apache.xerces.internal.impl.dv.dtd.NOTATIONDatatypeValidator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int a = Integer.parseUnsignedInt(reader.readLine());
            int b = Integer.parseUnsignedInt(reader.readLine());
            NOD((a < b ? a: b), (a > b ? a: b));
    }
    public static void NOD(int a, int b)
    {
        int c = a;
        while (true) {
            if (b % c == 0) {
                if (a % c == 0) {
                    System.out.println(c);
                    break;
                }
            }
            c --;
        }
    }
}
