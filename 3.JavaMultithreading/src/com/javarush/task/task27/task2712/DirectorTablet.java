package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class DirectorTablet {
    public void printAdvertisementProfit() {
        long total = 0;
        for (Map.Entry<Date, Long> entry: StatisticManager.getInstance().advertisementProfit().entrySet()) {
            ConsoleHelper.writeMessage(String.format("%s - %.2f", new SimpleDateFormat("dd-MMM-yyyy", Locale.US).format(entry.getKey()), entry.getValue() / 100.0));
            total += entry.getValue();
        }
        ConsoleHelper.writeMessage(String.format("Total - %.2f", total / 100.0));
    }

    public void printCookWorkloading() {
        for (Map.Entry<Date, Map<String, Integer>> entry1: StatisticManager.getInstance().cookWorkloading().entrySet()){
            ConsoleHelper.writeMessage(new SimpleDateFormat("dd-MMM-yyyy", Locale.US).format(entry1.getKey()));
            for (Map.Entry<String, Integer> entry2: entry1.getValue().entrySet()) {
                if (Math.ceil(entry2.getValue() / 60.0) > 0) {
                    ConsoleHelper.writeMessage(String.format("%s - %.0f min", entry2.getKey(), Math.ceil(entry2.getValue() / 60.0)));
                }
            }
            ConsoleHelper.writeMessage("");
        }
    }

    public void printActiveVideoSet() {}

    public void printArchivedVideoSet() {}
}
