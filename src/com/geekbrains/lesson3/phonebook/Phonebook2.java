package com.geekbrains.lesson3.phonebook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Phonebook2 {

    private final Map<String, Set<String>> phoneBook2 = new HashMap<>();
    private static Phonebook2 instance;

    private Phonebook2() {}

    public static Phonebook2 getInstance() {
        if (instance == null) instance = new Phonebook2();
        return instance;
    }

    public void add(String name, String phoneNumber) {
        phoneBook2.putIfAbsent(name, Stream.of(phoneNumber).collect(Collectors.toCollection(HashSet::new)));
        if (phoneBook2.containsKey(name)) phoneBook2.get(name).add(phoneNumber);
    }

    public void get(String name) {
        System.out.printf("%s = %s", name, phoneBook2.get(name));
    }
}
