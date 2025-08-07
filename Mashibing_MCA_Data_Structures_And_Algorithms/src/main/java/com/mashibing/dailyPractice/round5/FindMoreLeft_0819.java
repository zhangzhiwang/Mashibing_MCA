package com.mashibing.dailyPractice.round5;

import java.util.Arrays;

/**
 * 给定一个有序数组，查找>=某个数的最左位置，比如：[1,2,3,3,3,6,7,8,9]，大于等于5的最左位置是5。
 */
public class FindMoreLeft_0819 {
    public static int findMoreLeft(int[] arr, int k) {
        if(arr == null || arr.length == 0) {
            return -1;
        }

        return recurse(arr, 0, arr.length - 1, k);
    }

    private static int recurse(int[] arr, int l, int r, int k) {
        if(l > r) {
            return -1;
        }

        int m = l + ((r - l) >> 1);
        if(arr[m] >= k) {
            if(m == l) {
                return m;
            } else if (arr[m - 1] < k) {
                return m;
            } else {
                return recurse(arr, l, m - 1, k);
            }
        } else {
            return  recurse(arr, m + 1, r, k);
        }
    }

    // 对数器
    // for test
    public static int test(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= value) {
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
            if (test(arr, value) != findMoreLeft(arr, value)) {
                printArray(arr);
                System.out.println(value);
                System.out.println(test(arr, value));
                System.out.println(findMoreLeft(arr, value));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
