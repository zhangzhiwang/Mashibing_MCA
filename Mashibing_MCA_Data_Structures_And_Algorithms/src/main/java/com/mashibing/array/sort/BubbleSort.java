package com.mashibing.array.sort;

/**
 * 冒泡排序
 * 课程：新手班课时6
 * 思路：见com.mashibing.preInEclipse.junior.array.sort.BubbleSort注释
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3,2,1,3,5,6,7,8,9,1,2,3,4,5,3,2,9};
        SelectionSort.printArr(arr);
        bubbleSort(arr);
        SelectionSort.printArr(arr);
    }

    public static void bubbleSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;
        for(int end = N - 1; end >= 0; end--) {
            for(int left = 0; left <= end - 1; left ++) {
                if(arr[left] > arr[left + 1]) {
                    swap(arr, left, left + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
