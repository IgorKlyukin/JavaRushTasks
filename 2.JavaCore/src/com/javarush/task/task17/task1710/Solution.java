package com.javarush.task.task17.task1710;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        int n = args.length, m;
        String s = "";
        try {
            switch (args[0]) {
                case ("-c"): {
                    if (n < 4) {
                        throw new Exception();
                    }
                    date = simpleDateFormat.parse(args[n - 1]);
                    for (int i = 1; i < n - 2; i++) {
                        if (i == n - 3) {
                            s += args[i];
                        } else {
                            s += args[i] + " ";
                        }
                    }
                    if (args[n - 2].charAt(0) == 'м') {
                        allPeople.add(Person.createMale(s, date));
                    } else if (args[n - 2].charAt(0) == 'ж') {
                        allPeople.add(Person.createFemale(s, date));
                    } else {
                        throw new Exception();
                    }
                    System.out.println(allPeople.size() - 1);
                    break;
                }
                case ("-u"): {
                    if (n < 5) {
                        throw new Exception();
                    }
                    date = simpleDateFormat.parse(args[n - 1]);
                    m = Integer.parseInt(args[1]);
                    for (int i = 2; i < n - 2; i++) {
                        if (i == n - 3) {
                            s += args[i];
                        } else {
                            s += args[i] + " ";
                        }
                    }
                    if (args[n - 2].charAt(0) == 'м') {
                        allPeople.set(m, Person.createMale(s, date));
                    } else if (args[n - 2].charAt(0) == 'ж') {
                        allPeople.set(m, Person.createFemale(s, date));
                    }
                    break;
                }
                case ("-d"): {
                    if (n != 2) {
                        throw new Exception();
                    }
                    Person person = allPeople.get(Integer.parseInt(args[1]));
                    person.setName(null);
                    person.setBirthDay(null);
                    person.setSex(null);
                    break;
                }
                case ("-i"): {
                    if (n != 2) {
                        throw new Exception();
                    }
                    simpleDateFormat.applyPattern("dd-MMM-yyyy");
                    Person person = allPeople.get(Integer.parseInt(args[1]));
                    System.out.println(person.getName() + " " + (person.getSex() != null?person.getSex().equals(Sex.MALE) ? "м" : "ж":null) + " " + (person.getBirthDay() != null?simpleDateFormat.format(person.getBirthDay()):null));
                    break;
                }
                default: {
                    break;
                }
            }
        } catch (Exception e) {
        }
    }
}
