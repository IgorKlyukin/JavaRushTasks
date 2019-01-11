package com.javarush.task.task08.task0820;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* 
Множество всех животных
*/

public class Solution {
    public static void main(String[] args) {
        Set<Cat> cats = createCats();
        Set<Dog> dogs = createDogs();

        Set<Object> pets = join(cats, dogs);
        printPets(pets);

        removeCats(pets, cats);
        printPets(pets);
    }

    public static Set<Cat> createCats() {
        HashSet<Cat> result = new HashSet<Cat>();

        //напишите тут ваш код
        for (int i = 0; i < 4; i++) {
            result.add(new Cat());
        }

        return result;
    }

    public static Set<Dog> createDogs() {
        HashSet<Dog> result = new HashSet<Dog>();

        //напишите тут ваш код
        for (int i = 0; i < 3; i++) {
            result.add(new Dog());
        }

        return result;
    }

    public static Set<Object> join(Set<Cat> cats, Set<Dog> dogs) {
        //напишите тут ваш код
        Set<Object> set = new HashSet<>();
        set.addAll(cats);
        set.addAll(dogs);
        return set;
    }

    public static void removeCats(Set<Object> pets, Set<Cat> cats) {
        //напишите тут ваш код
        Iterator iterator = pets.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            Iterator<Cat> catIterator = cats.iterator();
            while (catIterator.hasNext()) {
                if (o instanceof Cat) {
                    if (o.equals(catIterator.next())) {
                        iterator.remove();
                        break;
                    }
                }
                else
                    catIterator.next();
            }
        }
    }

    public static void printPets(Set<Object> pets) {
        //напишите тут ваш код
        for (Object o :
                pets) {
            System.out.println(o);
        }
    }

    public static class Cat {
    }

    public static class Dog {
    }

    //напишите тут ваш код
}
