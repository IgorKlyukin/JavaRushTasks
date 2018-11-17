package com.javarush.task.task20.task2025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/*
Алгоритмы-числа
*/
public class Solution {
    public static long[] getNumbers(long N) {
        long[] result = null;
        if (N > 0) {
            try {
                ArrayList<Long> arrayResult = new ArrayList<>();

                int n = 10, m;
                byte[] NMassiv;
                NMassiv = long_in_Array_not_sort(N);
                m = NMassiv.length;
                long[] arrayList = new long[n];
                for (int i = 0; i < n; i++) {
                    arrayList[i] = i;
                }
                int[] a;
                long sum, sum1;
                byte[] SumMassiv;

                for (byte i = 1; i <= m; i++) {
                    a = new int[i];
                    do {
                        sum = 0;
                        sum1 = 0;
                        for (byte j = 0; j < i; j++) {
                            sum += arrayList[a[j]];
                        }
                        SumMassiv = long_in_Array_not_sort(sum);
                        if (i == SumMassiv.length) {
                            for (int j = 0, k = SumMassiv.length; j < k; j++) {
                                sum1 += arrayList[SumMassiv[j]];
                            }
                            if (sum == sum1) {
                                if (!(sum == 0 || sum == 1) || i == 1) {
                                    if (sum < N) {
                                        arrayResult.add(sum);
                                    }
                                }
                            }
                        }
                    } while (NextSet(a, n - 1, i));
                    power(arrayList);
                }
                Collections.sort(arrayResult);
                int tmp = arrayResult.size();
                result = new long[tmp - 1];
                for (int i = 1; i < tmp; i++) {
                    result[i - 1] = arrayResult.get(i);
                }
            }
            catch (Exception e){}
        }
        return result;
    }

    private static void power(long[] a) {
        for (byte i = 0; i < 10; i++) {
            a[i] *= i;
        }
    }

    private static boolean NextSet(int[] a, int n, int m) {
        int j = m - 1;
        while (j >= 0 && a[j] == n) j--;
        if (j < 0) return false;
        if (a[j] >= n)
            j--;
        a[j]++;
        if (j == m - 1) return true;
        for (int k = j + 1; k < m; k++)
            a[k] = a[j];
        return true;
    }

    private static byte[] long_in_Array_not_sort(long N) {
        byte[] arrayList;
        if (N == 0) {
            arrayList = new byte[1];
        } else {
            byte[] a = new byte[20];
            byte b = 0;
            while (N > 0) {
                a[b] = (byte) (N % 10);
                N /= 10;
                b++;
            }
            arrayList = new byte[b];
            for (int i = 0; i < b; i++) {
                arrayList[i] = a[i];
            }
        }
        return arrayList;
    }

    private static void Sortir(long[] array, int start, int end) {
        if (end > start) {
            int middle = (start + end) / 2;
            Sortir(array, start, middle);
            Sortir(array, middle + 1, end);
            merge(array, start, middle, middle + 1, end);
        }
    }

    private static void merge(long[] a, int start, int middle, int middlee, int end) {
        int m = end - start + 1, i = 0;
        int s = start;
        long[] t = new long[m];
        while (start <= middle || middlee <= end) {
            if (middlee > end || start <= middle && a[start] <= a[middlee]) {
                t[i] = a[start];
                start++;
            } else {
                t[i] = a[middlee];
                middlee++;
            }
            i++;
        }
        for (int j = 0; j < m; j++) {
            a[s + j] = t[j];
        }
    }

    public static void main(String[] args) {
//        Date date = new Date();
//        long[] l = getNumbers(Long.MAX_VALUE);
//
//        System.out.println((new Date()).getTime() - date.getTime());
//        for (int i = 0; i < l.length; i++) {
//            System.out.println(i + " " + l[i]);
//        }
    }
}