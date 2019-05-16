package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String s = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(s);

        String[] strings = ConsoleHelper.getValidTwoDigits(s);
        try {
            currencyManipulator.addAmount(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
        } catch (NullPointerException e) {
        }
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), Integer.parseInt(strings[0]) * Integer.parseInt(strings[1]), s));
    }
}
