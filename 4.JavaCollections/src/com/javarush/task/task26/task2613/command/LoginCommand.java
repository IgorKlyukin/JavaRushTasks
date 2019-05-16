package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

public class LoginCommand implements Command {
    private static final String CARD_NUMBER = "123456789012";
    private static final String PIN_CODE = "1234";

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
                    && card.equals(CARD_NUMBER) && pin.equals(PIN_CODE)) {
                ConsoleHelper.writeMessage("Congratulations!");
                break;
            }
            ConsoleHelper.writeMessage("You enter: Card = " + card + " and Pin = " + pin + ". Error");
        }
    }
}
