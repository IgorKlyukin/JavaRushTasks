package com.javarush.task.task27.task2712.statistic.event;

import java.util.Date;

public interface EventDataRow {
    public EventType getType();

    public Date getDate();

    public int getTime();
}
