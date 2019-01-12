package com.javarush.task.task08.task0827;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* 
Работа с датой
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(isDateOdd("MAY 1 2013"));
        System.out.println(isDateOdd("JANUARY 1 2000"));
        System.out.println(isDateOdd("JANUARY 2 2020"));
    }

    public static boolean isDateOdd(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM d yyyy", Locale.ENGLISH);
        Date dateOdd = new Date();
        try {
            dateOdd = simpleDateFormat.parse(date);
        } catch (ParseException e) {}
        Date temp = new Date(dateOdd.getTime());
        temp.setDate(0);
        temp.setMonth(0);
        if ((dateOdd.getTime() - temp.getTime())/1000/60/60/24 % 2 > 0) return true;
        return false;
    }
}
