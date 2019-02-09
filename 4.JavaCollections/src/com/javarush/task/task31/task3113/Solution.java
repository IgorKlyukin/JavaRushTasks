package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Path path = Paths.get(reader.readLine());
        reader.close();

        if (Files.isDirectory(path)){
            SearchFileVisitor search = new SearchFileVisitor();
            Files.walkFileTree(path, search);
            System.out.format("Всего папок - %d%n", search.getDirectorys().size() - 1);
            System.out.format("Всего файлов - %d%n", search.getFiles().size());
            System.out.format("Общий размер - %d%n", search.getSize());
        }
        else
            System.out.format("%s - не папка", path);
    }

    public static class SearchFileVisitor extends SimpleFileVisitor<Path> {
        private List<Path> directorys = new ArrayList<>();
        private List<Path> files = new ArrayList<>();
        private long size;
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            files.add(file);
            size += Files.size(file);
            return super.visitFile(file, attrs);
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            directorys.add(dir);
            return super.preVisitDirectory(dir, attrs);
        }

        public List<Path> getDirectorys() {
            return directorys;
        }

        public List<Path> getFiles() {
            return files;
        }

        public long getSize() {
            return size;
        }
    }
}
