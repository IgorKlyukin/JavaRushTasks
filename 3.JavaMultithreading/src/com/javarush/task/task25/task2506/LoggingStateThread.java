package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread{
    Thread thread;
    public LoggingStateThread(Thread name) {
        thread = name;
    }

    @Override
    public void run() {
        super.run();
        State state = thread.getState();
        while (!thread.isInterrupted()) {
            System.out.println(state);
            if (state == state.TERMINATED){
                break;
            }
            while (state == thread.getState()) {

            }
            state = thread.getState();
        }
    }
}
