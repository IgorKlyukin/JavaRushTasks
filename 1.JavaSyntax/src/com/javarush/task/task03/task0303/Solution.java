package com.javarush.task.task03.task0303;

/* 
Обмен валют
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(convertEurToUsd(70, 0.7));
        System.out.println(convertEurToUsd(70, 0.8));

    }

    public static double convertEurToUsd(int eur, double course) {
        double d = eur * course;
        return  d;
    }
}
