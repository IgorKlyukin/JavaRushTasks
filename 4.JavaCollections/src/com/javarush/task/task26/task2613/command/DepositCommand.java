package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class DepositCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String s = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(s);

        String[] strings = ConsoleHelper.getValidTwoDigits(s);
        try {
            currencyManipulator.addAmount(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
        } catch (NullPointerException e) {
        }
    }
}
