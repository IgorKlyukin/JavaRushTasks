package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        /*Map<String, String> map = new HashMap<>();
        map.put("name", "Ivanov");
        map.put("country", "Ukraine");
        map.put("city", "Kiev");
        map.put("age", null);
        System.out.println(map);
        System.out.println(getQuery(map));*/
    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        String format = "%s = '%s'";
        for (Map.Entry<String, String> entry :
                params.entrySet()) {
            if (entry.getValue() != null) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(" and ");
                }
                stringBuilder.append(String.format(format, entry.getKey(), entry.getValue()));
            }
        }
        return stringBuilder.toString();
    }
}
