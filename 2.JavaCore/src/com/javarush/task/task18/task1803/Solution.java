
package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;


/*
Самые частые байты
*/


public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream file = new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine());

        HashMap<Byte, Integer> map = new HashMap<>();
        

        file.close();
    }
}
