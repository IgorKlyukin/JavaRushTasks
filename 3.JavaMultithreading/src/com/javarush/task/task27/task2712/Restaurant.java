package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    public static void main(String[] args) {
//        Tablet tablet = new Tablet(999);
//        Cook cook = new Cook("Amigo");
//        tablet.addObserver(cook);
//        Waiter waiter = new Waiter();
//        cook.addObserver(waiter);
//        for (int i = 0; i < 4; i++) {
//            tablet.createOrder();
//        }
//        int n = 4;
//        Tablet[] tablets = new Tablet[n];
//        Cook[] cooks = new Cook[n];
//        Waiter waiter = new Waiter();
//        for (int i = 0; i < n; i++) {
//            tablets[i] = new Tablet(i);
//            cooks[i] = new Cook("cook №" + i);
//            tablets[i].addObserver(cooks[i]);
//            cooks[i].addObserver(waiter);
//        }
//
//        for (int i = 0; i < n; i++)
//        {
//            tablets[i % n].createOrder();
//            tablets[(i + 1) % n].createOrder();
//        }

        List<Tablet> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Tablet(i));
        }
        Cook[] cooks = new Cook[2];
        Waiter waiter = new Waiter();
        for (int i = 0; i < 2; i++) {
            cooks[i] = new Cook("cook №" + i);
            cooks[i].addObserver(waiter);
        }
        for (int i = 0; i < 10; i++) {
            list.get(i).addObserver(cooks[i % 2]);
        }
        Thread thread = new Thread(new RandomOrderGeneratorTask(list, ORDER_CREATING_INTERVAL));
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
