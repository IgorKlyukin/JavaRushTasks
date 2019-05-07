package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date start = new Date();
        for (String str :
                strings) {
            ids.add(shortener.getId(str));
        }
        return new Date().getTime() - start.getTime();
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date start = new Date();
        for (long l :
                ids) {
            strings.add(shortener.getString(l));
        }
        return new Date().getTime() - start.getTime();
    }

    @Test
    public void testHashMapStorage() {
        HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        HashBiMapStorageStrategy hashBiMapStorageStrategy = new HashBiMapStorageStrategy();

        Shortener shortener1 = new Shortener(hashMapStorageStrategy);
        Shortener shortener2 = new Shortener(hashBiMapStorageStrategy);

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> ids1 = new HashSet<>();
        Set<Long> ids2 = new HashSet<>();

        long time1 = getTimeToGetIds(shortener1, origStrings, ids1);
        long time2 = getTimeToGetIds(shortener2, origStrings, ids2);

        Assert.assertTrue(time1 - time2 > 0);

        Set<String> str1 = new HashSet<>();
        Set<String> str2 = new HashSet<>();

        long time3 = getTimeToGetStrings(shortener1, ids1, str1);
        long time4 = getTimeToGetStrings(shortener2, ids2, str2);

        Assert.assertEquals(time3, time4, 30);
    }
}
