package com.javarush.task.task07.task0718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Проверка на упорядоченность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            arrayList.add(reader.readLine());
        for (int i = 0; i < 9;)
            if (arrayList.get(i++).length() > arrayList.get(i).length()) {
                System.out.println(i);
                break;
            }
    }
}

