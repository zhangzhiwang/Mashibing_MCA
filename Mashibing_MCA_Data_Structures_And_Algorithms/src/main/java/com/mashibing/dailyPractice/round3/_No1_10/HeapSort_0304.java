package com.mashibing.dailyPractice.round3._No1_10;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 堆排序
 */
public class HeapSort_0304 {
    public static void heapSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }

        int size = arr.length;
        for(int i = size; i >= 0; i--) {
            heapify(arr, i, size);
        }

        while(size > 0) {
            swap(arr, 0, --size);
            heapify(arr, 0, size);
        }
    }

    private static void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while(left < size) {
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] < arr[index] ? index : largest;
            if(largest == index) {
                break;
            }

            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if(i == j) {
            return;
        }

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,2,4,6};
        heapSort(arr);
        DuiShuQiUtil.printArr(arr);
    }
}
