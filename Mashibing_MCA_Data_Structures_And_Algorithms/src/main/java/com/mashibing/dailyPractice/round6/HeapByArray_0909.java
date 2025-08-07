package com.mashibing.dailyPractice.round6;

import com.mashibing.dailyPractice.round5.HeapByArray_0819;
import com.mashibing.others.DuiShuQiUtil;

import java.util.Comparator;

/**
 * 用数组实现大根堆（小根堆），并实现添加元素和弹出元素
 */
public class HeapByArray_0909 {
    private int[] arr;
    private int size;
    private Comparator<Integer> comp;

    public HeapByArray_0909(int n, Comparator<Integer> comp) {
        arr = new int[n];
        this.comp = comp;
    }

    public void add(int n) {
        if(n == size) {
            throw new RuntimeException("Heap is full!");
        }

        arr[size] = n;
        heapInsert(arr, size++);
    }

    public int pop() {
        if(size == 0) {
            throw new RuntimeException("Heap is empty!");
        }

        int r = arr[0];
        swap(arr, 0, --size);
        heapify(arr, 0, size);
        return r;
    }

    public int peek() {
        if(size == 0) {
            throw new RuntimeException("Heap is empty!");
        }

        return arr[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void heapInsert(int[] arr, int i) {
        while (comp.compare(arr[i], arr[(i - 1) / 2]) < 0) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private void heapify(int[] arr, int i, int size) {
        int l = i * 2 + 1;
        while (l < size) {
            int t = l + 1 < size && comp.compare(arr[l], arr[l + 1]) > 0 ? l + 1 : l;
            t = comp.compare(arr[t], arr[i]) > 0 ? i : t;
            if(t == i) {
                break;
            }

            swap(arr, i, t);
            i = t;
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
        HeapByArray_0909 h = new HeapByArray_0909(5, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
//                return o2 - o1;
            }
        });
        h.add(1);
        h.add(2);
        h.add(3);
        h.add(4);
        h.add(5);

        DuiShuQiUtil.printArr(h.arr);
//        while (!h.isEmpty()) {
//            System.out.println(h.pop());
//        }
//
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
        System.out.println("peek = " + h.peek());
        System.out.println("isEmpty = " + h.isEmpty());
        System.out.println("--------------");
    }
}
