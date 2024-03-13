package com.mashibing.dailyPractice.round2._0225;

import com.mashibing.others.DuiShuQiUtil;

import java.util.Comparator;

/**
 * 用数组实现大根堆（小根堆），并实现添加元素和弹出元素
 */
public class HeapByArray_0225 {
    private int[] arr;
    private int size;
    private Comparator<Integer> com;

    public HeapByArray_0225(int len, Comparator<Integer> com) {
        this.arr = new int[len];
        this.com = com;
    }

    public void add(int num) {
        if(size == arr.length) {
            throw new RuntimeException("堆已满");
        }

        arr[size] = num;
        heapInsert(arr, size++);
    }

    public int pop() {
        if(size == 0) {
            throw new RuntimeException("堆已空");
        }

        int result = arr[0];
        swap(arr, 0, --size);
        heapify(arr, 0);

        return result;
    }

    private void heapInsert(int[] arr, int index) {
        while(index != 0 && (index - 1) / 2 >= 0) {
            if(com.compare(arr[index], arr[(index - 1) / 2]) <= 0) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            } else {
                break;
            }
        }
    }

    private void heapify(int[] arr, int index) {
        int left = index * 2 + 1;
        while (left < size) {
            int smallesOrLargest = left + 1 < size && com.compare(arr[left + 1], arr[left]) <= 0 ? left + 1 : left;
            smallesOrLargest = com.compare(arr[smallesOrLargest], arr[index]) <= 0 ? smallesOrLargest : index;
            if(smallesOrLargest == index) {
                break;
            }

            swap(arr, smallesOrLargest, index);
            index = smallesOrLargest;
            left = index * 2 + 1;
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
        HeapByArray_0225 heap = new HeapByArray_0225(10, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.add(0);
//        heap.add(4);
//        heap.add(5);
//        heap.add(6);
//        heap.add(7);

//        DuiShuQiUtil.printArr(heap.arr);
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
    }
}
