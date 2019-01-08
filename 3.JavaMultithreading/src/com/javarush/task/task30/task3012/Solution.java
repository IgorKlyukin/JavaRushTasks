package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
        solution.createExpression(1234);
        solution.createExpression(2);
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        String string = Integer.toString(number, 3);
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (String s : string.split("")) {
            arrayList.add(Integer.parseInt(s));
        }
//        System.out.println(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(arrayList.size() - 1 - i) == 2) {
                arrayList.set(arrayList.size() - 1 - i, -1);
                if (arrayList.size() - 2 - i < 0) {
                    arrayList.add(0,1);
                    break;
                }
                else {
                    arrayList.set(arrayList.size() - 2 - i,arrayList.get(arrayList.size() - 2 - i) + 1);
                }
            }
            else if (arrayList.get(arrayList.size() - 1 - i) == 3) {
                arrayList.set(arrayList.size() - 1 - i, 0);
                if (arrayList.size() - 2 - i < 0) {
                    arrayList.add(0,1);
                    break;
                }
                else {
                    arrayList.set(arrayList.size() - 2 - i,arrayList.get(arrayList.size() - 2 - i) + 1);
                }
            }
        }
//        System.out.println(arrayList);

        StringBuilder sb = new StringBuilder();
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            if (arrayList.get(i) == 0) {continue;}
            if (arrayList.get(i) < 0){
                sb.append(" - ");
            }
            else {
                sb.append(" + ");
            }
            sb.append((int)Math.pow(3.0, arrayList.size() - i - 1));
        }
//        System.out.println(sb.toString());

        System.out.format("%d =%s%n", number, sb.toString());
    }
}