package com.javarush.task.task17.task1711;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        int n = args.length, m;
        String s = "";
        List<String> str = new ArrayList<>();
        try {
            switch (args[0]) {
                case ("-c"): {
                    synchronized (allPeople) {
                        if (n < 4) {
                            throw new Exception();
                        }
                        m = 1;
                        for (int i = 2; i < n - 1; i++) {
                            date = null;
                            if (args[i].equals("м") || args[i].equals("ж")) {
                                try {
                                    date = simpleDateFormat.parse(args[i + 1]);
                                    for (int k = m; k < i; k++) {
                                        if (k == i - 1) {
                                            s += args[k];
                                        } else {
                                            s += args[k] + " ";
                                        }
                                    }
                                    str.add(s);
                                    str.add(args[i]);
                                    str.add(args[i + 1]);
                                    s = "";
                                    m = i + 2;
                                } catch (Exception e) {
                                }
                            }
                        }
                        if (date == null) {
                            throw new Exception();
                        }
                        args = null;
                        n = str.size();
                        args = new String[n];
                        for (int i = 0; i < n; i++) {
                            args[i] = str.get(i);
                        }
                        //-------------------------------
                        for (int i = 0; i < n; i += 3) {
                            date = simpleDateFormat.parse(args[i + 2]);
                            if (args[i + 1].charAt(0) == 'м') {
                                allPeople.add(Person.createMale(args[i], date));
                            } else {
                                allPeople.add(Person.createFemale(args[i], date));
                            }
                            System.out.println(allPeople.size() - 1);
                        }
                        //-------------------------------
                        break;
                    }
                }
                case ("-u"): {
                    synchronized (allPeople) {
                        if (n < 5) {
                            throw new Exception();
                        }
                        m = 2;
                        for (int i = 3; i < n - 1; i++) {
                            date = null;
                            if (args[i].equals("м") || args[i].equals("ж")) {
                                try {
                                    date = simpleDateFormat.parse(args[i + 1]);
                                    Integer.parseInt(args[m - 1]);
                                    for (int k = m; k < i; k++) {
                                        if (k == i - 1) {
                                            s += args[k];
                                        } else {
                                            s += args[k] + " ";
                                        }
                                    }
                                    str.add(args[m - 1]);
                                    str.add(s);
                                    str.add(args[i]);
                                    str.add(args[i + 1]);
                                    s = "";
                                    m = i + 3;
                                } catch (Exception e) {
                                }
                            }
                        }
                        if (date == null) {
                            throw new Exception();
                        }
                        args = null;
                        n = str.size();
                        args = new String[n];
                        for (int i = 0; i < n; i++) {
                            args[i] = str.get(i);
                        }
                        //-----------------------------------
                        for (int i = 0; i < n; i += 4) {
                            m = Integer.parseInt(args[i]);
                            date = simpleDateFormat.parse(args[i + 3]);
                            if (args[i + 2].charAt(0) == 'м') {
                                allPeople.set(m, Person.createMale(args[i + 1], date));
                            } else {
                                allPeople.set(m, Person.createFemale(args[i + 1], date));
                            }
                        }
                        //-----------------------------------
                        break;
                    }
                }
                case ("-d"): {
                    synchronized (allPeople) {
                        Person person;
                        for (int i = 1; i < args.length; i++) {
                            allPeople.get(Integer.parseInt(args[i]));
                        }
                        for (int i = 1; i < args.length; i++) {
                            person = allPeople.get(Integer.parseInt(args[i]));
                            person.setName(null);
                            person.setBirthDay(null);
                            person.setSex(null);
                        }
                        break;
                    }
                }
                case ("-i"): {
                    synchronized (allPeople) {
                        simpleDateFormat.applyPattern("dd-MMM-yyyy");
                        Person person;
                        for (int i = 1; i < args.length; i++) {
                            allPeople.get(Integer.parseInt(args[i]));
                        }
                        for (int i = 1; i < args.length; i++) {
                            person = allPeople.get(Integer.parseInt(args[i]));
                            System.out.println(person.getName() + " " + (person.getSex() != null ? person.getSex().equals(Sex.MALE) ? "м" : "ж" : null) + " " + (person.getBirthDay() != null ? simpleDateFormat.format(person.getBirthDay()) : null));
                        }
                        break;
                    }
                }
                default: {
                    synchronized (allPeople) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
