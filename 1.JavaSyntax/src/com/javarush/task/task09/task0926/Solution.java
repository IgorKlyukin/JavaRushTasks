package com.javarush.task.task09.task0926;

import java.util.ArrayList;

/* 
Список из массивов чисел
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<int[]> list = createList();
        printList(list);
    }

    public static ArrayList<int[]> createList() {
        ArrayList<int[]> arrayList = new ArrayList<>();
        int a[]={5,2,4,7,0};

        for (int i = 0; i < a.length; i++) {
            arrayList.add(new int [a[i]]);
        }

        for (int[] array : arrayList) {
            for (int i = 0; i < array.length; i++) {
                array[i] = i + array.length;
            }
        }

        return  arrayList;
    }

    public static void printList(ArrayList<int[]> list) {
        for (int[] array : list) {
            for (int x : array) {
                System.out.println(x);
            }
        }
    }
}
