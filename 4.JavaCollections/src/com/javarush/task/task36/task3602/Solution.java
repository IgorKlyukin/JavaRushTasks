package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class clazz = Collections.class;
        Class[] classes = clazz.getDeclaredClasses();
        for (int i = 0; i < classes.length; i++) {
            if (List.class.isAssignableFrom(classes[i]) && Modifier.isPrivate(classes[i].getModifiers())) {
                try {
                    if (classes[i].getDeclaredConstructor().getParameterCount() == 0) {
                        return classes[i];
                    }
                } catch (NoSuchMethodException e) {}
            }
        }
        return null;
    }
}
