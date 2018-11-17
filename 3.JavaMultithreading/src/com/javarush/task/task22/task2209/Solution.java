package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
Составить цепочку слов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(reader.readLine())));
        reader.close();

        List<String> list = new ArrayList<>();
        while (file.ready()) {
            list.addAll(Arrays.asList(file.readLine().split(" ")));
        }
        file.close();
        String[] ss = list.toArray(new String[list.size()]);

        StringBuilder result = getLine(ss);
        System.out.println(result.toString());
    }
    //C:/input.txt

    public static StringBuilder getLine(String... words) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            int n = words.length;
            if (n > 0) {
                if (n == 1) {
                    return stringBuilder.append(words[0]);
                }
                List<String> list = new ArrayList<>(Arrays.asList(words));
                List<String> tmp = new ArrayList<>();
                tmp.add(list.get(0) + "");
                list.remove(0);
                List<String> listStringMax = regursia(list, tmp);
                for (int i = 0, m = listStringMax.size(); i < m; i++) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(" ");
                    }
                    stringBuilder.append(listStringMax.get(i));
                }
            }
        }
        catch (Exception e){}
        return stringBuilder;
    }

    private static List<String> regursia(List<String> list, List<String> stringList){
        List<String> tmp1;
        List<String> tmp2;
        List<String> tmpmax = stringList;
        List<String> tmp;
        for (int i = 0, n = list.size(); i < n; i++) {
            if ((stringList.get(0).charAt(0) + "").toLowerCase().equals((list.get(i).charAt(list.get(i).length() - 1) + "").toLowerCase())) {
                tmp1 = new ArrayList<>(list);
                tmp2 = new ArrayList<>(stringList);
                tmp2.add(0,tmp1.get(i) + "");
                tmp1.remove(i);
                tmp = regursia(tmp1, tmp2);
                if (tmpmax.size() < tmp.size()){
                    tmpmax = tmp;
                }
            }
            if ((stringList.get(stringList.size() - 1).charAt(stringList.get(stringList.size() - 1).length() - 1) + "").toLowerCase().equals((list.get(i).charAt(0) + "").toLowerCase())) {
                tmp1 = new ArrayList<>(list);
                tmp2 = new ArrayList<>(stringList);
                tmp2.add(tmp1.get(i) + "");
                tmp1.remove(i);
                tmp = regursia(tmp1, tmp2);
                if (tmpmax.size() < tmp.size()){
                    tmpmax = tmp;
                }
            }
        }
        return tmpmax;
    }
}
