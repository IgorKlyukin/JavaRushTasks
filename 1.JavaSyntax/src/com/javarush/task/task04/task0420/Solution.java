package com.javarush.task.task04.task0420;

/* 
Сортировка трех чисел
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());

        if (a >= b && a >= c)
        {
            System.out.print(a + " ");
            if (b >= c)
                System.out.println(b + " " + c);
            else
                System.out.println(c + " " + b);
        }
        else if (b >= a && b >= c)
        {
            System.out.print(b + " ");
            if (a >= c)
                System.out.println(a + " " + c);
            else
                System.out.println(c + " " + a);
        }
        else if (c >= a && c >= b)
        {
            System.out.print(c + " ");
            if (a >= b)
                System.out.println(a + " " + b);
            else
                System.out.println(b + " " + a);
        }
    }
}
