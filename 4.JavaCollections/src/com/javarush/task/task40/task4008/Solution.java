package com.javarush.task.task40.task4008;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Locale;

/* 
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        //напишите тут ваш код
        DateTimeFormatter dateFormatter = null, timeFormatter = null;
        String[] strings = date.split(" ");
        for (String s :
                strings) {
            if (s.matches("\\d+\\.\\d+\\.\\d+.*")) {
                dateFormatter = DateTimeFormatter.ofPattern("d.M.yyyy");

                LocalDate localDate = LocalDate.parse(s, dateFormatter);

                System.out.println("День: " + localDate.getDayOfMonth());
                System.out.println("День недели: " + localDate.getDayOfWeek().getValue());
                System.out.println("День месяца: " + localDate.getDayOfMonth());
                System.out.println("День года: " + localDate.getDayOfYear());
                WeekFields weekFields = WeekFields.of(Locale.getDefault());
                System.out.println("Неделя месяца: " + localDate.get(weekFields.weekOfMonth()));
                System.out.println("Неделя года: " + localDate.get(weekFields.weekOfYear()));
                System.out.println("Месяц: " + localDate.getMonthValue());
                System.out.println("Год: " + localDate.getYear());
            }
            if (s.matches(".*\\d+:\\d+:\\d+")) {
                timeFormatter = DateTimeFormatter.ofPattern("H:m:s");

                LocalTime localTime = LocalTime.parse(s, timeFormatter);

                System.out.println("AM или PM: " + (localTime.get(ChronoField.AMPM_OF_DAY) == 1 ? "PM" : "AM"));
                System.out.println("Часы: " + localTime.get(ChronoField.HOUR_OF_AMPM));
                System.out.println("Часы дня: " + localTime.getHour());
                System.out.println("Минуты: " + localTime.getMinute());
                System.out.println("Секунды: " + localTime.getSecond());
            }
        }

    }
}
