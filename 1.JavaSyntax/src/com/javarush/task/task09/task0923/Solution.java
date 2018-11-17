package com.javarush.task.task09.task0923;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Гласные и согласные
*/

public class Solution {
    public static char[] vowels = new char[]{'а', 'я', 'у', 'ю', 'и', 'ы', 'э', 'е', 'о', 'ё'};

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s0 = "",s = reader.readLine();
        for (int i = 0, n = s.length(); i < n; i++) {
            if (isVowel(s.charAt(i))){
                System.out.print(s.charAt(i) + " ");
            }
            else if (!(s.charAt(i) == ' ')){
                s0 += s.charAt(i);
                s0 += " ";
            }
        }
        System.out.println();
        System.out.println(s0);
    }

    // метод проверяет, гласная ли буква
    public static boolean isVowel(char c) {
        c = Character.toLowerCase(c);  // приводим символ в нижний регистр - от заглавных к строчным буквам

        for (char d : vowels)   // ищем среди массива гласных
        {
            if (c == d)
                return true;
        }
        return false;
    }
}