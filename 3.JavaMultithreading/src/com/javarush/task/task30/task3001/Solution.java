package com.javarush.task.task30.task3001;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
Конвертер систем счислений
*/
public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumerationSystemType._10, "6");
        Number result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._2);
        System.out.println(result);    //expected 110

        number = new Number(NumerationSystemType._16, "6df");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._8);
        System.out.println(result);    //expected 3337

        number = new Number(NumerationSystemType._16, "abcdefabcdef");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._16);
        System.out.println(result);    //expected abcdefabcdef
    }

    public static Number convertNumberToOtherNumerationSystem(Number number, NumerationSystem expectedNumerationSystem) {
        //напишите тут ваш код


        Map<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("" + i, i);
            if (i < 6)
                map.put(("" + (char)(65 + i)).toLowerCase(), 10 + i);
        }

        String string = number.getDigit();
        for (int i = 0, n = string.length(); i < n; i++) {
            if (map.containsKey("" + string.charAt(i)) && map.get("" + string.charAt(i)) < number.getNumerationSystem().getNumerationSystemIntValue()) {
                continue;
            }
            else {
                throw new NumberFormatException();
            }
        }

        BigInteger bi = new BigInteger(number.getDigit(), number.getNumerationSystem().getNumerationSystemIntValue());
        String ans = bi.toString(expectedNumerationSystem.getNumerationSystemIntValue());
        return new Number(expectedNumerationSystem, ans);
//        if (number.getNumerationSystem().equals(expectedNumerationSystem))
//            return number;
//
//        BigInteger digit = BigInteger.ZERO;
//        if (number.getNumerationSystem().getNumerationSystemIntValue() != 10) {
//            for (int i = 0, n = string.length(); i < n; i++) {
//                BigInteger exp = BigInteger.valueOf(number.getNumerationSystem().getNumerationSystemIntValue());
//                BigInteger mapBI = BigInteger.valueOf(map.get("" + string.charAt(i)));
//                exp = exp.pow( n - 1 - i);
//                mapBI = mapBI.multiply(exp);
//                digit = digit.add(mapBI);
//            }
//        }
//        else {
//            digit = BigInteger.valueOf(Integer.parseInt(string));
//        }
//
//        StringBuilder stringBuilder = new StringBuilder();
//        BigInteger expNumSys = BigInteger.valueOf(expectedNumerationSystem.getNumerationSystemIntValue());
//        while (digit.compareTo(expNumSys) >= 0){
//            int temp = digit.mod(expNumSys).intValue();
//            for (Map.Entry<String, Integer> entry : map.entrySet()) {
//                if (entry.getValue() == temp) {
//                    stringBuilder.append(entry.getKey());
//                    break;
//                }
//            }
//            digit = digit.divide(expNumSys);
//        }
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            if (entry.getValue() == digit.intValue()) {
//                stringBuilder.append(entry.getKey());
//                break;
//            }
//        }
//
//        return new Number(expectedNumerationSystem, stringBuilder.reverse().toString());
    }
}
