package com.javarush.task.task22.task2213;

public class Figure {
    private int x;
    private int y;
    private int[][] matrix;

    public Figure(int x, int y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void left() {
        x--;
    }

    public void right() {
        x++;
    }

    public void down() {
        y++;
    }

    public void up() {
        y--;
    }

    public void rotate() {
        int t;
        for (int i = 0; i < 2; i++) {
            t = matrix[0][i];
            matrix[0][i] = matrix[2 - i][0];
            matrix[2 - i][0] = matrix[2][2 - i];
            matrix[2][2 - i] = matrix[i][2];
            matrix[i][2] = t;
        }
    }

    public void downMaximum() {

    }

    public boolean isCurrentPositionAvailable() {
        return true;
    }

    public void landed() {}

}
