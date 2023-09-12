package com.mashibing.array.sort;

/**
 * 插入排序
 * 课程：新手班课时6
 * 思路：见com.mashibing.preInEclipse.junior.array.sort.BubbleSort注释
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {3,2,1,3,5,6,7,8,9,1,2,3,4,5,3,2,9};
        SelectionSort.printArr(arr);
        insertSort(arr);
        SelectionSort.printArr(arr);
    }

    public static void insertSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;
        for(int newValueIndex = 1; newValueIndex < N; newValueIndex++) {
            for(int pre = newValueIndex - 1; pre >= 0 && arr[pre] > arr[pre + 1]; pre--) {
                swap(arr, pre, pre + 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
