package main;

public class MyArraySizeException extends Exception {
    public MyArraySizeException() {
        super("Размер массива должен быть 4x4");
    }
}
