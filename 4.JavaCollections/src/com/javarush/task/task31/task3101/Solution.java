package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) throws IOException{
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);

        List<File> list = regursiaFiles(path);
        list.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        FileOutputStream out = new FileOutputStream(allFilesContent);
        for (File file : list) {
            FileInputStream in = new FileInputStream(file);
            while (in.available() > 0)
                out.write(in.read());
            out.write('\n');
            in.close();
        }
        out.close();

    }

    private static List<File> regursiaFiles(File file) throws NullPointerException{
        List<File> list = new ArrayList<>();
        for (File files : file.listFiles()) {
            if (files.isDirectory())
                list.addAll(regursiaFiles(files));
            else if (files.length() <= 50)
                list.add(files);
        }
        return list;
    }
}
