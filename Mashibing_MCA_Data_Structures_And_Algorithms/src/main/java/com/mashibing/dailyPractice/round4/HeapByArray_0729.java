package com.mashibing.dailyPractice.round4;

/**
 * 用数组实现大根堆（小根堆），并实现添加元素和弹出元素
 * 本代码实现小根堆
 */
public class HeapByArray_0729 {
    private int[] arr;
    private int size;

    public HeapByArray_0729(int n) {
        if(n <= 0) {
            return;
        }

        arr = new int[n];
    }

    public void add(int i) {
        if (size == arr.length) {
            throw new RuntimeException("Heap is full!");
        }

        arr[size] = i;
        heapInsert(arr, size, size++);
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("Heap is empty!");
        }

        int result = arr[0];
        swap(arr, 0, --size);
        heapify(arr, 0, size);

        return result;
    }

    private void heapInsert(int[] arr, int i, int size) {
        while (arr[i] < arr[(i - 1) / 2]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private void heapify(int[] arr, int i, int size) {
        int l = i * 2 + 1;
        while (l < size) {
            int smallestIndex = l + 1 < size && arr[l + 1] < arr[l] ? l + 1 : l;
            smallestIndex = arr[smallestIndex] < arr[i] ? smallestIndex : i;
            if(smallestIndex == i) {
                break;
            }

            swap(arr, i, smallestIndex);
            i = smallestIndex;
            l = i * 2 + 1;
        }
    }

    private void swap(int[] arr, int i, int j) {
        if(i == j) {
            return;
        }

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        HeapByArray_0729 heap = new HeapByArray_0729(5);
        System.out.println("size = " + heap.size);
        System.out.println("-------------");
        heap.add(5);
        heap.add(4);
        heap.add(3);
        heap.add(2);
        heap.add(1);

        System.out.println("size = " + heap.size);
        while (heap.size > 0) {
            System.out.println(heap.pop());
        }
    }
}
