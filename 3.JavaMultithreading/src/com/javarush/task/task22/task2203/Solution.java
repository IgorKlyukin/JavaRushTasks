package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException{
        String str = null;
        try {
            String[] strs = string.split("\t");
            if (strs.length < 3) {
                throw  new Exception();
            }
            str = strs[1];
        }
        catch (Exception e){
            throw new TooShortStringException();
        }
        return str;
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
