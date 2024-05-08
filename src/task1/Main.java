package task1;

public class Main {
    public static void main(String[] args) {
        Cat[] cats = {new Cat("Барсик"), new Cat("Мурзик"), new Cat("Васька")};
        Bowl bowl = new Bowl(10);

        for (Cat cat : cats) {
            cat.eatFromBowl(bowl);
            System.out.println(cat.name + " сытость: " + cat.isFull());
        }

// Добавление еды в миску
        bowl.addFood(5);

// Подсчет животных
        System.out.println("Всего животных: " + Animal.totalAnimals);
        System.out.println("Всего собак: " + Dog.totalDogs);
        System.out.println("Всего котов: " + Cat.totalCats);
    }
}