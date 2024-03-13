package com.mashibing.dailyPractice.round2._0225;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 已知给定一个大根堆数组，但是其中的一个元素被换掉了，重新调整这个堆
 */
public class AdjustHeap_0225 {
    public static void adjustHeap(int[] arr, int index) {
        if (arr == null || arr.length == 0) {
            return;
        }

        heapInsert(arr, index);
        heapify(arr, index, arr.length);
    }

    private static void heapInsert(int[] arr, int index) {
        while (index != 0 && (index - 1) / 2 >= 0) {
            if (arr[index] - arr[(index - 1) / 2] > 0) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            } else {
                break;
            }
        }
    }

    private static void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            int largest = left + 1 < size && arr[left + 1] - arr[left] > 0 ? left + 1 : left;
            largest = arr[largest] - arr[index] > 0 ? largest : index;
            if (largest == index) {
                break;
            }

            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        int[] arr = {7, 4, 0, 1, 3, 2, 5};
        adjustHeap(arr, 2);
        DuiShuQiUtil.printArr(arr);
    }
}