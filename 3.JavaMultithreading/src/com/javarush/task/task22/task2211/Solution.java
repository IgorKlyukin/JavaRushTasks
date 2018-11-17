package com.javarush.task.task22.task2211;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream(args[0]);
        FileOutputStream outputStream = new FileOutputStream(args[1]);

        Charset koi8 = Charset.forName("UTF-8");
        Charset windows1251 = Charset.forName("Windows-1251");

        byte[] buffer = new byte[1000];
        while (inputStream.available() > 0) {
            inputStream.read(buffer);
            String s = new String(buffer, windows1251);
            buffer = s.getBytes(koi8);
            outputStream.write(buffer);
        }
        inputStream.close();
        outputStream.close();

    }
}
