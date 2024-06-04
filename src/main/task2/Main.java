package main.task2;

class Main {
    public static void main(String[] args) {
        PhoneBook directory = new PhoneBook();

//Добавляем номера
        directory.add("Иванов", "12345");
        directory.add("Петров", "67890");
        directory.add("Сидоров", "13579");

//выводим номера
        System.out.println("Иванов: " + directory.get("Иванов"));
        System.out.println("Петров: " + directory.get("Петров"));
    }
}