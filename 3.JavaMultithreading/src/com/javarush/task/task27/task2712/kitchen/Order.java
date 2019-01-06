package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public int getTotalCookingTime() {
        int cookingTime = 0;
        for (Dish dish :
                dishes) {
            cookingTime += dish.getDuration();
        }
        return cookingTime;
    }

    public boolean isEmpty() {
        return dishes.size() <= 0;
    }

    @Override
    public String toString() {
        return dishes.size() == 0
                ? ""
                : "Your order: " + dishes.toString() + " of " + tablet.toString() + ", cooking time " + getTotalCookingTime() + "min";
    }
}
