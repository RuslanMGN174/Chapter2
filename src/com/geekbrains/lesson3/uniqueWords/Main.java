package com.geekbrains.lesson3.uniqueWords;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String text = "Алисе наскучило сидеть с сестрой без дела на берегу реки; разок-другой она заглянула в книжку, которую читала сестра, но там не было ни картинок, ни разговоров.\n" + "\n" + "- Что толку в книжке, - подумала Алиса, - если в ней нет ни картинок, ни разговоров?\n" + "\n" + "Она сидела и размышляла, не встать ли ей и не нарвать ли цветов для венка; мысли ее текли медленно и несвязно - от жары ее клонило в сон.";

        printMapOfUniqueWords(text);
    }

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
        map.keySet().forEach(key -> System.out.println(key + " => " + map.get(key)));
    }
}
