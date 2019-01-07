package com.javarush.task.task27.task2712;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets;
    private int ORDER_CREATING_INTERVAL;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int ORDER_CREATING_INTERVAL) {
        this.tablets = tablets;
        this.ORDER_CREATING_INTERVAL = ORDER_CREATING_INTERVAL;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (tablets.size() > 0) {
                    tablets.get((int)(Math.random() * tablets.size())).createTestOrder();
                }
                else {
                    break;
                }
                Thread.sleep(ORDER_CREATING_INTERVAL);
            } catch (InterruptedException e) { return;}
        }
    }
}
