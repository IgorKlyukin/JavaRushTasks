package com.javarush.task.task12.task1233;

/* 
Изоморфы наступают
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int[] data = new int[]{1, 2, 3, 5, -2, -8, 0, 77, 5, 5};

        Pair<Integer, Integer> result = getMinimumAndIndex(data);

        System.out.println("Minimum is " + result.x);
        System.out.println("Index of minimum element is " + result.y);
    }

    public static Pair<Integer, Integer> getMinimumAndIndex(int[] array) {
        if (array == null || array.length == 0) {
            return new Pair<Integer, Integer>(null, null);
        }
        Pair<Integer, Integer> pair = new Pair<>(array[0],0);
        for (int i = 1, n = array.length; i < n; i++) {
            if (pair.x > array[i]) {
                pair.x = array[i];
                pair.y = i;
            }
        }

        return pair;
    }


    public static class Pair<X, Y> {
        public X x;
        public Y y;

        public Pair(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }
}
