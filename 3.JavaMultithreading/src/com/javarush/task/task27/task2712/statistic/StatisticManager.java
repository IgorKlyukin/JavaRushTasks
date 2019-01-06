package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.EventDataRow;

public class StatisticManager {
    private static StatisticManager instance = new StatisticManager();

    private StatisticManager() {
    }

    public static StatisticManager getInstance() {
        return instance;
    }

    public static void register(EventDataRow data){}
}
