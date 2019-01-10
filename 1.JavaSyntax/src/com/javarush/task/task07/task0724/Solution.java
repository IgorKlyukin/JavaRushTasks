package com.javarush.task.task07.task0724;

/* 
Семейная перепись
*/

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        // напишите тут ваш код
        ArrayList<Human> arrayList = new ArrayList<>();
        Human dad1 = new Human("dad1", true, 80);
        arrayList.add(dad1);
        Human dad2 = new Human("dad2", true, 81);
        arrayList.add(dad2);
        Human mom1 = new Human("mom1", false, 82);
        arrayList.add(mom1);
        Human mom2 = new Human("mom2", false, 83);
        arrayList.add(mom2);

        Human dad = new Human("DAD", true, 40, dad1, mom1);
        arrayList.add(dad);
        Human mom = new Human("MOM", false, 40, dad2, mom2);
        arrayList.add(mom);

        Human child1 = new Human("child1", true, 20, dad, mom);
        arrayList.add(child1);
        Human child2 = new Human("child2", false, 20, dad, mom);
        arrayList.add(child2);
        Human child3 = new Human("child3", true, 20, dad, mom);
        arrayList.add(child3);

        for (Human human :
                arrayList) {
            System.out.println(human.toString());
        }


    }

    public static class Human {
        // напишите тут ваш код
        private String name;
        private boolean sex;
        private int age;
        private Human father;
        private Human mother;

        public Human(String name, boolean sex, int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public Human(String name, boolean sex, int age, Human father, Human mother) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
                text += ", отец: " + this.father.name;

            if (this.mother != null)
                text += ", мать: " + this.mother.name;

            return text;
        }
    }
}