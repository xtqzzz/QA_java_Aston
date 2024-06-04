package main.task2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private Map<String, List<String>> directory;
    public PhoneBook() {
        directory = new HashMap<>();
    }
    public void add(String surname, String phoneNumber) {
        List<String> numbers = directory.getOrDefault(surname, new ArrayList<>());
        numbers.add(phoneNumber);
        directory.put(surname, numbers);
    }
    public List<String> get(String surname) {
        return directory.getOrDefault(surname, new ArrayList<>());
    }
}
