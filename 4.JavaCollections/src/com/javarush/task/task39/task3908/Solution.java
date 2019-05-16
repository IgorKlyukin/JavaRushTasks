package com.javarush.task.task39.task3908;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("лазерборехеробрезал"));
    }

    public static boolean isPalindromePermutation(String s) {
        if (s == null || s.length() == 0)
            return true;

        String s1 = s.toLowerCase().replaceAll(" ", "");
        Map<Character, Integer> map = new HashMap<>();
        for (char c :
                s1.toCharArray()) {
            if (map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                map.put(c, 1);
        }

        int t = 0;
        for (Integer i :
                map.values()) {
            if (i % 2 == 1)
                t++;
        }

        return t < 2;
    }
}
