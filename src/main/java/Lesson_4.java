package main.java;

import java.util.Arrays;

public class Lesson_4 {
    public static void main(String[] args) {
        //printThreeWords();
        //checkSumSign();
        //printColor();
        //compareNumbers();
        //System.out.println(sumFromTenToTwenty(10,6));
        //isThePositiveNumber(-1);
        //System.out.println(isTheNegativeNumber(-7));
        //printLine("Hello World",4);
        //System.out.println(checkLeapYear(2024));
        //inversionOfArrayValues();
        //fillArrayFromOneToHundred();
        //doublingValuesInArrayIfValueLessThanSix();
        //fillTheDiagonalWithUnits();
        //returnArrayWithLenAndInitialValue(5,3);




    }

    //1
        static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    //2
    static void checkSumSign() {
        int a = -12;
        int b = 3;
        int result = a + b;
        String answer = result >= 0 ? "Сумма положительная" : "Сумма отрицательная";
        System.out.println(answer);
    }

    //3
    static void printColor() {
        int value = 500;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Жёлтый");
        } else {
            System.out.println("Зелёный");
        }
    }

    //4
    static void compareNumbers() {
        int a = 3;
        int b = 2;
        String result = a >= b ? "a >= b" : "a < b";
        System.out.println(result);
    }

    //5
    static boolean sumFromTenToTwenty(int a, int b) {
        int result = a + b;
        return result > 10 && result <= 20;
    }

    //6
    static void isThePositiveNumber(int n) {
        if (n >= 0) {
            System.out.println("Число: " + n + " положительное");
        } else {
            System.out.println("Число: " + n + " отрицательное");
        }
    }

    //7
    static boolean isTheNegativeNumber(int n) {
        return n >= 0;
    }

    //8
    static void printLine(String str, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(str);
        }
    }

    //9
    static boolean checkLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0) || (year % 400 == 0);
    }


    //10
    static void inversionOfArrayValues() {
        int[] array = new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1;
            } else if (array[i] == 1) {
                array[i] = 0;
            }
        }
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    //11
    static void fillArrayFromOneToHundred() {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i + 1;
        }
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    //12
    static void doublingValuesInArrayIfValueLessThanSix() {
        int[] array = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    //13
    static void fillTheDiagonalWithUnits() {
        int size = 7;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
            matrix[i][size - 1 - i] = 1;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    //14
    static int[] returnArrayWithLenAndInitialValue(int len, int initialValue){
        int[] array = new int[len];
        for(int i = 0; i < len; i++){
            array[i] = initialValue;
        }
        for(int i : array){
            System.out.print(i + " ");
        }
        return array;
    }
}
