package com.mashibing.dailyPractice.round2._0221;

import com.mashibing.others.DuiShuQiUtil;

import java.util.Arrays;

/**
 * 给定一个有序数组，查找<=某个数的最右位置，[1,2,3,3,3,6,9,11,13]，小于等于7的最右位置是5。
 */
public class FindMostRight_0221 {
    public static int findMostRight(int[] arr, int target) {
        if(arr == null || arr.length == 0) {
            return -1;
        }

        return recurse(arr, 0, arr.length - 1, target);
    }

    private static int recurse(int[] arr, int L, int R, int target) {
        if(L == R) {
            return arr[L] <= target ? L : -1;
        }

        int M = L + ((R - L ) >> 1);
        if(arr[M] <= target) {
            if(M == R || arr[M + 1] > target) {
                return M;
            } else {
                return recurse(arr, M + 1, R, target);
            }
        } else {
            if(M == L) {
                return -1;
            } else if (arr[M - 1] <= target) {
                return M - 1;
            } else {
                return recurse(arr, L, M - 1, target);
            }
        }
    }

    // 对数器
    public static int testFindMostRight(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= num) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100_0000; i++) {
            int maxValue = 10;
            int[] arr = DuiShuQiUtil.createRandArr(10, maxValue);
            Arrays.sort(arr);
            int randomValue = (int) (Math.random() * (maxValue + 1));
            int index1 = findMostRight(arr, randomValue);
            int index2 = testFindMostRight(arr, randomValue);
            if(index1 != index2) {
                System.out.println("错误：index1 = " + index1 + "index2 = " + index2);
                DuiShuQiUtil.printArr(arr);
                System.out.println("randomValue = " + randomValue);
                break;
            }
        }

        System.out.println("成功！");
    }
}
