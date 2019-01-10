package com.javarush.task.task07.task0722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Это конец
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //напишите тут ваш код
        ArrayList<String> arrayList = new ArrayList<>();
        String s;
        while (!(s = reader.readLine()).equals("end")) {
            arrayList.add(s);
        }
        for (String str : arrayList)
                System.out.println(str);
    }
}