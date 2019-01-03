package com.javarush.task.task28.task2805;

import javafx.scene.layout.Priority;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    private static AtomicInteger j = new AtomicInteger();

    public MyThread() {
        PriorityThread(MAX_PRIORITY);
    }

    public MyThread(Runnable target) {
        super(target);
        PriorityThread(MAX_PRIORITY);
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        PriorityThread(group.getMaxPriority());
    }

    public MyThread(String name) {
        super(name);
        PriorityThread(MAX_PRIORITY);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        PriorityThread(group.getMaxPriority());
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        PriorityThread(MAX_PRIORITY);
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        PriorityThread(group.getMaxPriority());
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        PriorityThread(group.getMaxPriority());
    }

    private void PriorityThread (int PriorityThreadGroup) {
        synchronized (this) {
            int prioritet = j.intValue() % MAX_PRIORITY + 1;
            this.setPriority(prioritet > PriorityThreadGroup ? PriorityThreadGroup : prioritet);
            j.incrementAndGet();
        }
    }
}
