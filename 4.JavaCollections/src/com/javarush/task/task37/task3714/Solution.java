package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.NavigableMap;
import java.util.TreeMap;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        if (s.matches("^(M{0,3})(D?C{0,3}|C[DM])(L?X{0,3}|X[LC])(V?I{0,3}|I[VX])$")) {
            int sum = 0;
            for (int i = 0, n = s.length(); i < n; i++)
                sum += units.get(s.substring(i,
                        i + 1 < n && units.containsKey(s.substring(i, i + 2)) ?
                                i++ + 2 :
                                i + 1));
            return sum;
        }
        return 0;
    }



    private static final NavigableMap<String, Integer> units;
    static {
        NavigableMap<String, Integer> initMap = new TreeMap<>();
        initMap.put("M", 1000);
        initMap.put("CM", 900);
        initMap.put("D", 500);
        initMap.put("CD", 400);
        initMap.put("C", 100);
        initMap.put("XC", 90);
        initMap.put("L", 50);
        initMap.put("XL", 40);
        initMap.put("X", 10);
        initMap.put("IX", 9);
        initMap.put("V", 5);
        initMap.put("IV", 4);
        initMap.put("I", 1);
        units = Collections.unmodifiableNavigableMap(initMap);
    }
}
