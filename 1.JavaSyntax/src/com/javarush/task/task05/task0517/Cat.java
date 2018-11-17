package com.javarush.task.task05.task0517;

/* 
Конструируем котиков
*/

public class Cat {
    String name;
    int age;
    int weight;
    String address;
    String color;

    public Cat(String name){
        this.name = name;
        age = 1;
        weight = 1;
        color = "red";
    }
    public Cat(String name, int weight, int age){
        this.name = name;
        this.age = age;
        this.weight = weight;
        color = "red";
    }
    public Cat(String name, int age){
        this.name = name;
        this.age = age;
        weight = 2;
        color = "red";
    }
    public Cat(int weight, String color){
        age = 1;
        this.weight = weight;
        this.color = color;
    }
    public Cat(int weight, String color, String address){
        age = 3;
        this.weight = weight;
        this.address = address;
        this.color = color;
    }

    public static void main(String[] args) {

    }
}
