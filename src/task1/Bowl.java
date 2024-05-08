package task1;

class Bowl {
    private int food;

    public Bowl(int food) {
        this.food = food;
    }

    public boolean decreaseFood() {
        if (food > 0) {
            food--;
            return true;
        }
        return false;
    }

    public void addFood(int amount) {
        food += amount;
        System.out.println("В миску добавлено " + amount + " еды. Теперь в миске " + food + " еды");
    }

    public int getFood() {
        return food;
    }
}