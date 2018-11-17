package com.javarush.task.task10.task1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Количество букв
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // алфавит
        String abc = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        char[] abcArray = abc.toCharArray();

        ArrayList<Character> alphabet = new ArrayList<Character>();
        for (int i = 0; i < abcArray.length; i++) {
            alphabet.add(abcArray[i]);
        }

        // ввод строк
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            String s = reader.readLine();
            list.add(s.toLowerCase());
        }

        int [] a = new int [alphabet.size()];
        String s;
        for (int i = 0, n = list.size(); i < n; i++) {
            s = list.get(i);
            for (int j = 0, m = s.length(); j < m; j++) {
                for (int k = 0, l = alphabet.size(); k < l; k++) {
                    if (s.charAt(j) == alphabet.get(k)){
                        a[k] ++;
                        break;
                    }
                }
            }
        }
        for (int i = 0, n = alphabet.size(); i < n; i++) {
            System.out.println(alphabet.get(i) + " " + a[i]);
        }
    }

}
