package com.javarush.task.task07.task0728;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* 
В убывающем порядке
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] array = new int[20];
        for (int i = 0; i < 20; i++) {
            array[i] = Integer.parseInt(reader.readLine());
        }

        sort(array);

        for (int x : array) {
            System.out.println(x);
        }
    }

    public static void sort(int[] array) {
        //напишите тут ваш код
        Arrays.sort(array);
        for (int i = 0, n = array.length; i < n / 2; i++) {
            array[i] ^= array[n - i - 1];
            array[n - i - 1] ^= array[i];
            array[i] ^= array[n - i - 1];
        }
    }
}
