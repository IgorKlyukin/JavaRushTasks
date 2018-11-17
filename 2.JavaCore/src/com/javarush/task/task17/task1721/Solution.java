package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName1)));
        } catch (FileNotFoundException e) {
        }
        while (reader.ready()) {
            allLines.add(reader.readLine());
        }

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName2)));
        } catch (FileNotFoundException e) {
        }
        while (reader.ready()) {
            forRemoveLines.add(reader.readLine());
        }
        reader.close();

        new Solution().joinData();
    }

    public void joinData () throws CorruptedDataException {
        if (allLines.containsAll(forRemoveLines)) {
            allLines.removeAll(forRemoveLines);
        }
        else {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }
}
