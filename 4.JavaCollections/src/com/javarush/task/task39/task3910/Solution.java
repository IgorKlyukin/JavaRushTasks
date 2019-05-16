package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(0));
        System.out.println(isPowerOfThree(1));
        System.out.println(isPowerOfThree(3));
        System.out.println(isPowerOfThree(9));
        System.out.println(isPowerOfThree(27));
        System.out.println(isPowerOfThree(30));
    }

    public static boolean isPowerOfThree(int n) {
        if (Math.log(n) / Math.log(3) % 1 == 0) {
            return true;
        }
        return false;
    }
}
