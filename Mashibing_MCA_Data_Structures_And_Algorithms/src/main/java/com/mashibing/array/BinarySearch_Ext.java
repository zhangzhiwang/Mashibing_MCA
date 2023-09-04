package com.mashibing.array;

import com.mashibing.others.DuiShuQi;

import java.util.Arrays;

/**
 * 二分法题目2
 * 题目：找到一个有序数组中>=一个值的最左位置
 * 课程：新手班课时17
 * 思路：见com.mashibing.preInEclipse.junior.array.BinarySearch_Ext注释
 */
public class BinarySearch_Ext {
    public static void main(String[] args) {
        for(int i = 0; i < 100_0000; i++) {
            int maxValue = 10;
            int[] arr = DuiShuQi.createRandArr(10, maxValue);
            Arrays.sort(arr);
            int randomValue = (int) (Math.random() * (maxValue + 1));
            int index1 = findMostLeft(arr, randomValue);
            int index2 = testFindMostLeft(arr, randomValue);
            if(index1 != index2) {
                System.out.println("错误：index1 = " + index1 + "index2 = " + index2);
                DuiShuQi.printArr(arr);
                System.out.println("randomValue = " + randomValue);
                break;
            }
        }

        System.out.println("成功！");
    }

    public static int findMostLeft(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;// 索引为-1代表没有找到
        }

        int L = 0;
        int R = arr.length - 1;
        int tmpIndex = -1;// tmp用于标记每一次二分时找到的最左位置，初始值一定是-1而不能是0
        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] < num) {
                L = mid + 1;
            } else {// arr[mid] >= num
                R = mid - 1;
                tmpIndex = mid;// 只有arr[mid] >= num时才更新tmpIndex
            }
        }

        return tmpIndex;
    }

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
}
