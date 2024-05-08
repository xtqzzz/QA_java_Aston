package task1;

class Dog extends Animal {
    static int totalDogs = 0;

    public Dog(String name) {
        super(name);
        totalDogs++;
    }

    @Override
    void run(int distance) {
        if (distance <= 500) {
            System.out.println(name + " пробежал " + distance + " метров");
        } else {
            System.out.println(name + " не может пробежать " + distance + " метров");
        }
    }

    @Override
    void swim(int distance) {
        if (distance <= 10) {
            System.out.println(name + " проплыл " + distance + " метров");
        } else {
            System.out.println(name + " не может проплыть " + distance + " метров");
        }
    }
}