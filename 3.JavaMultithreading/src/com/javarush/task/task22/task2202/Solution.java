package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
        System.out.println(getPartOfString("Амиго и Диего лучшие друзья!"));
    }

    public static String getPartOfString(String string) {
        String str = null;
        try {
            String[] strs = string.split(" ");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i < 5; i++) {
                stringBuilder.append(strs[i]).append(" ");
            }
            str = stringBuilder.toString().trim();
        }
        catch (Exception e){
            throw new TooShortStringException();
        }

        return str;
    }

    public static class TooShortStringException extends RuntimeException{
    }
}
