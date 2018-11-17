package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber != null) {
            if (telNumber.matches("^\\+[\\d|\\-|\\(|\\)]{12,16}")) {
                if (telNumber.matches("^\\+\\d{12}")) return true;
                if (telNumber.matches("^[\\+|\\d]+\\-\\d+") && telNumber.length() == 14) return true;
                if (telNumber.matches("^[\\+|\\d]+\\-\\d+\\-\\d+") && telNumber.length() == 15) return true;
                if (telNumber.matches("^[\\+|\\d]+\\(\\d{3}\\)\\d+") && telNumber.length() == 15) return true;
                if (telNumber.matches("^[\\+|\\d]+\\(\\d{3}\\)\\d*\\-\\d+") && telNumber.length() == 16) return true;
                if (telNumber.matches("^[\\+|\\d]+\\(\\d{3}\\)\\d*\\-\\d+\\-\\d+") && telNumber.length() == 17)
                    return true;
            }
            if (telNumber.matches("^\\d[\\d|\\-|\\(|\\)]{9,13}")) {
                if (telNumber.matches("^\\d{10}")) return true;
                if (telNumber.matches("^\\d+\\-\\d+") && telNumber.length() == 11) return true;
                if (telNumber.matches("^\\d+\\-\\d+\\-\\d+") && telNumber.length() == 12) return true;
                if (telNumber.matches("^\\d+\\(\\d{3}\\)\\d+") && telNumber.length() == 12) return true;
                if (telNumber.matches("^\\d+\\(\\d{3}\\)\\d*\\-\\d+") && telNumber.length() == 13) return true;
                if (telNumber.matches("^\\d+\\(\\d{3}\\)\\d*\\-\\d+\\-\\d+") && telNumber.length() == 14) return true;
            }
            if (telNumber.matches("^\\(\\d{3}\\)[\\d|\\-]{7,9}")) {
                if (telNumber.matches("^\\(\\d{3}\\)\\d{7}")) return true;
                if (telNumber.matches("^\\(\\d{3}\\)\\d*\\-\\d+") && telNumber.length() == 8) return true;
                if (telNumber.matches("^\\(\\d{3}\\)\\d*\\-\\d+\\-\\d+") && telNumber.length() == 9) return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        /*System.out.println(checkTelNumber("+123456789012"));
        System.out.println(checkTelNumber("1234567890"));
        System.out.println(checkTelNumber("(123)4567890"));
        System.out.println(checkTelNumber("+1234(580)90000"));
        System.out.println(checkTelNumber("+1234(580)900-00"));
        System.out.println(checkTelNumber("+1234(580)9-000-0"));
        System.out.println(checkTelNumber("+1234(580)-90000"));
        System.out.println(checkTelNumber("+(580)123490000"));
        System.out.println();
        System.out.println(checkTelNumber("+380501234567"));
        System.out.println(checkTelNumber("+38(050)1234567"));
        System.out.println(checkTelNumber("+38050123-45-67"));
        System.out.println(checkTelNumber("050123-4567"));
        System.out.println(checkTelNumber("+38)050(1234567"));
        System.out.println(checkTelNumber("+38(050)1-23-45-6-7"));
        System.out.println(checkTelNumber("050ххх4567"));
        System.out.println(checkTelNumber("050123456"));
        System.out.println(checkTelNumber("(0)501234567"));*/
    }
}
