package main.java;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Vasya", "java developer", "vasya@mail.ru", "375442345678", 2300, 22);
        employees[1] = new Employee("Kolya", "IOS developer", "kolya@mail.ru", "375442345678", 2000, 29);
        employees[2] = new Employee("Gena", "python developer", "gena@mail.ru", "375443456789", 3500, 19);
        employees[3] = new Employee("Sasha", "c++ developer", "sasha@mail.ru", "375292485710", 4000, 40);
        employees[4] = new Employee("Petya", "designer", "petya@mail.ru", "375440987654", 1500, 34);
        for (Employee employee : employees) {
            System.out.println(employee.getInfo());
        }

        Park.Attraction park = new Park("Румянцевых-Паскевичей").new Attraction("Веселый аттракцион", "8:00-17:00",25);
        System.out.println(park.getName());
        System.out.println(park.getPrice());
        System.out.println(park.getTime());
    }
}
