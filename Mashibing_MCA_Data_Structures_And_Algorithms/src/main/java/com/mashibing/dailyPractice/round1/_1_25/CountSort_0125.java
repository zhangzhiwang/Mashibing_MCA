package com.mashibing.dailyPractice.round1._1_25;

import java.util.Arrays;

/**
 * 桶排序——计数排序
 * 注意：桶排序对样本数据是有要求的
 */
public class CountSort_0125 {
    public static void countSort(int[] arr) {
        if(arr == null || arr.length <= 1) {
            return;
        }

        int N = arr.length;
        int maxValue = -1;// 计数排序要求元素值必须为非负数
        for(int i = 0; i < N; i++) {
            maxValue = Math.max(arr[i], maxValue);
        }

        int[] help = new int[maxValue + 1];
        for(int i = 0; i < N; i++) {
            help[arr[i]]++;
        }

        int arrIndex = 0;
        for(int i = 0; i < help.length; i++) {
           if(help[i] == 0) {
               continue;
           }

           while(help[i] > 0) {
               arr[arrIndex++] = i;
               help[i]--;
           }
        }
    }

    // 对数器
    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
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
//        int[] arr = {3,0,7,2,5,8,7,6,5,8,7};
//        countSort(arr);
//        DuiShuQiUtil.printArr(arr);

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 150;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            countSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

//        int[] arr = generateRandomArray(maxSize, maxValue);
//        printArray(arr);
//        countSort(arr);
//        printArray(arr);
    }
}
