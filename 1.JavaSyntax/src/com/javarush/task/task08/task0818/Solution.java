package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        //напишите тут ваш код
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("фамилия" + i, (225 * i) % 750);
        }
        return (HashMap<String, Integer>) map;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        //напишите тут ваш код
        HashMap<String, Integer> mapZP = new HashMap<>(map);
        for (Map.Entry<String, Integer> entry :
                mapZP.entrySet()) {
            if (entry.getValue() < 500)
                map.remove(entry.getKey());
        }
    }

    public static void main(String[] args) {

    }
}