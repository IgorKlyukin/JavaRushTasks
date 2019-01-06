package com.javarush.task.task27.task2712;

public class Restaurant {
    public static void main(String[] args) {
        Tablet tablet = new Tablet(999);
        for (int i = 0; i < 4; i++) {
            tablet.createOrder();
        }

    }
}
