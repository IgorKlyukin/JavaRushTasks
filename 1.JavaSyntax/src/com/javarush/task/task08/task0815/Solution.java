package com.javarush.task.task08.task0815;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* 
Перепись населения
*/

public class Solution {
    public static HashMap<String, String> createMap() {
        //напишите тут ваш код
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("Фамилия" + i, "Имя" + i % 3);
        }
        return (HashMap<String, String>)map;
    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name) {
        //напишите тут ваш код
        int i = 0;
        for (Map.Entry<String, String> entry:
                map.entrySet()) {
            if (entry.getValue().equals(name))
                i++;
        }
        return i;
    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String lastName) {
        //напишите тут ваш код
        int i = 0;
        for (Map.Entry<String, String> entry:
                map.entrySet()) {
            if (entry.getKey().equals(lastName))
                i++;
        }
        return i;
    }

    public static void main(String[] args) {

    }
}
