package task1;

abstract class Animal {
    static int totalAnimals = 0;
    protected String name;

    public Animal(String name) {
        this.name = name;
        totalAnimals++;
    }

    abstract void run(int distance);
    abstract void swim(int distance);
}