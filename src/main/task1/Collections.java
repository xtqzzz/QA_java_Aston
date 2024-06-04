package main.task1;
import java.util.*;

public class Collections {
    public static void main(String[] args) {
        String[] arrayWords = {"Никита", "Стас", "Гена", "Турбо", "Дюша Метёлкин", "Петрович", "Семёныч", "Никита", "Эдуард Суровый", "Эдуард Несуровый"};

//сделал подсчет количества каждого слова с помощью метода getOrDefault
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : arrayWords) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        System.out.println("Слова: ");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}