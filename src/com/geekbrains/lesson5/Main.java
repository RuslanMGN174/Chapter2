package com.geekbrains.lesson5;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    private static final int size = 10000000;
    private static final int h = size / 2;
    private static final int arr2Size = size - h;
    private static volatile float[] arr = new float[size];

    public static void main(String[] args) {
        arrayCalculateTime();
        arraySplitAndCalculate();
    }

    static void arrayCalculateTime() {

        Arrays.fill(arr, 1);

        long start = System.nanoTime();

        arrayCalculate(arr);
        System.out.printf("array calculate time: %d", System.nanoTime() - start);
        System.out.print(System.lineSeparator());
    }

    private static void arrayCalculate(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    static void arraySplitAndCalculate() {
        
        float[] arr1 = new float[h];
        float[] arr2 = new float[arr2Size];

        printTimeOfArraySplit(arr1, arr2);
        multithreadCalculate(arr1, arr2);
        printTimeOfArrayMerge(arr1, arr2);

    }

    private static void printTimeOfArrayMerge(float[] arr1, float[] arr2) {
        long startMerge = System.nanoTime();

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, arr2Size, arr2Size);

        long endMerge = System.nanoTime();

        System.out.printf("array merge time: %d", endMerge - startMerge);
        System.out.print(System.lineSeparator());
    }

    private static void printTimeOfArraySplit(float[] arr1, float[] arr2) {
        long startSplit = System.nanoTime();

        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, arr2Size);

        long endSplit = System.nanoTime();

        System.out.printf("array split time: %d", endSplit - startSplit);
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
            System.out.printf("array multithreading calculate %s time: %d", text, task.get() - startArr1Calculate);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.print(System.lineSeparator());
    }
}
