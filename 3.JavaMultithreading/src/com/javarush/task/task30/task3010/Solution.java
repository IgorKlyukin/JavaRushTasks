package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        try {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                s.append(args[i]);
            }
            String string = s.toString();
            for (int i = 2; i < 37; i++) {
                try {
                    BigInteger bigInteger = new BigInteger(string, i);
                    System.out.println(i);
                    return;
                } catch (NumberFormatException e) {
                }
            }
            System.out.println("incorrect");
        }
        catch (Exception e){}
    }
}