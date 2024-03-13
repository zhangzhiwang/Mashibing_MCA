package com.mashibing.dailyPractice.round3._No51_60;

import com.mashibing.others.DuiShuQiUtil;

import java.util.Comparator;

/**
 * 用数组实现大根堆（小根堆），并实现添加元素和弹出元素
 */
public class HeapByArray_0309 {
    private int[] arr;
    private int size;
    private Comparator<Integer> com;

    public HeapByArray_0309(int len, Comparator<Integer> com) {
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

        int r = arr[0];
        swap(arr, 0, --size);
        heapify(arr, 0);
        return r;
    }

    public int peek() {
        if (size == 0) {
            throw new RuntimeException("堆已空");
        }

        return arr[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void heapInsert(int[] arr, int index) {
        while(com.compare(arr[index], arr[(index - 1) / 2]) < 0) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int[] arr, int index) {
        int left = index * 2 + 1;
        while(left < size) {
            int largestOrSmallest = left + 1 < size && com.compare(arr[left], arr[left + 1]) > 0 ? left + 1 : left;
            largestOrSmallest = com.compare(arr[index], arr[largestOrSmallest]) > 0 ? largestOrSmallest : index;
            if(largestOrSmallest == index) {
                break;
            }

            swap(arr, index, largestOrSmallest);
            index = largestOrSmallest;
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
        HeapByArray_0309 h = new HeapByArray_0309(5, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        h.add(1);
        h.add(2);
        h.add(3);
        h.add(4);
        h.add(5);

        DuiShuQiUtil.printArr(h.arr);
        System.out.println("size = " + h.size());
        System.out.println("peek = " + h.peek());
        System.out.println("isEmpty = " + h.isEmpty());
        System.out.println("--------------");

        System.out.println("pop = " + h.pop());
        System.out.println("size = " + h.size());
        System.out.println("peek = " + h.peek());
        System.out.println("isEmpty = " + h.isEmpty());
        System.out.println("--------------");

        System.out.println("pop = " + h.pop());
        System.out.println("size = " + h.size());
        System.out.println("peek = " + h.peek());
        System.out.println("isEmpty = " + h.isEmpty());
        System.out.println("--------------");

        System.out.println("pop = " + h.pop());
        System.out.println("size = " + h.size());
//        System.out.println("peek = " + h.peek());
        System.out.println("isEmpty = " + h.isEmpty());
        System.out.println("--------------");
    }
}
