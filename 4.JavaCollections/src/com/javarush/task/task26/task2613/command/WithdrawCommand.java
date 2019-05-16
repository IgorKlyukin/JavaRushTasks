package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator cM = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        int a;
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            String s = ConsoleHelper.readString();
            if (s.matches("^\\d+$")) {
                a = Integer.parseInt(s);
                if (cM.isAmountAvailable(a)) {
                    try {
                        Map<Integer, Integer> newMap = new TreeMap<>(Collections.reverseOrder());
                        newMap.putAll(cM.withdrawAmount(a));
                        for (Map.Entry<Integer, Integer> entry :
                                newMap.entrySet()) {
                            ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
                        }
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"),a,cM.getCurrencyCode()));
                        break;
                    } catch (NotEnoughMoneyException e) {
                        ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                        continue;
                    }
                }
                else {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    continue;
                }
            }
            ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
        }
    }
}
