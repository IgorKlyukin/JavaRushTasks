package com.javarush.task.task07.task0715;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Продолжаем мыть раму
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("мама");
        arrayList.add("мыла");
        arrayList.add("раму");
        arrayList.add(3,"именно");
        arrayList.add(2,"именно");
        arrayList.add(1,"именно");
        for (String s :
                arrayList) {
            System.out.println(s);
        }
    }
}
