package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.net.URLDecoder;
import java.util.*;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> animalSet = new HashSet<>();
        try {
            File[] files = new File(URLDecoder.decode(pathToAnimals, "UTF-8")).listFiles();
            ClassLoader cl = new MyClassLoader(URLDecoder.decode(pathToAnimals, "UTF-8"),
                                                ClassLoader.getSystemClassLoader());
            for (int i = 0; i < files.length; i++) {
                try {
                    Class clazz = cl.loadClass(files[i].getName().split("\\.class")[0]);
                    if (Animal.class.isAssignableFrom(clazz)) {
                        Constructor[] constructors = clazz.getConstructors();
                        for (int j = 0; j < constructors.length; j++) {
                            if (constructors[j].getParameterCount() == 0)
                                animalSet.add((Animal) clazz.newInstance());
                        }
                    }
                } catch (ClassNotFoundException|IllegalAccessException|InstantiationException e) {
                    System.out.println("Error");
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return animalSet;
    }

    static class MyClassLoader extends ClassLoader {

        private String classPath;

        public MyClassLoader(String classPath, ClassLoader parent) {
            super(parent);
            this.classPath = classPath;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                File f = findFile(name.replace('.','/'), ".class");
                if (f == null)
                    return findSystemClass(name);

                byte[] bytes = loadFileAsBytes(f);
                return defineClass(null, bytes, 0, bytes.length);
            } catch (IOException|ClassFormatError e) {
                return super.findClass(name);
            }
        }

        private byte[] loadFileAsBytes(File f) throws IOException{
            byte[] result = new byte[(int)f.length()];
            FileInputStream fis = new FileInputStream(f);
            try {
                fis.read(result, 0, result.length);
            } finally {
                try {
                    fis.close();
                } catch (IOException e) {}
            }
            return result;
        }

        private File findFile(String name, String s) {
            File f = new File((new File(classPath)).getPath()
                    + File.separatorChar
                    + name.replace('/', File.separatorChar)
                    + s);
            if (f.exists())
                return f;
            return null;
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            Class result = findClass(name);
            if (resolve)
                resolveClass(result);
            return result;
        }
    }
}
