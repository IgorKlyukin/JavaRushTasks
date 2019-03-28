package com.javarush.task.task34.task3404;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
//        solution.recurse("1+(1+(1+1)*(1+1))*(1+1)+1", 0); //expected output 12 8
//        solution.recurse("2*(589+((2454*0.1548/0.01*(-2+9^2))+((25*123.12+45877*25)+25))-547)", 0); //expected output 8302231.36 14
//        solution.recurse("0.000025+0.000012", 0); //expected output 0 1
//        solution.recurse("2^2^2^2", 0); //expected output 65536 3
//        solution.recurse("sin(30)", 0); //expected output 0.5 1
//        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); // expected output 0.5 6
//        solution.recurse("tan(2025 ^ 0.5)", 0); // expected output 1 2
//        solution.recurse("tan(45)", 0); // expected output 1 1
//        solution.recurse("tan(-45)", 0); //expected output -1 2
//        solution.recurse("0.305", 0); //expected output 0.3 0
//        solution.recurse("0.3051", 0); //expected output 0.31 0
//        solution.recurse("(0.3051)", 0); //expected output 0.31 0
//        solution.recurse("tan(44+sin(89-cos(180)^2))", 0); //expected output 1 6
//        solution.recurse("-2+(-2+(-2)-2*(2+2))", 0); //expected output -14 8
//        solution.recurse("sin(80+(2+(1+1))*(1+1)+2)", 0); //expected output 1 7
//        solution.recurse("1+4/2/2+2^2+2*2-2^(2-1+1)", 0); //expected output 6 11
//        solution.recurse("10-2^(2-1+1)", 0); //expected output 6 4
//        solution.recurse("2^10+2^(5+5)", 0); //expected output 2048 4
//        solution.recurse("1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1" , 0); //expected output 72.96 8
//        solution.recurse("-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)" , 0); //expected output -3 16
//        solution.recurse("cos(3 + 19*3)" , 0); //expected output 0.5 3
//        solution.recurse("(-1 + (-2))" , 0); //expected output  -3 3
//        solution.recurse("-sin(2*(-5+1.5*4)+28)" , 0); //expected output -0.5 7
//        solution.recurse("-(-22+22*2)", 0); //expected output -22 4
//        solution.recurse("-2^(-2)", 0); //expected output -0.25 3
//        solution.recurse("-(-2^(-2))+2+(-(-2^(-2)))", 0); //expected output 2.5 10
//        solution.recurse("(-2)^2", 0); //4 2
//        solution.recurse("-2^2", 0); //-4 2
//        solution.recurse("(-2)*(-2)", 0); //expected output 4 3
//        solution.recurse("(-2)/(-2)", 0); //expected output 1 3
//        solution.recurse("sin(-30)", 0); //expected output -0.5 2
//        solution.recurse("cos(-30)", 0); //expected output 0.87 2
//        solution.recurse("tan(-30)", 0); //expected output -0.58 2
//        solution.recurse("2+8*(9/4-1.5)^(1+1)", 0); //expected output 6.5 6
//        solution.recurse("0.005", 0); //expected output 0.01 0
//        solution.recurse("0.0049", 0); //expected output 0 0
//        solution.recurse("0+0.304", 0); //expected output 0.3 1
//        solution.recurse("sin(45) - cos(45)", 0); //expected output 0 3
//        solution.recurse("0/(-3)", 0); //0 2
    }

    public void recurse(final String expression, int countOperation) {
        //убераем пробелы
        String str = expression.replaceAll(" ", "");

        //количество операций
        if (countOperation == 0)
            countOperation = str.split("[\\+\\-\\*\\/\\^]|sin|cos|tan").length - 1;

        //выделение области действия, то есть скобок
        String substr;
        if (str.contains("("))
            substr = str.substring(str.lastIndexOf("(") + 1, str.indexOf(")", str.lastIndexOf("(")));
        else
            substr = str;

        //это только число?
        if (substr.matches("^-?\\d+(\\.\\d+)?$")) {
            //мы закончили здесь
            //осталось избавиться от лишних скобок и научится работать с sin, cos, tan
            switch (str.length() - substr.length()) {
                case 0: {
                    NumberFormat format = new DecimalFormat("#.##");
                    System.out.println(String.format("%s %d", format.format(Double.parseDouble(str)),countOperation));
                    return;
                }
                default: {
                    int temp = str.lastIndexOf("(");
                    if (temp > 2) {
                        if (str.substring(temp - 3, temp).equals("sin")) {
                            str = str.replace("sin(" + substr + ")", "" + new BigDecimal(Math.sin(Math.toRadians(Double.parseDouble(substr)))).setScale(4, RoundingMode.HALF_UP).doubleValue());
                        } else if (str.substring(temp - 3, temp).equals("cos")) {
                            str = str.replace("cos(" + substr + ")", "" + new BigDecimal(Math.cos(Math.toRadians(Double.parseDouble(substr)))).setScale(4, RoundingMode.HALF_UP).doubleValue());
                        } else if (str.substring(temp - 3, temp).equals("tan")) {
                            str = str.replace("tan(" + substr + ")", "" + new BigDecimal(Math.tan(Math.toRadians(Double.parseDouble(substr)))).setScale(4, RoundingMode.HALF_UP).doubleValue());
                        } else {
//                            if (Double.parseDouble(substr) < 0){
//                                temp = 0;
//                                for (;;) {
//                                    if (str.indexOf(substr + ")", temp) + substr.length() + 1 < str.length())
//                                    {
//                                        if (str.charAt(str.indexOf(substr + ")", temp) + substr.length() + 1) == '^') {
//                                            System.out.println("hello");
//                                            temp = str.indexOf(substr + ")", temp) + substr.length() + 1;
//                                            String strtemp = "";
//                                            while (("" + str.charAt(temp)).matches("[\\.\\d]")) {
//                                                strtemp += str.charAt(temp);
//                                                temp++;
//                                            }
//                                        }
//                                    }
//                                    else
//                                        break;
//                                }
//                            }
                            str = str.replace("(" + substr + ")", substr);
                        }
                    }
                    else
                        str = str.replace("(" + substr + ")", substr);
                }

            }
            //проверить на тестовых примерах
        }
        else {


            //составление списка чисел и операций
            ArrayList<String> number = new ArrayList<>(Arrays.asList(substr.split("[\\+\\-\\*\\/\\^]")));
            ArrayList<String> symbol = new ArrayList<>(Arrays.asList(substr.split("\\d+\\.?\\d*")));

            //борьба с минусами
            int t = 0;
            for (int i = 0; i < number.size(); i++) {
                if (number.get(i).equals("")) {
                    number.remove(i);
                    number.set(i, "-" + number.get(i));
                    if (symbol.get(i - t).length() == 2)
                        symbol.set(i - t, symbol.get(i - t).substring(0,1));
                    else {
                        t++;
                        symbol.remove(i);
                    }
                }
            }

            //очистка мусора
            if (symbol.size() != 0 && symbol.get(0).equals(""))
                symbol.remove(0);

            //операции по приоритету
            //возведение в степень
            if (symbol.size() != 0 && symbol.contains("^")) {
                for (int i = symbol.size() - 1; i >= 0; i--) {
                    if (symbol.get(i).equals("^")) {
                        number.set(i, "" + new BigDecimal(Math.pow(Double.parseDouble(number.get(i)), Double.parseDouble(number.get(i + 1)))).setScale(4, RoundingMode.HALF_UP).doubleValue());
                        number.remove(i + 1);
                        symbol.remove(i);
                    }
                }
            }

            //умножение и деление
            if (symbol.size() != 0 && (symbol.contains("*") || symbol.contains("/"))) {
                for (int i = 0; i < symbol.size(); i++) {
                    if (symbol.get(i).equals("*")) {
                        number.set(i, "" + new BigDecimal(Double.parseDouble(number.get(i)) * Double.parseDouble(number.get(i + 1))).setScale(4, RoundingMode.HALF_UP).doubleValue());
                        number.remove(i + 1);
                        symbol.remove(i);
                        i--;
                    } else if (symbol.get(i).equals("/")) {
                        number.set(i, "" + new BigDecimal(Double.parseDouble(number.get(i)) / Double.parseDouble(number.get(i + 1))).setScale(4, RoundingMode.HALF_UP).doubleValue());
                        number.remove(i + 1);
                        symbol.remove(i);
                        i--;
                    }
                }
            }

            //сложение и вычитание
            if (symbol.size() != 0 && (symbol.contains("+") || symbol.contains("-"))) {
                for (int i = 0; i < symbol.size(); i++) {
                    if (symbol.get(i).equals("+")) {
                        number.set(i, "" + new BigDecimal((Double.parseDouble(number.get(i)) + Double.parseDouble(number.get(i + 1)))).setScale(4, RoundingMode.HALF_UP).doubleValue());
                        number.remove(i + 1);
                        symbol.remove(i);
                        i--;
                    } else if (symbol.get(i).equals("-")) {
                        number.set(i, "" + new BigDecimal((Double.parseDouble(number.get(i)) - Double.parseDouble(number.get(i + 1)))).setScale(4, RoundingMode.HALF_UP).doubleValue());
                        number.remove(i + 1);
                        symbol.remove(i);
                        i--;
                    }
                }
            }

//        System.out.println(substr);
//        System.out.println(number);
//        System.out.println(symbol);
//        System.out.println(str);
            str = str.replace(substr, number.get(0));
//        System.out.println(str);
//        System.out.println();
        }
        recurse(str, countOperation);
        //implement
    }

    public Solution() {
        //don't delete
    }
}
