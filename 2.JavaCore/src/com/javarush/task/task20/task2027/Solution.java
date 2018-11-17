package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> l = detectAllWords(crossword, "home", "same");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
        System.out.println(l);
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> listWord = new ArrayList<>();
        try {
            int n = crossword.length;
            int m = crossword[0].length;
            int sn = words.length;
            Word w;
            for (int i = 0; i < sn; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < m; k++) {
                        if (words[i].charAt(0) == crossword[j][k]) {
                            w = poisk(crossword, j, k, words[i]);
                            if (w != null) {
                                listWord.add(w);
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e){}
        return listWord;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }

    private static Word poisk(int[][] crossword, int i, int j, String s){
        char[] c = s.toCharArray();
        int n = crossword.length;
        int m = crossword[0].length;
        Word w  = null;
        for (int k = 0; k < 8; k++) {
            switch (k){
                case (0): {
                    if (i == 0) {
                        break;
                    }
                    w = help_poisk(c,crossword,i,j,s,-1,0);
                    if (w != null){
                        k = 8;
                    }
                    break;
                }
                case (1): {
                    if (i == 0 || j == m - 1){
                        break;
                    }
                    w = help_poisk(c,crossword,i,j,s,-1,1);
                    if (w != null){
                        k = 8;
                    }
                    break;
                }
                case (2): {
                    if (j == m - 1){
                        break;
                    }
                    w = help_poisk(c,crossword,i,j,s,0,1);
                    if (w != null){
                        k = 8;
                    }
                    break;
                }
                case (3): {
                    if (j == m - 1 || i == n - 1){
                        break;
                    }
                    w = help_poisk(c,crossword,i,j,s,1,1);
                    if (w != null){
                        k = 8;
                    }
                    break;
                }
                case (4): {
                    if (i == n - 1){
                        break;
                    }
                    w = help_poisk(c,crossword,i,j,s,1,0);
                    if (w != null){
                        k = 8;
                    }
                    break;
                }
                case (5): {
                    if (i == n - 1 || j == 0){
                        break;
                    }
                    w = help_poisk(c,crossword,i,j,s,1,-1);
                    if (w != null){
                        k = 8;
                    }
                    break;
                }
                case (6): {
                    if (j == 0){
                        break;
                    }
                    w = help_poisk(c,crossword,i,j,s,0,-1);
                    if (w != null){
                        k = 8;
                    }
                    break;
                }
                case (7): {
                    if (i == 0 || j == 0){
                        break;
                    }
                    w = help_poisk(c,crossword,i,j,s,-1,-1);
                    if (w != null){
                        k = 8;
                    }
                    break;
                }
                default: break;
            }
        }
        return w;
    }

    private static Word help_poisk(char[] c, int[][]crossword, int i, int j, String s, int i1, int j1){
        Word w = null;
        int n = c.length - 1;
        int m = crossword.length;
        int m1 = crossword[0].length;
        if (i + n*i1 >= 0 && i + n*i1 < m && j + n*j1 >= 0 && j + n*j1 < m1) {
            for (int l = 1; l < n + 1; l++) {
                if (crossword[i + l * i1][j + l * j1] != c[l]) {
                    break;
                }
                if (l == n) {
                    w = new Word(s);
                    w.setStartPoint(j, i);
                    w.setEndPoint(j + l * j1, i + l * i1);
                }
            }
        }
        return w;
    }
}
