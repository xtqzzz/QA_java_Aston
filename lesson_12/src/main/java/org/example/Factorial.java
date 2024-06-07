package org.example;

/**
 * Hello world!
 *
 */
public class Factorial {

    public static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        else {
            return n * factorial(n - 1);
        }
    }

    public static void main(String[] args) {
        int n = 1;
        System.out.println("Факториал " + n + " равен " + factorial(n));
    }
}