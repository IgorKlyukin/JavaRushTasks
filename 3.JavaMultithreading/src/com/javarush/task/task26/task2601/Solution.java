package com.javarush.task.task26.task2601;

import java.util.*;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        List<Integer> arrayList = Arrays.asList(array);
        Collections.sort(arrayList);

        int size = arrayList.size();
        Integer medium;

        if (size % 2 == 0){
            medium = (arrayList.get(size / 2 - 1) + arrayList.get(size / 2)) / 2;
        }
        else {
            medium = arrayList.get(size / 2);
        }

        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(medium - o1) - Math.abs(medium - o2);
            }
        };

        Collections.sort(arrayList, comparator);

        array = (Integer[]) arrayList.toArray();
        return array;
    }
}
