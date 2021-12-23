package com.geekbrains.lesson3.phonebook;

import java.util.HashMap;
import java.util.Map;

public class Phonebook {

    private final Map<String, String> phoneBook = new HashMap<>();
    private static Phonebook instance;

    private Phonebook() {}

    public static Phonebook getInstance() {
        if (instance == null) instance = new Phonebook();
        return instance;
    }

    public void add(String phoneNumber, String name) {
        phoneBook.putIfAbsent(phoneNumber, name);
    }

    public void get(String name) {
        phoneBook.entrySet().stream()
                .filter(s -> s.getValue().equals(name))
                .forEach(s -> System.out.printf("Имя: %s, номер телефона: %s%s",
                        name,
                        s.getKey(),
                        System.lineSeparator()));
    }
}
