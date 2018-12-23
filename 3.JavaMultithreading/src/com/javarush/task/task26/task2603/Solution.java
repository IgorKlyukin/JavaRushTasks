package com.javarush.task.task26.task2603;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Убежденному убеждать других не трудно
*/
public class Solution {

    public static void main(String[] args) {

    }

    public static class CustomizedComparator<T> implements Comparator<T>{
        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator<T> ... vararg) {
            this.comparators = vararg;
        }

        @Override
        public int compare(T t1, T t2) {
            for (Comparator<T> comparator :
                    comparators) {
                int result;
                if ((result = comparator.compare(t1, t2)) != 0) {
                    return result;
                }
            }
            return 0;
        }
    }
}
