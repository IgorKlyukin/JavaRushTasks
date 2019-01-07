package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderManager implements Observer {// будет Observer для планшетов
    LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public OrderManager() {
        StatisticManager statisticManager = StatisticManager.getInstance();
        Thread demon = new Thread( () ->
        {
            try {
                while (true) {
                    while (orderQueue.isEmpty())
                        Thread.sleep( 10 );
                    for (Cook cook : statisticManager.getCooks()) {
                        if (cook.isBusy()) {
                            new Thread(() -> {
                                if (!orderQueue.isEmpty())
                                    cook.startCookingOrder(orderQueue.poll());

                            }).start();
                        }
                    }
                }
            } catch (InterruptedException ine) {
            }
        } );
        demon.setDaemon( true );
        demon.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        orderQueue.add( (Order) arg );
    }
}
