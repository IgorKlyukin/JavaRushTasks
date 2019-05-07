package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.OurHashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        testStrategy(hashMapStorageStrategy, 10000);
        OurHashMapStorageStrategy ourHashMapStorageStrategy = new OurHashMapStorageStrategy();
        testStrategy(ourHashMapStorageStrategy, 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        HashSet<Long> set = new HashSet<>();
        for (String s :
                strings) {
            set.add(shortener.getId(s));
        }
        return set;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        HashSet<String> set = new HashSet<>();
        for (Long l :
                keys) {
            set.add(shortener.getString(l));
        }
        return set;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getName());

        Set<String> list = new HashSet<>();
        Set<Long> listId;
        Set<String> listS;

        for (long i = 0; i < elementsNumber; i++) {
            list.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);
        Date start = new Date();
        listId = getIds(shortener, list);
        Helper.printMessage("" + (new Date().getTime() - start.getTime()));

        start = new Date();
        listS = getStrings(shortener, listId);
        Helper.printMessage("" + (new Date().getTime() - start.getTime()));

        if (list.containsAll(listS) && list.size() == listS.size())
            Helper.printMessage("Тест пройден.");
        else
            Helper.printMessage("Тест не пройден.");
    }
}
