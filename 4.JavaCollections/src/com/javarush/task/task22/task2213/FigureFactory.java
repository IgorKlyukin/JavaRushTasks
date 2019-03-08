package com.javarush.task.task22.task2213;

public class FigureFactory {
    public static Figure createRandomFigure(int x,int y) {
        return new Figure(x, y, new int[3][3]);
    }
}
