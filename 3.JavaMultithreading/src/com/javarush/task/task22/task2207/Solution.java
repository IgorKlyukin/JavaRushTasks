package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(reader.readLine())));
        reader.close();

        List<String> list = new ArrayList<>();
        while (file.ready()){
            String[] strings = file.readLine().split(" ");
            list.addAll(Arrays.asList(strings));
        }
        for (String s :
                list) {
            String s1 = new StringBuilder(s).reverse().toString();
            if (list.contains(s1) && list.indexOf(s) != list.lastIndexOf(s1)) {
                Pair pair = new Pair();
                pair.first = s;
                pair.second = s1;
                Pair pair1 = new Pair();
                pair1.first = s1;
                pair1.second = s;
                if (!result.contains(pair) && !result.contains(pair1)) {
                    result.add(pair);
                }
            }
        }
        System.out.println(result);
    }
    //C:\input.txt

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
