package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {
    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName() + ".resources.";

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            Operation o = Operation.LOGIN;
            CommandExecutor.execute(o);
            do {
                o = ConsoleHelper.askOperation();
                CommandExecutor.execute(o);
            }while (o != null && !o.equals(Operation.EXIT));
        } catch (InterruptOperationException e) {
            ConsoleHelper.printExitMessage();
        }
    }
}
