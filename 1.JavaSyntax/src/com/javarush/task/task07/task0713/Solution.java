package com.javarush.task.task07.task0713;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Играем в Jолушку
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        ArrayList<Integer> listAll = new ArrayList<>();
        ArrayList<Integer> list3 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 20; i++) {
            listAll.add(Integer.parseInt(reader.readLine()));
        }
        for (int i = 0; i < 20; i++) {
            if (listAll.get(i) % 3 == 0) {
                list3.add(listAll.get(i));
            }
            if (listAll.get(i) % 2 == 0) {
                list2.add(listAll.get(i));
            }
            if (listAll.get(i) % 2 != 0 && listAll.get(i) % 3 != 0) {
                list.add(listAll.get(i));
            }
        }
        printList(list3);
        printList(list2);
        printList(list);
    }

    public static void printList(List<Integer> list) {
        //напишите тут ваш код
        for (int i :
                list) {
            System.out.println(i);
        }
    }
}
