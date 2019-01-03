package com.javarush.task.task28.task2802;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.NORM_PRIORITY;

/* 
Пишем свою ThreadFactory
*/
public class Solution {

    public static void main(String[] args) {
        class EmulateThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulateThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulateThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }

    public static class AmigoThreadFactory implements ThreadFactory {
        AtomicInteger i = new AtomicInteger();
        AtomicInteger ij;
        static AtomicInteger j = new AtomicInteger();

        public AmigoThreadFactory() {
            synchronized (this) {
                j.incrementAndGet();
                ij = new AtomicInteger(j.intValue());
            }
        }

        @Override
        public Thread newThread(Runnable r) {
            i.incrementAndGet();
            Thread thread = new Thread(r) {
                @Override
                public void run() {
                    super.run();
                }
            };
            thread.setName(String.format("%s-pool-%d-thread-%d", Thread.currentThread().getThreadGroup().getName(), ij.intValue(), i.intValue()));
            thread.setDaemon(false);
            thread.setPriority(NORM_PRIORITY);
            return thread;
        }
    }
}
