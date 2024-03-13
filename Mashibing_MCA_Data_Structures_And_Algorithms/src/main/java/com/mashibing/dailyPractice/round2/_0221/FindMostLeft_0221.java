package com.mashibing.dailyPractice.round2._0221;

import com.mashibing.others.DuiShuQiUtil;

import java.util.Arrays;

/**
 * 给定一个有序数组，查找>=某个数的最左位置，比如：[1,2,3,3,3,6,7,8,9]，大于等于5的最左位置是5。
 */
public class FindMostLeft_0221 {
    public static int findMostLeft(int[] arr, int target) {
        if(arr == null || arr.length == 0) {
            return -1;
        }

        return recurse1(arr, 0, arr.length - 1, target);
    }

    private static int recurse1(int[] arr, int L, int R, int target) {
//        if(L > R) {
//            return -1;
//        }
        if(L == R) {
            return arr[L] >= target ? L : -1;
        }

        int M = L + ((R - L) >> 1);
        if(arr[M] < target) {
            if(M == R) {
                return -1;
            } else if(arr[M + 1] < target) {
                return recurse1(arr, M + 1, R, target);
            } else {
                return M + 1;
            }
        } else {
            if(M == L) {
                return M;
            } else if(arr[M - 1] >= target) {
                return recurse1(arr, L, M - 1, target);
            } else {
                return M;
            }
        }
    }

    // 对数器
    public static int testFindMostLeft(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= num) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
//        int[] arr = {1,2,3};
//        System.out.println(findMostLeft(arr,2));

        for(int i = 0; i < 100_0000; i++) {
            int maxValue = 10;
            int[] arr = DuiShuQiUtil.createRandArr(10, maxValue);
            Arrays.sort(arr);
            int randomValue = (int) (Math.random() * (maxValue + 1));
            int index1 = findMostLeft(arr, randomValue);
            int index2 = testFindMostLeft(arr, randomValue);
            if(index1 != index2) {
                System.out.println("错误：index1 = " + index1 + "，index2 = " + index2);
                DuiShuQiUtil.printArr(arr);
                System.out.println("randomValue = " + randomValue);
                break;
            }
        }

        System.out.println("成功！");
    }
}
