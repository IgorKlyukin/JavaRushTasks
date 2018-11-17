package com.javarush.task.task10.task1013;

/* 
Конструкторы класса Human
*/

public class Solution {
    public static void main(String[] args) {
    }

    public static class Human {
        private int age;
        private String name;
        private boolean sex;
        private String proffesion;
        private double assessment;
        private char singal;

        //1
        public Human(String name){
            this.name = name;
        }
        //2
        public Human(String name, int age){
            this.name = name;
            this.age = age;
        }
        //3
        public Human(String name, int age, boolean sex){
            this.name = name;
            this.age = age;
            this.sex = sex;
        }
        //4
        public Human(String name, int age, boolean sex, String proffesion){
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.proffesion = proffesion;
        }
        //5
        public Human(String name, int age, boolean sex, String proffesion, double assessment){
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.proffesion = proffesion;
            this.assessment = assessment;
        }
        //6
        public Human(String name, int age, boolean sex, char singal){
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.singal = singal;
        }
        //7
        public Human(String name, int age, double assessment, char singal){
            this.name = name;
            this.age = age;
            this.assessment = assessment;
            this.singal = singal;
        }
        //8
        public Human(int age, boolean sex, String proffesion, double assessment, char singal){
            this.age = age;
            this.sex = sex;
            this.proffesion = proffesion;
            this.assessment = assessment;
            this.singal = singal;
        }
        //9
        public Human(String name, boolean sex, String proffesion, double assessment, char singal){
            this.name = name;
            this.sex = sex;
            this.proffesion = proffesion;
            this.assessment = assessment;
            this.singal = singal;
        }
        //10
        public Human(String name, int age, boolean sex, String proffesion, double assessment, char singal){
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.proffesion = proffesion;
            this.assessment = assessment;
            this.singal = singal;
        }
    }
}
