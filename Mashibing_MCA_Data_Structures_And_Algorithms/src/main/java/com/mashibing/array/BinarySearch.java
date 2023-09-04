package com.mashibing.array;

import com.mashibing.others.DuiShuQi;

import java.util.Arrays;

/**
 * 在有序数组中找到某一个值
 * 课程：新手班课时16
 * 思路：见com.mashibing.preInEclipse.junior.array.BinarySearch注释
 */
public class BinarySearch {
    public static void main(String[] args) {
        int maxValue = 10;
        int[] arr = DuiShuQi.createRandArr(10, maxValue);
        int randomValue = (int) (Math.random() * (maxValue + 1));
        DuiShuQi.printArr(arr);
        Arrays.sort(arr);
        DuiShuQi.printArr(arr);
        System.out.println("randomValue = " + randomValue);

        if(!containsValue(arr, randomValue)) {
            System.out.println("不包含！");
        } else {
            System.out.println("包含");
        }

//        int[] arr = {0,2,8,3};
//        System.out.println(containsValue(arr, 1));
    }

    public static boolean containsValue(int[] arr, int targetValue) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        int L = 0;// 二分查找左边界的索引
        int R = arr.length - 1;// 二分查找右边界的索引
        while (L <= R) {
            int mid = (L + R) / 2;// 二分查找中间点的索引，转化成int时是向下取整
            if (arr[mid] == targetValue) {
                return true;
            } else if (arr[mid] < targetValue) {
                L = mid + 1;
            } else {// arr[mid] > targetValue
                R = mid - 1;
            }
        }

        return false;
    }
}
