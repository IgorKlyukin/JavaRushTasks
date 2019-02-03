package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        LinkedList<File> list = new LinkedList<>();
        List<String> listString = new ArrayList<>();

        list.addAll(Arrays.asList(new File(root).listFiles()));
        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isDirectory())
                list.addAll(i, Arrays.asList(list.remove(i--).listFiles()));
            else
                listString.add(list.get(i).getAbsolutePath());
        }
        return listString;

    }

    public static void main(String[] args) throws IOException{
        System.out.println(getFileTree("C:\\temp\\qwe"));
    }
}
