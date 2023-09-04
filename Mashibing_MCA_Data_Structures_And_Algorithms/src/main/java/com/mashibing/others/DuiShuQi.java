package com.mashibing.others;

import com.mashibing.array.BubbleSort;
import com.mashibing.array.SelectionSort;

/**
 * 对数器
 */
public class DuiShuQi {
    public static void main(String[] args) {
//        int[] arr = {3, 2, 1, 3, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 3, 2, 9};
//        BubbleSort.bubbleSort(arr);
//
//        if (!isAsc(arr)) {
//            SelectionSort.printArr(arr);
//        } else {
//            System.out.println("准确无误");
//        }

//        System.out.println(arrContains(arr, 10));

        int[] arr1 = createRandArr(5, 10);
        printArr(arr1);
    }

    /**
     * 创建一个随机长度和值的数组
     * @param maxLen 随机长度的最大值
     * @param maxValue 随机值的最大值
     * @return
     */
    public static int[] createRandArr(int maxLen, int maxValue) {
        int len = (int)(Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for(int i = 0; i < len; i++) {
            arr[i] = (int)(Math.random() * (maxValue + 1));
        }

        return arr;
    }

    /**
     * 判断一个数组是否是升序的
     *
     * @param arr
     * @return
     */
    public static boolean isAsc(int[] arr) {
        if (arr == null) {
            return false;
        }

        if (arr.length < 2) {
            return true;
        }

        int tmp = arr[0];// 始假最开始位置的值是最小的
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < tmp) {// 后面的数比前面的小
                return false;
            }
            tmp = arr[i];// tmp往后移
        }

        return true;
    }

    /**
     * 判断数组是否至少包含一个指定值
     *
     * @param arr
     * @param targetValue
     * @return
     */
    public static boolean arrContains(int[] arr, int targetValue) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        for (int i : arr) {
            if (i == targetValue) {
                return true;
            }
        }

        return false;
    }

    /**
     * 数组里指定值的索引
     * @param arr
     * @param targetValue 返回-1代表不包含指定值
     * @return
     */
    public static int targetValueIndex(int[] arr, int targetValue) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == targetValue) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 打印数组
     * @param arr
     */
    public static void printArr(int[] arr) {
        if(arr == null || arr.length == 0) {
            System.out.println("Param is empty!");
        }

        for(int i : arr) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }
}
