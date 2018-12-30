package com.javarush.task.task30.task3008.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {

    public static void main(String[] args) {
        new BotClient().run();
    }

    public class BotSocketThread extends SocketThread {

        public BotSocketThread() {
        }

        @Override
        protected void processIncomingMessage(String message) {
            if (message.contains(": ")) {
                String[] strings = message.split(": ", 2);
                SimpleDateFormat simpleDateFormat = null;
                switch (strings[1]){
                    case "дата": {
                        simpleDateFormat = new SimpleDateFormat("d.MM.YYYY");
                        break;
                    }
                    case "день": {
                        simpleDateFormat = new SimpleDateFormat("d");
                        break;
                    }
                    case "месяц": {
                        simpleDateFormat = new SimpleDateFormat("MMMM");
                        break;
                    }
                    case "год": {
                        simpleDateFormat = new SimpleDateFormat("YYYY");
                        break;
                    }
                    case "время": {
                        simpleDateFormat = new SimpleDateFormat("H:mm:ss");
                        break;
                    }
                    case "час": {
                        simpleDateFormat = new SimpleDateFormat("H");
                        break;
                    }
                    case "минуты": {
                        simpleDateFormat = new SimpleDateFormat("m");
                        break;
                    }
                    case "секунды": {
                        simpleDateFormat = new SimpleDateFormat("s");
                        break;
                    }
                    default: {
                        break;
                    }
                }
                if (simpleDateFormat != null) {
                    sendTextMessage("Информация для " + strings[0] + ": " + simpleDateFormat.format(Calendar.getInstance().getTime()));
                }
            }
            super.processIncomingMessage(message);
        }

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }
}
