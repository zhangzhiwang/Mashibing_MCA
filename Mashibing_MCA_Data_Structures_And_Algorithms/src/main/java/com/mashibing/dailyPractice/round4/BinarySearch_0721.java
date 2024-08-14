package com.mashibing.dailyPractice.round4;

import java.util.Arrays;

/**
 * 给定一个有序数组，返回该数组是否包含某个数字k，比如：[1,2,3,5,7,8,9]，是否存在4，返回否。
 */
public class BinarySearch_0721 {
    public static boolean binarySearch(int[] arr, int k) {
        if(arr == null || arr.length == 0) {
            return false;
        }

        int L = 0;
        int R = arr.length - 1;
        boolean result = false;
        while(L <= R) {
            int M = L + ((R - L) >> 1);
            if(arr[M] == k) {
                result = true;
                break;
            } else if (arr[M] > k) {
                R = M - 1;
                continue;// 其实去掉continue也行，就是写上好理解
            } else {
                L = M + 1;
                continue;
            }
        }

        return result;
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
