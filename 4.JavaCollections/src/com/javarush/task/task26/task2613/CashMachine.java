package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);

        Operation o;
        do {
            o = ConsoleHelper.askOperation();
            CommandExecutor.execute(o);
        }while (o != null && !o.equals(Operation.EXIT));
    }
}
