package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");

    @Override
    public void execute() throws InterruptOperationException {
        while (true){
            ConsoleHelper.writeMessage("Please, enter two number.");
            ConsoleHelper.writeMessage("Card number:");
            String card = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("Pin code:");
            String pin = ConsoleHelper.readString();

            if (card.length() == 12 && card.matches("^\\d+$")
                    && pin.length() == 4 && pin.matches("^\\d+$")
                    && validCreditCards.containsKey(card) && validCreditCards.getString(card).equals(pin)) {
                ConsoleHelper.writeMessage("Congratulations!");
                break;
            }
            ConsoleHelper.writeMessage("You enter: Card = " + card + " and Pin = " + pin + ". Error");
        }
    }
}
