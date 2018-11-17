package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] solution = new Solution[2];
        for (int i = 0; i < 2; i++) {
            solution[i] = new Solution();
            for (int j = 0; j < 2; j++) {
                solution[i].innerClasses[j] = solution[i].new InnerClass();
            }
        }
        return solution;
    }

    public static void main(String[] args) {

    }
}
