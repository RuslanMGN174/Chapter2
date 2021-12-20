package com.geekbrains.lesson2;

public class Main {

    public static void main(String[] args) {

        String[][] array = new String[][]{
                {"5", "7", "3", "17"},
                {"7", "0", "1", "12"},
                {"8", "1", "2", "r"},
                {"5", "7", "3"}};

        try {
            testException(array);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }

    static void testException(String[][] array) {
        for (String[] strings : array) {
            if (array.length != 4 || strings.length != 4)
                throw new MyArraySizeException("Не корректный размер массива. Необходимо подавать массив размером 4х4");
        }

        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j].matches("[-+]?\\d+")) {
                    sum += Integer.parseInt(array[i][j]);
                } else {
                    throw new MyArrayDataException(
                            String.format("Значение ячейки row(%d), col(%d) не является числом", i, j));
                }
            }
        }
        System.out.println(sum);
    }
}
