package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator cM = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        int a;
        while (true) {
            ConsoleHelper.writeMessage("Please, enter count money.");
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
                        ConsoleHelper.writeMessage("GREAT!!! Good!!! Ok!)");
                        break;
                    } catch (NotEnoughMoneyException e) {
                        ConsoleHelper.writeMessage("Nomination is low. Sorry.");
                        continue;
                    }
                }
                else {
                    ConsoleHelper.writeMessage("Money is low. Sorry.");
                    continue;
                }
            }
            ConsoleHelper.writeMessage("You enter: " + s + ". It's no number!");
        }
    }
}
