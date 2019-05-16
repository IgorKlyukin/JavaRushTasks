package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

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
        if (s.equalsIgnoreCase("EXIT")) {
            writeMessage(res.getString("the.end"));
            throw new InterruptOperationException();
        }
        return s;
    }

    public static String askCurrencyCode() throws InterruptOperationException{
        String s;
        while (true) {
            writeMessage(res.getString("choose.currency.code"));
            s = readString();
            if (s.length() == 3)
                break;
            else
                writeMessage(res.getString("invalid.data"));
        }
        return s.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException{
        String[] strings;
        while (true){
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"),currencyCode));
            String s = readString();
            if (s.matches("^\\d+\\s\\d+$")) {
                strings = s.split(" ");
                break;
            }
            else
                writeMessage(res.getString("invalid.data"));
        }
        return strings;
    }

    public static Operation askOperation() throws InterruptOperationException{
        Operation o;
        while (true) {
            writeMessage(res.getString("choose.operation"));
            String s = readString();
            if (s.matches("\\d")) {
                try {
                    o = Operation.getAllowableOperationByOrdinal(Integer.parseInt(s));
                    switch (o) {
                        case INFO: {
                            writeMessage(res.getString("operation.INFO"));
                            break;
                        }
                        case DEPOSIT: {
                            writeMessage(res.getString("operation.DEPOSIT"));
                            break;
                        }
                        case WITHDRAW: {
                            writeMessage(res.getString("operation.WITHDRAW"));
                            break;
                        }
                        case EXIT: {
                            writeMessage(res.getString("operation.EXIT"));
                        }
                    }
                    break;
                } catch (IllegalArgumentException e) {
                }
            }
            writeMessage(res.getString("invalid.data"));
        }
        return o;
    }

    public static void printExitMessage(){
        writeMessage("By By, my love!");
    }
}
