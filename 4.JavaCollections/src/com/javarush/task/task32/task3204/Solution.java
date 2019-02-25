package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        Random random = new Random();
        int t = 'z' - 'a' + 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(random.nextInt(10));
        stringBuilder.insert(random.nextInt(stringBuilder.length() + 1), (char)(random.nextInt(t) + 'a'));
        stringBuilder.insert(random.nextInt(stringBuilder.length() + 1), (char)(random.nextInt(t) + 'A'));
        for (int i = 0; i < 5; i++) {
            switch (random.nextInt(3)) {
                case 0: {
                    stringBuilder.insert(random.nextInt(stringBuilder.length() + 1), random.nextInt(10));
                    break;
                }
                case 1: {
                    stringBuilder.insert(random.nextInt(stringBuilder.length() + 1), (char)(random.nextInt(t) + 'a'));
                    break;
                }
                case 2:
                    stringBuilder.insert(random.nextInt(stringBuilder.length() + 1), (char)(random.nextInt(t) + 'A'));
            }
        }
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try {
            bytes.write(stringBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }
}