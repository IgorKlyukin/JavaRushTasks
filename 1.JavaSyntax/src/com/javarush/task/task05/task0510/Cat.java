package com.javarush.task.task05.task0510;

/* 
Кошкоинициация
*/

public class Cat {
    String name;
    int age;
    int weight;
    String address;
    String color;

    public void initialize(String name){
        this.name = name;
        age = 1;
        weight = 1;
        color = "red";
    }
    public void initialize(String name, int weight, int age){
        this.name = name;
        this.weight = weight;
        this.age = age;
        color = "red";
    }
    public void initialize(String name, int age){
        this.name = name;
        this.weight = 3;
        this.age = age;
        color = "red";
    }
    public void initialize(int weight, String color){
        this.weight = weight;
        this.age = 4;
        this.color = color;
    }
    public void initialize(int weight, String color, String address){
        this.weight = weight;
        this.age = 5;
        this.color = color;
        this.address = address;
    }

    public static void main(String[] args) {

    }
}
