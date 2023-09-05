package com.mashibing.array;

import com.mashibing.others.DuiShuQi;

/**
 * 二分查找——局部最小值问题
 * 题目：给定一个数组，该数组相邻的两项不相等，整体无序，找出任意一个局部最小值的位置。
 * 课程：新手班课时18
 * 思路：见com.mashibing.preInEclipse.junior.array.LocalLeastValue注释
 */
public class BinarySearch_LocalLeastValue {
    public static void main(String[] args) {
        int testTimes = 100_0000;
        int maxLen = 10;
        int maxValue= 10;
        for(int i = 0; i < testTimes; i++) {
            int[] arr = f(maxLen, maxValue);
//            if(!testF(arr)) {
//                DuiShuQi.printArr(arr);
//                System.out.println("f函数有误！");
//                break;
//            }
            int index = findLocalLeastValue(arr);
            if(!testFindLocalLeastValue(arr, index)) {
                DuiShuQi.printArr(arr);
                System.out.println("index = " + index);
                System.out.println("出错了！");
                break;
            }
        }

        System.out.println("测试结束！");
    }

    /**
     * 找出任意一个局部最小值
     *
     * @param arr
     * @return 返回的是局部最小值的索引
     */
    public static int findLocalLeastValue(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;// 位置为-1表示没有局部最小值
        }

        if (arr.length == 1) {
            return 0;// 如果数组只有一个元素，那么这个元素就是局部最小的
        }

        int L = 0;
        int R = arr.length - 1;
        while (L < R - 1) {// 注意这里循环的条件不是L<R，因为必须只有三个元素才能走此循环
            int mid = (L + R) / 2;
            if (arr[mid - 1] < arr[mid]) {
                R = mid - 1;
//                continue;
            } else if (arr[mid + 1] < arr[mid]) {
                L = mid + 1;
            } else {
                return mid;
            }
        }

        return arr[L] < arr[R] ? L : R;
    }

    /**
     * 生成题目给出的已知数组
     *
     * @param maxLen
     * @param maxValue
     * @return
     */
    public static int[] f(int maxLen, int maxValue) {
        // 首先生成一个随机数组
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];

        if(arr.length == 0) {
            return arr;
        }
        arr[0] = (int) (Math.random() * (maxValue + 1));
        for (int i = 1; i < len; i++) {
            do {
                arr[i] = (int) (Math.random() * (maxValue + 1));
            } while (arr[i] == arr[i - 1]);
        }

        return arr;
    }

    public static boolean testF(int[] arr) {
        if(arr == null || arr.length < 2) {
            return true;
        }

        for(int i = 1; i < arr.length; i++) {
            if(arr[i] == arr[i - 1]) {
                return false;
            }
        }

        return true;
    }

    public static boolean testFindLocalLeastValue(int[] arr, int localMinValueIndex) {
        if(arr == null || arr.length == 0) {
            return localMinValueIndex == -1;
        }

        if(arr.length == 1) {
            return localMinValueIndex == 0;
        }

        if(localMinValueIndex == 0) {
            return arr[localMinValueIndex] < arr[localMinValueIndex + 1];
        }

        if(localMinValueIndex == arr.length - 1) {
            return arr[localMinValueIndex - 1] > arr[localMinValueIndex];
        }

        return arr[localMinValueIndex] < arr[localMinValueIndex -1] && arr[localMinValueIndex] < arr[localMinValueIndex + 1];
    }
}
