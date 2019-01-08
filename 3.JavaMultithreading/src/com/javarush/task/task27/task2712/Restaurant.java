package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private final static int ORDER_CREATING_INTERVAL = 100;
    private final static LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    public static void main(String[] args) {

        Waiter waiter = new Waiter();

        Cook cook1 = new Cook("Amigo1");
        cook1.setQueue(orderQueue);
        cook1.addObserver(waiter);
        Thread threadCook1 = new Thread(cook1);
        threadCook1.setDaemon(true);
        threadCook1.start();

        Cook cook2 = new Cook("Amigo2");
        cook2.setQueue(orderQueue);
        cook2.addObserver(waiter);
        Thread threadCook2 = new Thread(cook2);
        threadCook2.setDaemon(true);
        threadCook2.start();

        List<Tablet> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Tablet(i));
        }

        for (int i = 0; i < 5; i++) {
            list.get(i).setQueue(orderQueue);
        }

//        OrderManager orderManager = new OrderManager();
//        for (int i = 0; i < 5; i++) {
//            list.get(i).addObserver(orderManager);
//            list.get(i).addObserver(orderManager);
//        }

        Thread thread = new Thread(new RandomOrderGeneratorTask(list, ORDER_CREATING_INTERVAL));
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        while (cook1.isBusy() || !orderQueue.isEmpty()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadCook1.interrupt();
        while (cook2.isBusy() || !orderQueue.isEmpty()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadCook2.interrupt();

//
//        DirectorTablet directorTablet = new DirectorTablet();
//        directorTablet.printAdvertisementProfit();
//        directorTablet.printCookWorkloading();
//        directorTablet.printActiveVideoSet();
//        directorTablet.printArchivedVideoSet();
    }
}
