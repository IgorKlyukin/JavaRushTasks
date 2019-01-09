package com.javarush.task.task06.task0621;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Родственные связи кошек
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String dadName = reader.readLine();
        Cat catDad = new Cat(dadName);

        String momName = reader.readLine();
        Cat catMom = new Cat(momName);

        String fatherName = reader.readLine();
        Cat catFather = new Cat(fatherName, null, catDad);

        String motherName = reader.readLine();
        Cat catMother = new Cat(motherName, catMom, null);

        String sonName = reader.readLine();
        Cat catSon = new Cat(sonName, catMother, catFather);

        String daughterName = reader.readLine();
        Cat catDaughter = new Cat(daughterName, catMother, catFather);

        System.out.println(catDad);
        System.out.println(catMom);
        System.out.println(catFather);
        System.out.println(catMother);
        System.out.println(catSon);
        System.out.println(catDaughter);
    }

    public static class Cat {
        private String name;
        private Cat parentMom = null;
        private Cat parentDad = null;

        Cat(String name) {
            this.name = name;
        }

        public Cat(String name, Cat parentMom, Cat parentDad) {
            this.name = name;
            this.parentMom = parentMom;
            this.parentDad = parentDad;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("The cat's name is " + name + ", ");
            stringBuilder.append(parentMom != null ? "mother is " + parentMom.name : "no mother");
            stringBuilder.append(parentDad != null ? ", father is " + parentDad.name : ", no father");
            return stringBuilder.toString();
        }
    }

}
