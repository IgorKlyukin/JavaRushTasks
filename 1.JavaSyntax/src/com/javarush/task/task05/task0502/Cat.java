package com.javarush.task.task05.task0502;

/* 
Реализовать метод fight
*/

public class Cat {
    public String name;
    public int age;
    public int weight;
    public int strength;

    public Cat() {
    }

    public boolean fight(Cat anotherCat) {
        return (anotherCat.age > this.age && anotherCat.weight > this.weight && anotherCat.strength > this.strength);
    }

    public static void main(String[] args) {

    }
}
