package com.javarush.task.task31.task3105;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        Path file = Paths.get(args[0]);
        Path zip = Paths.get(args[1]);

        Path tempZipFile = Files.createTempFile(null, null);
        Map<ZipEntry, ByteArrayOutputStream> archiveFiles = new HashMap<>();

        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zip))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                if (!fileName.equals("new/" + file.toFile().getName()))
                    archiveFiles.put(zipEntry, copyData(zipInputStream));
                zipInputStream.closeEntry();
                zipEntry = zipInputStream.getNextEntry();
            }
        }

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zip))) {

            for (Map.Entry<ZipEntry, ByteArrayOutputStream> entry :
                    archiveFiles.entrySet()) {
                zipOutputStream.putNextEntry(new ZipEntry(entry.getKey().getName()));
                zipOutputStream.write(entry.getValue().toByteArray());
                zipOutputStream.closeEntry();
            }

            ZipEntry entry = new ZipEntry("new/" + file.toFile().getName());
            zipOutputStream.putNextEntry(entry);
            Files.copy(file, zipOutputStream);
            zipOutputStream.closeEntry();
        }
        Files.move(tempZipFile, zip, StandardCopyOption.REPLACE_EXISTING);
    }

    private static ByteArrayOutputStream copyData(InputStream in) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[8 * 1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        return byteArrayOutputStream;
    }
}
