package com.javarush.task.task40.task4009;


import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/* 
Buon Compleanno!
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getWeekdayOfBirthday("30.05.1984", "2015"));
        System.out.println(getWeekdayOfBirthday("1.12.2015", "2016"));
    }

    public static String getWeekdayOfBirthday(String birthday, String year) {
        //напишите тут ваш код
        String s = null;
        if (birthday.matches("\\d+\\.\\d+\\.\\d+") && year.matches("\\d{4}")) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            LocalDate localDate = LocalDate.parse(birthday, dateFormatter);

            Year yearNow = Year.parse(year);

            dateFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.ITALIAN);
            localDate = LocalDate.of(yearNow.getValue(), localDate.getMonth(), localDate.getDayOfMonth());

            s = localDate.format(dateFormatter);
        }
        return s;
    }
}
