package com.mashibing.dailyPractice.round5;

import java.util.Arrays;

/**
 * 给定一个有序数组，返回该数组是否包含某个数字k，如：[1,2,3,5,7,8,9]，是否存在4，返回否。
 */
public class BinarySearch_0817 {
    public static boolean binarySearch(int[] arr, int k) {
        if(arr == null || arr.length == 0) {
            return false;
        }

        return recurse(arr, 0, arr.length - 1, k);
    }

    private static boolean recurse(int[] arr, int l, int r, int k) {
        if(l > r) {
            return false;
        }

        if(l == r) {
            return arr[l] == k;
        }

        int m = l + ((r - l) >> 1);
        if(arr[m] == k) {
            return true;
        } else if (arr[m] < k) {
            return recurse(arr, m + 1, r , k);
        } else {
            return recurse(arr,l, m - 1, k);
        }
    }

    // 对数器
    // for test
    public static boolean test(int[] sortedArr, int num) {
        for (int cur : sortedArr) {
            if (cur == num) {
                return true;
            }
        }
        return false;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
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
            if (test(arr, value) != binarySearch(arr, value)) {
                System.out.println("出错了！");
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
