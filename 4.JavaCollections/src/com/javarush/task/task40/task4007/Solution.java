package com.javarush.task.task40.task4007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* 
Работа с датами
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
        System.out.println();
    }

    public static void printDate(String date) {
        //напишите тут ваш код
        String s = "";
        int a = 0;
        if (date.matches("\\d+\\.\\d+\\.\\d+.*")) {
            a |= 1;
            s ="dd.MM.yyyy";
        }
        if (date.matches(".*\\d+:\\d+:\\d+")) {
            a |= 1 << 1;
            s = s + (s.equals("") ? "" : " ") + "HH:mm:ss";
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(s);
        Calendar newDate = Calendar.getInstance();

        try {
            newDate.setTime(simpleDateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if ((a & 1) == 1) {
            System.out.println("День: " + newDate.get(Calendar.DAY_OF_MONTH));
            System.out.println("День недели: " + ((newDate.get(Calendar.DAY_OF_WEEK) + 5) % 7 + 1));
            System.out.println("День месяца: " + newDate.get(Calendar.DAY_OF_MONTH));
            System.out.println("День года: " + newDate.get(Calendar.DAY_OF_YEAR));
            System.out.println("Неделя месяца: " + newDate.get(Calendar.WEEK_OF_MONTH));
            System.out.println("Неделя года: " + newDate.get(Calendar.WEEK_OF_YEAR));
            System.out.println("Месяц: " + (newDate.get(Calendar.MONTH) + 1));
            System.out.println("Год: " + newDate.get(Calendar.YEAR));
        }
        if (((a >> 1) & 1) == 1) {
            System.out.println("AM или PM: " + (newDate.get(Calendar.AM_PM) == 1 ? "PM" : "AM"));
            System.out.println("Часы: " + newDate.get(Calendar.HOUR));
            System.out.println("Часы дня: " + newDate.get(Calendar.HOUR_OF_DAY));
            System.out.println("Минуты: " + newDate.get(Calendar.MINUTE));
            System.out.println("Секунды: " + newDate.get(Calendar.SECOND));
        }
    }
}
