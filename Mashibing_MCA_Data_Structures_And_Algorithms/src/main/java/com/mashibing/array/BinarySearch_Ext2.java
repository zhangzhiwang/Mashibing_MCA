package com.mashibing.array;

import com.mashibing.others.DuiShuQi;

import java.util.Arrays;

/**
 * 二分法题目3
 * 题目：找到一个有序数组中<=一个值的最右位置
 * 课程：新手班课时17
 * 思路：见com.mashibing.preInEclipse.junior.array.BinarySearch_Ext注释
 */
public class BinarySearch_Ext2 {
    public static void main(String[] args) {
        for(int i = 0; i < 100_0000; i++) {
            int maxValue = 10;
            int[] arr = DuiShuQi.createRandArr(10, maxValue);
            Arrays.sort(arr);
            int randomValue = (int) (Math.random() * (maxValue + 1));
            int index1 = findMostRight(arr, randomValue);
            int index2 = testFindMostRight(arr, randomValue);
            if(index1 != index2) {
                System.out.println("错误：index1 = " + index1 + "index2 = " + index2);
                DuiShuQi.printArr(arr);
                System.out.println("randomValue = " + randomValue);
                break;
            }
        }

        System.out.println("成功！");
    }

    public static int findMostRight(int[] arr, int num) {
        if(arr == null || arr.length == 0) {
            return -1;
        }

        int L = 0;
        int R = arr.length - 1;
        int tmpIndex = -1;
        while(L <= R) {
            int mid = (L + R) / 2;
            if(arr[mid] <= num) {
                L = mid + 1;
                tmpIndex = mid;
            } else {// arr[mid] > num
                R = mid - 1;
            }
        }

        return tmpIndex;
    }

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
}
