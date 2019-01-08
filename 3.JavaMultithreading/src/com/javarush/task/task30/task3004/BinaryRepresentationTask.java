package com.javarush.task.task30.task3004;

import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String> {
    private int x;

    public BinaryRepresentationTask(int x) {
        this.x = x;
    }

    @Override
    protected String compute() {
        BinaryRepresentationTask bin;
        int a = x % 2;
        int b = x / 2;
        String result = String.valueOf(a);
        if (b > 0) {
            bin = new BinaryRepresentationTask(b);
            bin.fork();
//            + result;
        }
        else {
            return result;
        }
        return bin.join() + result;
    }
}
