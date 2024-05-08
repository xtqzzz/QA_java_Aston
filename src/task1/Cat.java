package task1;

class Cat extends Animal {
    static int totalCats = 0;
    private boolean isFull = false;

    public Cat(String name) {
        super(name);
        totalCats++;
    }

    @Override
    void run(int distance) {
        if (distance <= 200) {
            System.out.println(name + " пробежал " + distance + " метров");
        } else {
            System.out.println(name + " не может пробежать " + distance + " метров");
        }
    }

    @Override
    void swim(int distance) {
        System.out.println(name + " не умеет плавать");
    }

    public void eatFromBowl(Bowl bowl) {
        if (bowl.getFood() > 0 && !isFull) {
            isFull = bowl.decreaseFood();
            if (isFull) {
                System.out.println(name + " поел и теперь сыт");
            } else {
                System.out.println(name + " не хватило еды");
            }
        }
    }

    public boolean isFull() {
        return isFull;
    }
}
