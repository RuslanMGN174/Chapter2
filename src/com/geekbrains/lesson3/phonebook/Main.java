package com.geekbrains.lesson3.phonebook;

public class Main {

    public static void main(String[] args) {
        Phonebook2 phonebook2 = Phonebook2.getInstance();

        phonebook2.add("Ivanov", "123");
        phonebook2.add("Ivanov", "234");
        phonebook2.add("Ivanov", "345");
        phonebook2.add("Ivanov", "456");
        phonebook2.add("Ivanov", "456");
        phonebook2.add("Ivanov", "456");

        phonebook2.get("Ivanov");
    }


}
