package com.javarush.task.task32.task3210;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(args[0], "rw")) {
            int a = Integer.parseInt(args[1]);
            randomAccessFile.seek(a);
            byte[] bytes = new byte[args[2].length()];
            randomAccessFile.read(bytes,0, bytes.length);
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.write(args[2].equals(new String(bytes)) ? "true".getBytes() : "false".getBytes());
        } catch (IOException e){}
    }
}
