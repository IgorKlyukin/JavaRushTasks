package com.javarush.task.task36.task3601;

import java.util.List;

public class Model {
    Service s = new Service();
    public List<String> getStringDataList() {
        return s.getData();
    }
}
