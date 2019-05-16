package com.javarush.task.task26.task2613;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);

        String s = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(s);

        String[] strings = ConsoleHelper.getValidTwoDigits(s);
        try {
            currencyManipulator.addAmount(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
        } catch (NullPointerException e) {
        }
    }
}
