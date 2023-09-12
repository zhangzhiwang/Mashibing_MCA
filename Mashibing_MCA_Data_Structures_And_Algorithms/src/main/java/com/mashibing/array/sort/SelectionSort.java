package com.mashibing.array.sort;

/**
 * 选择排序
 * 课程：新手班课时5
 * 思路：见com.mashibing.preInEclipse.junior.array.sort.SelectionSort注释
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {3,2,1,3,5,6,7,8,9,1,2,3,4,5,3,2,9};
        printArr(arr);
        selectionSort(arr);
        printArr(arr);
    }

    public static void selectionSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;
        for(int i = 0; i < N; i++) {
            int minValueIndex = i;
            for(int j = i + 1; j < N; j++) {
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            swap(arr, i, minValueIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

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
