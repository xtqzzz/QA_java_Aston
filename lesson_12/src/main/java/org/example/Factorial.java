package org.example;

/**
 * Hello world!
 *
 */
public class Factorial {

    public static long factorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Число должно быть неотрицательным");
        }
        long result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 1;
        System.out.println("Факториал " + n + " равен " + factorial(n));
    }
}