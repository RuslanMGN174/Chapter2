package com.geekbrains.lesson5;

import java.util.Arrays;
import java.util.concurrent.*;

public class Main {

    private static final int SIZE = 10000000;
    private static final int ARR1_SIZE = SIZE / 2;
    private static final int ARR2_SIZE = SIZE - ARR1_SIZE;
    private static volatile float[] arr = new float[SIZE];

    public static void main(String[] args) {
        arrayCalculateTime();
        arraySplitAndCalculate();
    }

    static void arrayCalculateTime() {
        Arrays.fill(arr, 1);

        long start = System.nanoTime();

        arrayCalculate(arr);
        System.out.printf("array calculate time: %d", TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start));
        System.out.print(System.lineSeparator());
    }

    private static void arrayCalculate(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    static void arraySplitAndCalculate() {
        float[] arr1 = new float[ARR1_SIZE];
        float[] arr2 = new float[ARR2_SIZE];

        printTimeOfArraySplit(arr1, arr2);
        multithreadCalculate(arr1, arr2);
        printTimeOfArrayMerge(arr1, arr2);
    }

    private static void printTimeOfArrayMerge(float[] arr1, float[] arr2) {
        long startMerge = System.nanoTime();

        System.arraycopy(arr1, 0, arr, 0, ARR1_SIZE);
        System.arraycopy(arr2, 0, arr, ARR2_SIZE, ARR2_SIZE);

        long endMerge = System.nanoTime();

        System.out.printf("array merge time: %d", TimeUnit.NANOSECONDS.toMillis(endMerge - startMerge));
        System.out.print(System.lineSeparator());
    }

    private static void printTimeOfArraySplit(float[] arr1, float[] arr2) {
        long startSplit = System.nanoTime();

        System.arraycopy(arr, 0, arr1, 0, ARR1_SIZE);
        System.arraycopy(arr, ARR1_SIZE, arr2, 0, ARR2_SIZE);

        long endSplit = System.nanoTime();

        System.out.printf("array split time: %d", TimeUnit.NANOSECONDS.toMillis(endSplit - startSplit));
        System.out.print(System.lineSeparator());
    }

    private static void multithreadCalculate(float[] arr1, float[] arr2) {
        ExecutorService service = Executors.newCachedThreadPool();
        arrCalculateInThread(service, arr1, "arr1");
        arrCalculateInThread(service, arr2, "arr2");
        service.shutdown();
    }

    private static void arrCalculateInThread(ExecutorService service, float[] splitedArr, String text) {
        long startArr1Calculate = System.nanoTime();

        Future<Long> task = service.submit(()->{
            arrayCalculate(splitedArr);
            return System.nanoTime();
        });

        while (!task.isDone()){
            Thread.yield();
        }

        try {
            System.out.printf("array multithreading calculate %s time: %d",
                    text, TimeUnit.NANOSECONDS.toMillis(task.get() - startArr1Calculate));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.print(System.lineSeparator());
    }
}
