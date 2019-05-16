package com.javarush.task.task26.task2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() {
        String s = null;
        try {
            s = bis.readLine();
        } catch (IOException e) {
        }
        return s;
    }

    public static String askCurrencyCode() {
        String s;
        while (true) {
            writeMessage("Please, enter code. Format: XXX");
            s = readString();
            if (s.length() == 3)
                break;
            else
                writeMessage("You enter: " + s + ". Format ERROR!");
        }
        return s.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) {
        String[] s = new String[2];
        while (true){
            writeMessage("Please, enter two positive integer.");
            String str = readString();
            if (str.matches("^\\d+\\s\\d+$")) {
                s = str.split(" ");
                break;
            }
            else
                writeMessage("You enter: " + str +". It`s not two positive integer.");
        }
        return s;
    }
}
