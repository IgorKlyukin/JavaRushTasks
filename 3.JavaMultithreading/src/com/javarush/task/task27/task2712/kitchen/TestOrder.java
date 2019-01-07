package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() {
        this.dishes = new ArrayList<>();
        for (int i = 0, n = ThreadLocalRandom.current().nextInt(1,Dish.values().length); i < n; i++) {
            dishes.add(Dish.values()[(int)(Math.random() * Dish.values().length)]);
        }
    }
}
