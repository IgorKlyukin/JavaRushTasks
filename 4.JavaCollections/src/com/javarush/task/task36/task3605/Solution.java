package com.javarush.task.task36.task3605;

import java.io.*;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
        TreeSet<Character> characters = new TreeSet<>();
        while (reader.ready()) {
            characters.add(((char)reader.read()  + "").toLowerCase().charAt(0));
        }
        reader.close();
        NavigableSet<Character> navigableSet = characters.subSet(characters.ceiling('a'), true, characters.floor('z'), true);
        Iterator<Character> iterator = navigableSet.iterator();
        int n = navigableSet.size() > 5 ? 5 : navigableSet.size();
        for (int i = 0; i < n; i++) {
            System.out.print(iterator.next());
        }
    }
}
