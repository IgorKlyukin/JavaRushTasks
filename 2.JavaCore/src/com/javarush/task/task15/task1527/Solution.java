package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        boolean flag = false;
        List<String> list = new ArrayList<>();
        String c = "";
        for (int i = 0, n = s.length(); i < n; i++) {
            if (s.charAt(i) == '?')
            {
                flag = true;
            }
            else if (flag && s.charAt(i) != '&' && s.charAt(i) != '=') {
                c += s.charAt(i);
            }
            else if (s.charAt(i) == '=') {
                flag = false;
                if (c.equals("obj")) {
                    list.add(c);
                    c = "";
                    flag = true;
                }
            }
            else if (s.charAt(i) == '&') {
                flag = true;
                list.add(c);
                c = "";
            }
            if (i == n - 1) {
                list.add(c);
            }
        }

        for (int i = 0, n = list.size(); i < n; i++) {
            System.out.print(list.get(i) + " ");
            if (list.get(i).equals("obj")) i++;
        }

        for (int i = 0, n = list.size(); i < n; i++) {
            if (list.get(i).equals("obj")) {
                System.out.println();
                try {
                    alert(Double.parseDouble(list.get(i + 1)));
                }
                catch (Exception e) {
                    alert(list.get(i + 1));
                }
            }
        }

    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
