package com.javarush.task.task13.task1319;

import java.io.*;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(bufferedReader.readLine()))); // соединяем FileWriter с BufferedWitter
        String line;
        while(!(line = bufferedReader.readLine()).equals("exit")) {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.write(line);

        bufferedReader.close(); // закрываем поток
        bufferedWriter.close();
    }
}
