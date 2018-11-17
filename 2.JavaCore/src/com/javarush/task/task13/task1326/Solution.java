package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(reader.readLine())));

        ArrayList<Integer> arrayList = new ArrayList<>();

        while (bufferedReader.ready())
        {
            int a = Integer.parseInt(bufferedReader.readLine());
            int p = 0;
            if (a % 2 == 0) {
                /*for (int i = 0, n = arrayList.size(); i < n; i++) {
                    if (arrayList.get(i) >= a) {
                        p = i;
                        break;
                    }
                }*/
                arrayList.add(p, a);
            }
        }
        Collections.sort(arrayList);

        for (int i = 0, n = arrayList.size(); i < n; i++) {
            System.out.println(arrayList.get(i));
        }

        bufferedReader.close();
        reader.close();
        
    }
}
