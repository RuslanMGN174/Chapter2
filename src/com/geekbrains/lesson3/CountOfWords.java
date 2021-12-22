package com.geekbrains.lesson3;

import java.util.HashMap;
import java.util.Map;

public class CountOfWords {

    // Метод для вывода массива слов на экран и подсчетом количества повторяющихся слов
    // в данном конкретном случае использовал HashMap как самый быстрый, но
    // может есть смысл периметовать метод в printHashMapOfUniqueWords и
    // соответственно добавить методы printLinkedMapOfUniqueWords и printTreeMapOfUniqueWords?
    // не знаю как лучше сделать.
    public static void printMapOfUniqueWords(String text) {
        Map<String, Integer> countsOfWords = new HashMap<>();
        String[] textArray = text.replaceAll("[^А-Яа-я]+", "\s").split("\s");

        printMap(getFilledMap(countsOfWords, textArray));
    }

    private static Map<String,Integer> getFilledMap(Map<String, Integer> map, String[] array) {
        for (String uniqueWord : array) {
            if (!map.containsKey(uniqueWord)) {
                map.put(uniqueWord, 1);
            } else {
                map.put(uniqueWord, map.get(uniqueWord) + 1);
            }
        }
        return map;
    }

    private static void printMap(Map<String, Integer> map) {
        for (String key : map.keySet()) {
            System.out.println(key + " => " + map.get(key));
        }
    }
}