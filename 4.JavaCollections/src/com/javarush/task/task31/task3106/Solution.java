package com.javarush.task.task31.task3106;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) {

        Arrays.sort(args,1, args.length);

        List<FileInputStream> list = new ArrayList<>();

        for (int i = 1, n = args.length; i < n; i++) {
            try {
                list.add(new FileInputStream(args[i]));
            } catch (FileNotFoundException|NullPointerException e) {
            }
        }

        try (ZipInputStream zipIn = new ZipInputStream(new SequenceInputStream(Collections.enumeration(list)));
             FileOutputStream fileOutputStream = new FileOutputStream(args[0])){
            zipIn.getNextEntry();
            copyData(zipIn, fileOutputStream);
            zipIn.closeEntry();
        }
        catch (Exception e){}
    }

    private static void copyData(InputStream in, OutputStream out) throws Exception {
        byte[] buffer = new byte[8 * 1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }
}
