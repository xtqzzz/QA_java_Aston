package task2;

public interface Shape {
    double getArea();
    double getPerimeter();
    String getFillColor();
    String getBorderColor();

    default void printCharacteristics() {
        System.out.println("[ Периметр: " + getPerimeter() +
                ", Площадь: " + getArea() +
                ", Цвет фона: " + getFillColor() +
                ", Цвет границ: " + getBorderColor() + " ]");
    }
}

