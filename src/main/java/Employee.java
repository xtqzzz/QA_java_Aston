package main.java;

public class Employee {
    private String name;
    private String workingPosition;
    private String email;
    private String phoneNumber;
    private double salary;
    private int age;

    public Employee(String name, String workingPosition, String email, String phoneNumber, double salary, int age) {
        this.name = name;
        this.workingPosition = workingPosition;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }


    public String getInfo() {
        return (
                "имя ='" + name + '\'' +
                ", должность ='" + workingPosition + '\'' +
                ", email ='" + email + '\'' +
                ", номер телефона ='" + phoneNumber + '\'' +
                ", зарплата =" + salary +
                ", возраст =" + age);
    }
}
