package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.login_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true){
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String card = ConsoleHelper.readString();
            String pin = ConsoleHelper.readString();

            if (card.length() == 12 && card.matches("^\\d+$")
                    && pin.length() == 4 && pin.matches("^\\d+$")) {
                    if (validCreditCards.containsKey(card) && validCreditCards.getString(card).equals(pin)) {
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), card));
                        break;
                    }
                    else
                        ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), card));
            }
            else
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
        }
    }
}
