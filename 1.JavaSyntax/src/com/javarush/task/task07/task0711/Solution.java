package com.javarush.task.task07.task0711;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Удалить и вставить
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        ArrayList<String> arrayList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            arrayList.add(reader.readLine());
        }
        for (int i = 0; i < 13; i++) {
            arrayList.add(0, arrayList.remove(4));
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(arrayList.get(i));
        }
    }
}
