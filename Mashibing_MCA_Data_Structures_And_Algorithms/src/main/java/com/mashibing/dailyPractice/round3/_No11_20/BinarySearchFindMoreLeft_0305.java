package com.mashibing.dailyPractice.round3._No11_20;

import java.util.Arrays;

/**
 * 给定一个有序数组，查找>=某个数的最左位置。比如：[1,2,3,3,3,6,7,8,9]，大于等于5的最左位置是5。
 */
public class BinarySearchFindMoreLeft_0305 {
    public static int binarySearchFindMoreLeft(int[] arr, int target) {
        if(arr == null || arr.length == 0) {
            return -1;
        }

        return recurse(arr, 0, arr.length - 1, target);
    }

    private static int recurse(int[] arr, int L, int R, int target) {
//        if(L > R) {
//            return -1;
//        }
        if(L == R) {
            return arr[L] >= target ? L : -1;
        }

        int M = L + ((R - L) >> 1);
        if(arr[M] < target) {
            return recurse(arr, M + 1, R, target);
        } else {
            if(M == L) {
                return M;
            } else if(arr[M - 1] >= target) {
                return recurse(arr, L, M - 1, target);
            } else {
                return M;
            }
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
//        int[] arr = {1,2,3,3,3,6,9,11,13};
//        int k = 19;
//        System.out.println(binarySearchFindMoreLeft(arr, k));

        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != binarySearchFindMoreLeft(arr, value)) {
                printArray(arr);
                System.out.println(value);
                System.out.println(test(arr, value));
                System.out.println(binarySearchFindMoreLeft(arr, value));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
