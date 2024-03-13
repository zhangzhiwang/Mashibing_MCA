package com.mashibing.dailyPractice.round3._No51_60;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 已知给定一个大根堆数组，但是其中的一个元素被换掉了，重新调整这个堆
 */
public class AdjustHeap_0310 {
    public static void resign(int[] arr, int index) {
        if (arr == null || arr.length == 0 || index < 0 || index >= arr.length) {
            return;
        }

        heapInsert(arr, index);
        heapify(arr, index);
    }

    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void heapify(int[] arr, int index) {
        int left = index * 2 + 1;
        while (left < arr.length) {
            int largest = left + 1 < arr.length && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }

            swap(arr, index, largest);
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
        int[] arr = new int[]{5, 4, 2, 1, 3};
        DuiShuQiUtil.printArr(arr);
        arr[3] = 10;
        resign(arr, 3);
        DuiShuQiUtil.printArr(arr);
    }
}
