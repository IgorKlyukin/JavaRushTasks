package com.javarush.task.task10.task1016;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* 
Одинаковые слова в списке
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> words = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            words.add(reader.readLine());
        }

        Map<String, Integer> map = countWords(words);

        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            System.out.println(pair.getKey() + " " + pair.getValue());
        }
    }

    public static Map<String, Integer> countWords(ArrayList<String> list) {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        boolean flag = true;
        result.put(list.get(0),1);

        for (int i = 1, n = list.size(); i < n; i++) {
            flag = true;
            for (Map.Entry<String, Integer> pair : result.entrySet()) {
                if (list.get(i).equals(pair.getKey())) {
                    pair.setValue(pair.getValue() + 1);
                    flag = false; break;
                }
            }
            if (flag){
                result.put(list.get(i),1);
            }
        }


        return result;
    }

}

