package com.mashibing.dailyPractice.round4;

import com.mashibing.others.DuiShuQiUtil;
import com.sun.org.apache.bcel.internal.generic.SWAP;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort_0720 {
    public static void heapSort(int[] arr) {
        if(arr == null || arr.length == 0) {
            return;
        }

        int heapSize = arr.length;
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, heapSize);
        }

        while (heapSize > 0) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }
    }

    private static void heapify(int[] arr, int i, int heapSize) {
        int left = 2 * i + 1;
        while (left < heapSize) {
            int largestIndex = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
            largestIndex = arr[largestIndex] > arr[i] ? largestIndex : i;
            if(largestIndex == i) {
                break;
            }

            swap(arr, i, largestIndex);
            i = largestIndex;
            left = 2 * i + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if(i == j) {
            return;
        }

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    // 对数器
    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
