package com.javarush.task.task08.task0817;

import java.util.HashMap;
import java.util.Map;

/* 
Нам повторы не нужны
*/

public class Solution {
    public static HashMap<String, String> createMap() {
        //напишите тут ваш код
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("фамилия" + i, "имя" + i % 6);
        }
        return (HashMap<String, String>)map;
    }

    public static void removeTheFirstNameDuplicates(Map<String, String> map) {
        //напишите тут ваш код
        Map<String, String> mapTemp = new HashMap<>(map);
        for (Map.Entry<String, String> entry :
                mapTemp.entrySet()) {
            String key = entry.getKey();
            if (map.containsValue(entry.getValue())) {
                String s = map.remove(entry.getKey());
                if (map.containsValue(s))
                    removeItemFromMapByValue(map, s);
                else
                    map.put(key, s);
            }
        }

    }

    public static void removeItemFromMapByValue(Map<String, String> map, String value) {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }

    public static void main(String[] args) {

    }
}
