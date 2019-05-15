package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.equals(""))
            return 0;

        ArrayList<String> list = new ArrayList<>();
        int t = 0;
        int max = 0;
        for (int i = 0, n = s.length(); i < n; i++) {
            for (int m = list.size(), j = m - 1; j > -1; j--)
                if (list.get(j).contains("" + s.charAt(i))) {
                    if (j == m - 1)
                        t = i - (list.get(j).length() - list.get(j).indexOf(s.charAt(i)) - 1);
                    break;
                }
            list.add(s.substring(t, i + 1));
            if (list.get(i).length() > max)
                max = list.get(i).length();
        }
        return max;
    }
}
