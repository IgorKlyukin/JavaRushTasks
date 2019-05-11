package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        PrepareMyTest annotation;
        if ((annotation = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class)) != null) {
            for (String s :
                    annotation.fullyQualifiedNames()) {
                System.out.println(s);
            }

            return true;
        }
        return false;
    }

    public static boolean printValues(Class c) {
        PrepareMyTest annotation;
        if ((annotation = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class)) != null) {
            for (Class clazz :
                    annotation.value()) {
                System.out.println(clazz.getSimpleName());
            }

            return true;
        }
        return false;
    }
}
