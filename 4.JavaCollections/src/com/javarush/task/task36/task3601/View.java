package com.javarush.task.task36.task3601;

public class View {
    private Controller c = new Controller();
    public void fireShowDataEvent() {
        System.out.println(c.onShowDataList());
    }
}
