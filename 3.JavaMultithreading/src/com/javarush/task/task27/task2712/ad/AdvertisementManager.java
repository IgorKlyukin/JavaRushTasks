package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException{
        if (storage.list().isEmpty()) {
            throw new NoVideoAvailableException();
        }
        else {
            Collections.sort(storage.list(), new Comparator<Advertisement>() {
                @Override
                public int compare(Advertisement o1, Advertisement o2) {
                    if (o1.getAmountPerOneDisplaying() > o2.getAmountPerOneDisplaying()) return -1;
                    else if (o1.getAmountPerOneDisplaying() == o2.getAmountPerOneDisplaying()){
                        if ((double) (o1.getDuration() / o1.getAmountPerOneDisplaying()) > (double) (o2.getDuration() / o2.getAmountPerOneDisplaying())) return -1;
                        else return 1;
                    }else return 1;
                }
            });

            List<Advertisement> list = new ArrayList<>();
            long amount = 0;
            int totalDuration = 0;

            for (Advertisement adv : storage.list()) {
                if (adv.getDuration() <= timeSeconds) {
                    try {
                        adv.revalidate();
                    } catch (UnsupportedOperationException e) {
                        continue;
                    }
                    timeSeconds -= adv.getDuration();
                    list.add(adv);
                    amount += adv.getAmountPerOneDisplaying();
                    totalDuration += adv.getDuration();
                }
            }

            StatisticManager.getInstance().register(new VideoSelectedEventDataRow(list, amount, totalDuration));

            for (Advertisement adv :
                    list) {
                ConsoleHelper.writeMessage(adv.toString());
            }
        }
    }
}
