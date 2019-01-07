package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticManager {
    private static StatisticManager instance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<>();

    private StatisticManager() {
    }

    public static StatisticManager getInstance() {
        return instance;
    }

    public void register(EventDataRow data){
        getInstance().statisticStorage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage;

        public StatisticStorage() {
            storage = new HashMap<>();
            for (EventType eventType :
                    EventType.values()) {
                storage.put(eventType, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }

        private Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }
    }

    public Map<Date, Long> advertisementProfit() {
        Map<Date, Long> map = new TreeMap<>(Collections.reverseOrder());
        List<EventDataRow> list = statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS);
        long amount = 0;
        Date date = list.get(0).getDate();
        for (EventDataRow eventDataRow : list) {
            if (compare(date, eventDataRow.getDate())) {
                amount += ((VideoSelectedEventDataRow)eventDataRow).getAmount();
            }
            else {
                map.put(date, amount);
                amount = ((VideoSelectedEventDataRow)eventDataRow).getAmount();
                date = eventDataRow.getDate();
            }
        }
        map.put(date, amount);
        return map;
    }

    private boolean compare(Date date1, Date date2){
        if (date1.getYear() != date2.getYear())
            return false;
        if (date1.getMonth() != date2.getMonth())
            return false;
        if (date1.getDate() != date2.getDate())
            return false;
        return true;
    }

    public Map<Date, Map<String, Integer>> cookWorkloading() {
        Map<Date, Map<String, Integer>> map = new TreeMap<>(Collections.reverseOrder());
        Map<String, Integer> mapCook = new TreeMap<>();
        List<EventDataRow> list = statisticStorage.getStorage().get(EventType.COOKED_ORDER);
        int time = 0;
        Date date = list.get(0).getDate();
        String cook = ((CookedOrderEventDataRow)list.get(0)).getCookName();
        for (EventDataRow eventDataRow : list) {
            if (compare(date, eventDataRow.getDate())) {
                if (cook.equals(((CookedOrderEventDataRow)eventDataRow).getCookName())) {
                    time += eventDataRow.getTime();
                }
                else {
                    if (mapCook.containsKey(cook)) {
                        mapCook.put(cook, mapCook.get(cook) + time);
                    }
                    else {
                        mapCook.put(cook, time);
                    }
                    time = eventDataRow.getTime();
                    cook = ((CookedOrderEventDataRow)eventDataRow).getCookName();
                }
            }
            else {
                if (mapCook.size() == 0) {
                    mapCook.put(cook, time);
                } else if (mapCook.containsKey(cook)) {
                    mapCook.put(cook, mapCook.get(cook) + time);
                }
                else {
                    mapCook.put(cook, time);
                }
                map.put(date, mapCook);
                time = eventDataRow.getTime();
                date = eventDataRow.getDate();
                cook = ((CookedOrderEventDataRow)eventDataRow).getCookName();
                mapCook = new TreeMap<>();
            }
        }
        if (mapCook.containsKey(cook)) {
            mapCook.put(cook, mapCook.get(cook) + time);
        }
        else {
            mapCook.put(cook, time);
        }
        map.put(date, mapCook);
        return map;
    }
}
