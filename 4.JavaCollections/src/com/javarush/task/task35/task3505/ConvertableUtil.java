package com.javarush.task.task35.task3505;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <T, V extends Convertable<T>> Map convert(List<? extends Convertable<T>> list) {
        Map<T, Convertable<T>> result = new HashMap<>();
        for (int i = 0, n = list.size(); i < n; i++) {
            result.put(list.get(i).getKey(), list.get(i));
        }
        return result;
    }
}
