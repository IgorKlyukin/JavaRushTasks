package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException{
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException{
        String string;
        Dish dish;
        List<Dish> list = new ArrayList<>();

        writeMessage(Dish.allDishesToString());
        writeMessage("Please, enter dish or exit.");

        while (!(string = readString()).equals("exit")) {
            switch (string) {
                case "Fish": {
                    dish = Dish.Fish;
                    break;
                }
                case "Steak": {
                    dish = Dish.Steak;
                    break;
                }
                case "Soup": {
                    dish = Dish.Soup;
                    break;
                }
                case "Juice": {
                    dish = Dish.Juice;
                    break;
                }
                case "Water": {
                    dish = Dish.Water;
                    break;
                }
                default: {
                    writeMessage("There is no such dish.");
                    dish = null;
                    break;
                }
            }
            if (dish != null) {
                list.add(dish);
            }
        }
        return list;
    }
}
