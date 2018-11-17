package com.javarush.task.task15.task1516;

/* 
Значения по умолчанию
*/

public class Solution {
    int intVar;
    double doubleVar;
    Double DoubleVar;
    boolean booleanVar;
    Object ObjectVar;
    Exception ExceptionVar;
    String StringVar;

    public static void main(String[] args) {
        System.out.println(new Solution().intVar);
        System.out.println(new Solution().doubleVar);
        System.out.println(new Solution().DoubleVar);
        System.out.println(new Solution().booleanVar);
        System.out.println(new Solution().ObjectVar);
        System.out.println(new Solution().ExceptionVar);
        System.out.println(new Solution().StringVar);
    }
}
