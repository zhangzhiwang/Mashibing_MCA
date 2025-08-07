package com.mashibing.dailyPractice.round6;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 已知给定一个大根堆数组，但是其中的一个元素被换掉了，重新调整这个堆
 */
public class AdjustHeap_1030 {
    private int[] arr;

    public AdjustHeap_1030(int[] arr) {
        this.arr = arr;
    }

    public void adjustHeap(int index) {
        if(index < 0 || index >= arr.length) {
            return;
        }

        heapInsert(index);
        heapify(index);
    }

    private void heapInsert(int index) {
        while (arr[index] > arr[index / 2]) {
            swap(index, index / 2);
            index = index / 2;
        }
    }

    private void heapify(int index) {
        int l = index * 2 + 1;
        while(l < arr.length) {
            int max = l + 1 < arr.length && arr[l + 1] > arr[l] ? l + 1 : l;
            max = arr[index] > arr[max] ? index : max;
            if(max == index) {
                break;
            }

            swap(max, index);
            index = max;
            l = index * 2 + 1;
        }
    }

    private void swap(int i, int j) {
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
        arr[1] = 0;
        new AdjustHeap_1030(arr).adjustHeap(1);
        DuiShuQiUtil.printArr(arr);
    }
}
