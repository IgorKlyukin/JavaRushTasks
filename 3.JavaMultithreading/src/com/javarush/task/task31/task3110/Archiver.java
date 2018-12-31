package com.javarush.task.task31.task3110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Archiver {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pathZip, pathFile;
//        do {
            System.out.print("Full pathZip: ");
            pathZip = reader.readLine();
//        }while (!pathZip.matches(".:\\\\[^\\\\]+.*(\\.zip)$"));
        ZipFileManager zipFileManager = new ZipFileManager(Paths.get(pathZip));


//        do {
            System.out.print("Full pathFile: ");
            pathFile = reader.readLine();
//        }while (!pathFile.matches(".:\\\\[^\\\\]+.*"));
        try {
            zipFileManager.createZip(Paths.get(pathFile));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
