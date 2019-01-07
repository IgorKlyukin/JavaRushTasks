package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable{
    private final int number;
    private Order order;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder() {
        try {
            order = new Order(this);
            ConsoleHelper.writeMessage(order.toString());
            orderIsEmpty();
        }
        catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            order = null;
        }
        return order;
    }

    private void orderIsEmpty() {
        if (!order.isEmpty()) {
            setChanged();
            notifyObservers(order);
            AdvertisementManager manager = new AdvertisementManager(order.getTotalCookingTime() * 60);
            manager.processVideos();
        }
    }

    public void createTestOrder() {
        try {
            order = new TestOrder(this);
            orderIsEmpty();
        }
        catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            order = null;
        }
    }

    @Override
    public String toString() {
        return "Tablet{number=" + number + "}";
    }
}
