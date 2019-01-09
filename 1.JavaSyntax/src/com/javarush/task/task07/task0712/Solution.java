package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        ArrayList<String> arrayList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            arrayList.add(reader.readLine());
        }
        int Max = arrayList.get(0).length();
        int Min = arrayList.get(0).length();

        for (String s :
                arrayList) {
            if (s.length() > Max) {
                Max = s.length();
            }
            else if (s.length() < Min) {
                Min = s.length();
            }
        }

        for (int i = 0; i < 10; i++) {
            if (arrayList.get(i).length() == Max || arrayList.get(i).length() == Min) {
                System.out.println(arrayList.get(i));
                break;
            }
        }

    }
}
