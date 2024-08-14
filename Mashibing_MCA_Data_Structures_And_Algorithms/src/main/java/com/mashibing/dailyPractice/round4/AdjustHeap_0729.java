package com.mashibing.dailyPractice.round4;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 已知给定一个大根堆数组，但是其中的一个元素被换掉了，重新调整这个堆
 */
public class AdjustHeap_0729 {
    public static void adjustHeap(int[] arr, int i) {
        if(arr == null || arr.length == 0 || i < 0 || i >= arr.length) {
            return;
        }

        heapInsert(arr, i);
        heapify(arr, i, arr.length);
    }

    private static void heapInsert(int[] arr, int i) {
        while (arr[i] > arr[(i - 1) / 2]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private static void heapify(int[] arr, int i, int size) {
        int l = i * 2 + 1;
        while (l < size) {
            int largestIndex = l + 1 < size && arr[l + 1] > arr[l] ? l + 1 : l;
            largestIndex = arr[largestIndex] > arr[i] ? largestIndex : i;
            if(largestIndex == i) {
                break;
            }

            swap(arr, i, largestIndex);
            i = largestIndex;
            l = i * 2 + 1;
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
        int[] arr = new int[]{5, 4, 2, 1, 3};
        DuiShuQiUtil.printArr(arr);
        arr[0] = 0;
        adjustHeap(arr, 0);
        DuiShuQiUtil.printArr(arr);
    }
}
