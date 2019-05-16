package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException{
        String s = null;
        try {
            s = bis.readLine();
        } catch (IOException e) {
        }
        if (s.equalsIgnoreCase("EXIT"))
            throw new InterruptOperationException();
        return s;
    }

    public static String askCurrencyCode() throws InterruptOperationException{
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

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException{
        String[] strings;
        while (true){
            writeMessage("Please, enter two positive integer.");
            String s = readString();
            if (s.matches("^\\d+\\s\\d+$")) {
                strings = s.split(" ");
                break;
            }
            else
                writeMessage("You enter: " + s + ". It`s not two positive integer.");
        }
        return strings;
    }

    public static Operation askOperation() throws InterruptOperationException{
        Operation o;
        while (true) {
            writeMessage("Please, enter number operation.");
            String s = readString();
            if (s.matches("\\d")) {
                try {
                    o = Operation.getAllowableOperationByOrdinal(Integer.parseInt(s));
                    break;
                } catch (IllegalArgumentException e) {
                }
            }
            writeMessage("You enter: " + s + ". Numbers don't exist.");
        }
        return o;
    }
}
