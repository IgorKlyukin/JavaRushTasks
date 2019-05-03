package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        try {
            File[] files = new File(URLDecoder.decode(packageName, "UTF-8")).listFiles();
            ClassLoader cl = new MyClassLoader(URLDecoder.decode(packageName, "UTF-8"),
                    ClassLoader.getSystemClassLoader());
            for (int i = 0; i < files.length; i++) {
                if (files[i].toString().endsWith(".class")) {
                    Class clazz = cl.loadClass(files[i].getName().split("\\.class")[0]);
                    hiddenClasses.add(clazz);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class clazz : hiddenClasses) {
            if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                try {
                    Constructor[] constructors = clazz.getDeclaredConstructors();
                    for (Constructor constructor : constructors) {
                        if (constructor.getParameterTypes().length == 0) {
                            Class[] interfaces = clazz.getInterfaces();
                            if (interfaces.length > 0) {
                                for(Class interfaze : interfaces) {
                                    if (interfaze.getSimpleName().equals("HiddenClass")) {
                                        constructor.setAccessible(true);
                                        return (HiddenClass) constructor.newInstance(null);
                                    }
                                }
                            }

                        }
                    }

                } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
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
            } catch (IOException |ClassFormatError e) {
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

