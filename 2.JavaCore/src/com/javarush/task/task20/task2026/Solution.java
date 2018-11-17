package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };
        byte[][] a3 = new byte[][]{
                {1, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
        int count3 = getRectangleCount(a3);
        System.out.println("count = " + count3 + ". Должно быть 5");
    }

    public static int getRectangleCount(byte[][] a) {
        int n = 0;
        int ai, aj;
        try {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a.length; j++) {
                    if (a[i][j] == 1) {
                        n++;
                        ai = i;
                        aj = j;
                        virus(a, ai, aj);
                    }
                }
            }
        }
        catch (Exception e) {}
        return n;
    }

    private static void virus(byte[][] a, int i, int j){
        for (int k = i; k < a.length; k++) {
            for (int l = j; l < a.length; l++) {
                if (l == j && a[k][l] == 0) {return;}
                if (a[k][l] == 1) {
                    a[k][l] = 2;
                }
                else {
                    break;
                }
            }
        }
    }
}
