package com.javarush.task.task22.task2213;

import java.util.ArrayList;

public class Field {
    private int width; //ширина
    private int height; //высота

    private int[][] matrix;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new int[height][width];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void print() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(matrix[i][j] == 0 ? "." : "X");
            }
            System.out.println();
        }
    }

    public void removeFullLines() {
        boolean flag;
        ArrayList<Integer> zeroList = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            flag = false;
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == 0)
                    break;
                if (j == width - 1)
                    flag = true;
            }
            if (flag)
                zeroList.add(i);
        }

        for (int i = 0; i < zeroList.size(); i++) {
            for (int j = zeroList.get(i); j > 0; j--) {
                for (int k = 0; k < width; k++) {
                    matrix[j][k] = matrix[j - 1][k];
                }
            }
        }
    }

    public Integer getValue(int x, int y) {
        return matrix[y][x];
    }

    public void setValue(int x, int y, int value) {
        matrix[y][x] = value;
    }
}
