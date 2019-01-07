package com.javarush.task.task27.task2712;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (tablets.size() > 0) {
                    tablets.get(ThreadLocalRandom.current().nextInt(tablets.size())).createTestOrder();
                }
                else {
                    break;
                }
                Thread.sleep(interval);
            }
        } catch (InterruptedException e) { return;}

    }
}
