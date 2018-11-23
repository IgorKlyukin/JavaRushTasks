package com.javarush.task.task24.task2413;

public class Canvas {
    private int width;  //ширина
    private int height; //высота
    private char[][] matrix;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new char[height+2][width+2];
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    void setPoint(double x, double y, char c){
        if (x >= 0 && y >= 0 && x < matrix[0].length && y < matrix.length){
            matrix[(int)Math.round(y)][(int)Math.round(x)] = c;
        }
    }

    void drawMatrix(double x, double y, int[][] matrix, char c){
        for (int i = 0, n = matrix.length; i < n; i++) {
            for (int j = 0, m = matrix[i].length; j < m; j++) {
                if (matrix[i][j] != 0){
                    setPoint(x+j, y+i, c);
                }
            }
        }
    }

    void clear(){
        matrix = new char[height+2][width+2];
    }

    void print(){
        for (int i = 0, n = matrix.length; i < n; i++) {
            for (int j = 0, m = matrix[i].length; j < m; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
