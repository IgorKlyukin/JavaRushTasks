package com.javarush.task.task27.task2707;

import java.lang.management.MonitorInfo;

import static java.lang.Thread.sleep;

/*
Определяем порядок захвата монитора
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isLockOrderNormal(final Solution solution, final Object o1, final Object o2) throws Exception {
        //do something here
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                synchronized (o1) {
                    try {
                        sleep(200);
                        synchronized (o2) {
                        }
                    }
                    catch (InterruptedException e){}
                }
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                solution.someMethodWithSynchronizedBlocks(o1, o2);
            }
        };

        thread1.setDaemon(true);
        thread2.setDaemon(true);

        thread1.start();
        try {
            sleep(50);
        }
        catch (InterruptedException e){}
        thread2.start();

        try {
            sleep(300);
        }
        catch (InterruptedException e){}

        System.out.println(thread1.getState());
        System.out.println(thread2.getState());

        return !thread2.getState().equals(Thread.State.BLOCKED);
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isLockOrderNormal(solution, o1, o2));
    }
}
