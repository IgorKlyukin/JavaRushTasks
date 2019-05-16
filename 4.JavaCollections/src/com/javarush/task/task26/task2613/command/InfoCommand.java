package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> currencyManipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (!currencyManipulators.isEmpty())
            for (CurrencyManipulator cM :
                currencyManipulators) {
                if (!cM.hasMoney())
                    ConsoleHelper.writeMessage(res.getString("no.money"));
                else
                    ConsoleHelper.writeMessage(cM.getCurrencyCode() + " - " + cM.getTotalAmount());
            }
        else
            ConsoleHelper.writeMessage(res.getString("no.money"));
    }
}
