package com.javarush.task.task27.task2710;

public class Mail {
    private String text;

    public synchronized String getText() {
        return text;
    }

    public synchronized void setText(String text) {
        this.text = text;
    }
}
