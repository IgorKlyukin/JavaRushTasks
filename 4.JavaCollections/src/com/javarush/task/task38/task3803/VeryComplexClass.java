package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import java.util.List;

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object i = Integer.valueOf(42);
        String s = (String)i;
    }

    public void methodThrowsNullPointerException() {
        List list = null;
        list.size();
    }

    public static void main(String[] args) {

    }
}
