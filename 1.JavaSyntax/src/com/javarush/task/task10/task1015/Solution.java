package com.javarush.task.task10.task1015;

import java.util.ArrayList;

/* 
Массив списков строк
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<String>[] arrayOfStringList = createList();
        printList(arrayOfStringList);
    }

    public static ArrayList<String>[] createList() {
        ArrayList<String>[] arrayList = new ArrayList[5];
        for (int i = 0, n = arrayList.length; i < n; i++) {
            arrayList[i] = new ArrayList<>();
            for (int j = 0, m = n; j < m; j++) {
                arrayList[i].add("i + j = " + (i + j));
            }
        }

        return arrayList;
    }

    public static void printList(ArrayList<String>[] arrayOfStringList) {
        for (ArrayList<String> list : arrayOfStringList) {
            for (String s : list) {
                System.out.println(s);
            }
        }
    }
}