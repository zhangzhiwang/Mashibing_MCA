package com.mashibing.dailyPractice.round6;

import java.util.Arrays;

/**
 * 给定一个有序数组，查找<=某个数的最右位置。
 * [1,2,3,3,3,6,9,11,13]，小于等于7的最右位置是5。
 */
public class BinarySearchFindLessRight_1109 {
    public static int binarySearchFindLessRight(int[] arr, int k) {
        if(arr == null || arr.length == 0) {
            return -1;
        }

        return recurse(arr, 0, arr.length - 1, k);
    }

    private static int recurse(int[] arr, int l, int r, int k) {
        if(l > r) {
            return -1;
        }
        if(l == r) {
            return arr[l] <= k ? l : -1;
        }

        int m = l + ((r - l) >> 1);
        if(arr[m] > k) {
            return recurse(arr, l, m - 1, k);
        } else {
            if(arr[m + 1] > k) {
                return m;
            } else {
                return recurse(arr, m + 1, r, k);
            }
        }
    }

    // 对数器
    // for test
    public static int test(int[] arr, int value) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= value) {
                return i;
            }
        }
        return -1;
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
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != binarySearchFindLessRight(arr, value)) {
                System.out.println("arr:");
                printArray(arr);
                System.out.println("k = " + value);
                System.out.println("correct ans:" + test(arr, value));
                System.out.println("my ans:" + binarySearchFindLessRight(arr, value));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
