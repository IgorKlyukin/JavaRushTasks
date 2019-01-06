package com.javarush.task.task27.task2712.kitchen;

public enum Dish {
    Fish,
    Steak,
    Soup,
    Juice,
    Water;

    public static String allDishesToString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Dish dish: Dish.values()) {
            stringBuilder.append(dish).append(", ");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }
}
