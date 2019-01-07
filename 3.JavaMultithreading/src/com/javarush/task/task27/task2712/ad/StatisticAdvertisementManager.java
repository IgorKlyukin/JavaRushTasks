package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager statisticAdvertisementManager = new StatisticAdvertisementManager();
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {
    }

    public static StatisticAdvertisementManager getStatisticAdvertisementManager() {
        return statisticAdvertisementManager;
    }

    public List<Advertisement> activeArchivedVideoSet(boolean flag) {
        List<Advertisement> list = new ArrayList<>();
        for (Advertisement adv : advertisementStorage.list()) {
            if (adv.getHits() > 0 == flag) {
                list.add(adv);
            }
        }
        Collections.sort(list, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return list;
    }
}
