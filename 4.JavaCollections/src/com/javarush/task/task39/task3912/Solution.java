package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {
        int[][] max = new int[][]{{0,0,0,0,0,0},
                {1,0,0,0,1,0},
                {1,1,1,1,1,0},
                {1,1,1,1,0,0},
                {1,1,1,1,0,1},
                {1,1,1,1,0,0}};
        System.out.println(maxSquare(max));
    }

    public static int maxSquare(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 1)
                    matrix[i][j] = Math.min(matrix[i][j - 1], Math.min(matrix[i - 1][j], matrix[i - 1][j - 1])) + 1;

                if (matrix[i][j] > max)
                    max = matrix[i][j];
            }
        }

        return max * max;
    }
}
