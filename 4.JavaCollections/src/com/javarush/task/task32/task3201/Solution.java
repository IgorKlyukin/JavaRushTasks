package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(args[0], "rw");
            int a = Integer.parseInt(args[1]);
            randomAccessFile.seek(a > randomAccessFile.length() ? randomAccessFile.length() : a);
            randomAccessFile.write(args[2].getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
