package com.mashibing.dailyPractice.round5;

import com.mashibing.dailyPractice.round3._No51_60.HeapByArray_0309;
import com.mashibing.others.DuiShuQiUtil;

import java.util.Comparator;

/**
 * 用数组实现大根堆（小根堆），并实现添加元素和弹出元素
 */
public class HeapByArray_0819 {
    private int[] arr;
    private int size;
    private Comparator<Integer> comp;

    public HeapByArray_0819(int n, Comparator<Integer> comp) {
        if(n <= 0 || comp == null) {
            return;
        }

        arr = new int[n];
        this.comp = comp;
    }

    public void add(int num) {
        if(size == arr.length) {
            throw new RuntimeException("Heap is Full!");
        }

        arr[size] = num;
        heapInsert(arr, size++);
    }

    public int pop() {
        if(size == 0) {
            throw new RuntimeException("Heap is empty!");
        }

        int result = arr[0];
        swap(arr, 0, --size);
        heapify(arr, 0, size);
        return result;
    }

    private void heapInsert(int[] arr, int i) {
        while (comp.compare(arr[i], arr[(i - 1) / 2]) < 0) {
            swap(arr, i, (i - 1) / 2);
            i  =(i - 1) / 2;
        }
    }

    private void heapify(int[] arr, int i, int size) {
        int l = i * 2 + 1;
        while (l < size) {
            int targetI= l + 1 < size && comp.compare(arr[l], arr[l + 1]) > 0 ? l + 1 : l;
            targetI = comp.compare(arr[targetI], arr[i]) > 0 ? i : targetI;
            if(i == targetI) {
                break;
            }

            swap(arr, i, targetI);
            i = targetI;
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

    private boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        HeapByArray_0819 h = new HeapByArray_0819(5, new Comparator<Integer>() {
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
        while (!h.isEmpty()) {
            System.out.println(h.pop());
        }

//        System.out.println("size = " + h.size());
//        System.out.println("peek = " + h.peek());
//        System.out.println("isEmpty = " + h.isEmpty());
//        System.out.println("--------------");
//
//        System.out.println("pop = " + h.pop());
//        System.out.println("size = " + h.size());
//        System.out.println("peek = " + h.peek());
//        System.out.println("isEmpty = " + h.isEmpty());
//        System.out.println("--------------");
//
//        System.out.println("pop = " + h.pop());
//        System.out.println("size = " + h.size());
//        System.out.println("peek = " + h.peek());
//        System.out.println("isEmpty = " + h.isEmpty());
//        System.out.println("--------------");
//
//        System.out.println("pop = " + h.pop());
//        System.out.println("size = " + h.size());
////        System.out.println("peek = " + h.peek());
//        System.out.println("isEmpty = " + h.isEmpty());
//        System.out.println("--------------");
    }
}
