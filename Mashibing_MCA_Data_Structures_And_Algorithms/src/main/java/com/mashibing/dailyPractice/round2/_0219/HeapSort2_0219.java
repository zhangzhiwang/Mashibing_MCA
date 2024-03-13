package com.mashibing.dailyPractice.round2._0219;

import com.mashibing.others.DuiShuQiUtil;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort2_0219 {
    public static void heapSort2(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }

        int size = arr.length;
        for(int i = size - 1; i >= 0; i--) {
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
            largest = arr[largest] > arr[index] ? largest : index;
            if(largest == index) {
                break;
            }

            swap(arr, index, largest);
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

    // 对数器
    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
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
//        int[] arr = {3,2,5,7,6,5,8};
//        heapSort2(arr);
//        DuiShuQiUtil.printArr(arr);

        // 默认小根堆
//        PriorityQueue<Integer> heap = new PriorityQueue<>();
//        heap.add(6);
//        heap.add(8);
//        heap.add(0);
//        heap.add(2);
//        heap.add(9);
//        heap.add(1);
//
//        while (!heap.isEmpty()) {
//            System.out.println(heap.poll());
//        }

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort2(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
