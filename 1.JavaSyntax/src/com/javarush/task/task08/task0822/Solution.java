package com.javarush.task.task08.task0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Минимальное из N чисел
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        List<Integer> integerList = getIntegerList();
        System.out.println(getMinimum(integerList));
    }

    public static int getMinimum(List<Integer> array) {
        // Найти минимум тут
        if (array != null && array.size() > 0) {
            int min = array.get(0);
            for (int i :
                    array) {
                min = Integer.min(min, i);
            }
            return min;
        }
        return 0;
    }

    public static List<Integer> getIntegerList() throws IOException {
        // Создать и заполнить список тут
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0, n = Integer.parseInt(reader.readLine()); i < n; i++) {
            list.add(Integer.parseInt(reader.readLine()));
        }
        return list;
    }
}
