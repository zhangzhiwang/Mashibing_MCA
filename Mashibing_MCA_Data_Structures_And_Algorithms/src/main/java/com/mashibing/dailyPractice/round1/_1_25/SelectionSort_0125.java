package com.mashibing.dailyPractice.round1._1_25;

import com.mashibing.others.DuiShuQiUtil;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectionSort_0125 {
    public static void selectionSort(int[] arr) {
        if(arr == null || arr.length <= 1) {
            return;
        }

        int N = arr.length;
        for(int i = 0; i < N; i++) {
            int minimum = arr[i];
            int minIndex = i;
            for(int j = i + 1; j < N; j++) {
                if(arr[j] < minimum) {
                    minimum = arr[j];
                    minIndex = j;
                }
            }
//            if(i != minIndex) {
                swap(arr, i, minIndex);
//            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        // 这种算法的前提条件是交换的两个数可以相同，但是必须要指向两个不同的内存区域，如果引用a和b指向相同的内存区域，那么最后a和b都会被刷成0
        if(i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];

//        int tmp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = tmp;
    }

    // 对数器
// for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random()   [0,1)
        // Math.random() * N  [0,N)
        // (int)(Math.random() * N)  [0, N-1]
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            // [-? , +?]
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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
//        String[] arr = {"1","2","3"};
//        swap(arr, 1, 2);
//        DuiShuQiUtil.printArr(arr);

        int [] arr = new int[]{9,8,7,3,4,5,6,2,1,1,3};
        selectionSort(arr);
        DuiShuQiUtil.printArr(arr);

//        Character i1 = 1;
//        Character i2 = 1;
//        System.out.println(i1 ^ i2);// byte short char int long

        // 对数器验证
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            selectionSort(arr1);
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
//        selectionSort(arr);
//        printArray(arr);
    }
}
