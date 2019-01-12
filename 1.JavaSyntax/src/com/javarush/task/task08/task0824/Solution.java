package com.javarush.task.task08.task0824;

/* 
Собираем семейство
*/

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        ArrayList<Human> family = new ArrayList<>();
        Human child1 = new Human("child1", true, 10);
        family.add(child1);
        Human child2 = new Human("child2", false, 11);
        family.add(child2);
        Human child3 = new Human("child3", true, 12);
        family.add(child3);

        Human dad = new Human("Dad", true, 40, new ArrayList<>(family));
        family.add(dad);
        Human mam = new Human("Mam", false, 40, dad.children);
        family.add(mam);

        Human dad1 = new Human("Dad1", true, 60, new ArrayList<Human>(){{this.add(dad);}});
        family.add(dad1);
        Human mam1 = new Human("Mam1", false, 60, dad1.children);
        family.add(mam1);

        Human dad2 = new Human("Dad2", true, 60, new ArrayList<Human>(){{this.add(mam);}});
        family.add(dad2);
        Human mam2 = new Human("Mam2", false, 60, dad2.children);
        family.add(mam2);

        for (Human human :
                family) {
            System.out.println(human.toString());
        }
    }

    public static class Human {
        //напишите тут ваш код
        public String name;
        public boolean sex;
        public int age;
        public ArrayList<Human> children;

        public Human(String name, boolean sex, int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = new ArrayList<>();
        }

        public Human(String name, boolean sex, int age, ArrayList<Human> children) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0) {
                text += ", дети: " + this.children.get(0).name;

                for (int i = 1; i < childCount; i++) {
                    Human child = this.children.get(i);
                    text += ", " + child.name;
                }
            }
            return text;
        }
    }

}
