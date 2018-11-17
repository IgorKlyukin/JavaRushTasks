package com.javarush.task.task09.task0919;

/* 
Деление на ноль
*/

import static com.sun.corba.se.impl.util.Utility.printStackTrace;

public class Solution {

    public static void main(String[] args) {
        try {
            divisionByZero();
        }
        catch(ArithmeticException e){
            e.printStackTrace();
        }
    }

    public static void divisionByZero() {
        int a = 0/0;
        System.out.println(a);
    }
}
