package task2;

public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(3, "Чёрный", "Белый");
        Shape rectangle = new Rectangle(9, 6, "Красный", "Фиолетовый");
        Shape triangle = new Triangle(2, 5, 2, "Розовый", "Бежевый");

        circle.printCharacteristics();
        rectangle.printCharacteristics();
        triangle.printCharacteristics();
    }
}