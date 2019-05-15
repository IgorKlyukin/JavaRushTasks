package com.javarush.task.task39.task3905;

/* 
Залей меня полностью
*/

public class Solution {
    public static void main(String[] args) {
        PhotoPaint photoPaint = new PhotoPaint();
        Color[][] colors = new Color[5][5];
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < colors[i].length; j++) {
                colors[j][i] = Color.GREEN;
            }
        }
        for (int i = 0; i < colors.length - 1; i++) {
            colors[i][0] = Color.BLUE;
            colors[2][i] = Color.BLUE;
        }

        for (Color[] col :
                colors) {
            for (Color colo :
                    col) {
                System.out.print(colo + " ");
            }
            System.out.println();
        }

        photoPaint.paintFill(colors, 1, 1, Color.ORANGE);

        System.out.println();
        for (Color[] col :
                colors) {
            for (Color colo :
                    col) {
                System.out.print(colo + " ");
            }
            System.out.println();
        }
    }
}
