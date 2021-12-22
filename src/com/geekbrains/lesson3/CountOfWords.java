package com.geekbrains.lesson3;

import java.util.HashMap;
import java.util.Map;

public class CountOfWords {

    public static void printMapOfUniqueWords(String text) {
        Map<String, Integer> countsOfWords = new HashMap<>();
        String[] textArray = text.replaceAll("[^А-Яа-я]+", "\s").split("\s");

        fillMap(countsOfWords, textArray);
        printMap(countsOfWords);
    }

    private static void fillMap(Map<String, Integer> map, String[] array) {
        for (String uniqueWord : array) {
            if (!map.containsKey(uniqueWord)) {
                map.put(uniqueWord, 1);
            } else {
                map.put(uniqueWord, map.get(uniqueWord) + 1);
            }
        }
    }

    private static void printMap(Map<String, Integer> map) {
        for (String key : map.keySet()) {
            System.out.println(key + " => " + map.get(key));
        }
    }
}