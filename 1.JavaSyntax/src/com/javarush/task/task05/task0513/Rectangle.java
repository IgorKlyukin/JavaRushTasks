package com.javarush.task.task05.task0513;

/* 
Собираем прямоугольник
*/

import java.sql.Ref;

public class Rectangle {
    int top, left, width, height;

    public void initialize(int top, int left, int width, int height){
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
    }
    public void initialize(int top, int left){
        this.top = top;
        this.left = left;
//        width = 0;
//        height = 0;
        width = height = 0;
    }
    public void initialize(int top, int left, int width){
        this.top = top;
        this.left = left;
        this.width = width;
        height = width;
    }
    public void initialize(Rectangle rectangle){
        this.top = rectangle.top;
        this.left = rectangle.left;
        this.width = rectangle.width;
        this.height = rectangle.height;
    }

    public static void main(String[] args) {

    }
}
