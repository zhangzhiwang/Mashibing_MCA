package com.mashibing.dailyPractice.round2._0228;

import com.mashibing.others.DuiShuQiUtil;

import java.util.Comparator;

/**
 * 加强堆：实现add、pop、resign、remove方法（本算法假设堆中没有重复的元素）
 */
public class HeapEnhanced_0228 {
    private int[] arr;
    private int size;
    private Comparator<Integer> com;

    public HeapEnhanced_0228(int len, Comparator<Integer> com) {
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

    public void resign(int index, int num) {
        if(index < 0 || index >= size) {
            throw new RuntimeException("参数有误");
        }

        arr[index] = num;
        heapInsert(arr, index);
        heapify(arr, index);
    }

    public int remove(int index) {
        if(index < 0 || index >= size) {
            throw new RuntimeException("参数有误");
        }

        int result = arr[index];
        swap(arr, index, --size);
        heapify(arr, index);
        return result;
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
            int largestOrSmallest = left + 1 < size && com.compare(arr[left + 1], arr[left]) <= 0 ? left + 1 : left;
            largestOrSmallest = com.compare(arr[index], arr[largestOrSmallest]) <= 0 ? index : largestOrSmallest;
            if(largestOrSmallest == index) {
                break;
            }

            swap(arr, largestOrSmallest, index);
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
        HeapEnhanced_0228 heap = new HeapEnhanced_0228(5, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.add(4);
        heap.add(5);
//        System.out.println("size = " + heap.size);
//        while(!heap.isEmpty()) {
//            System.out.print(heap.pop() + "\t");
//        }
//        System.out.println();
        DuiShuQiUtil.printArr(heap.arr);

//        heap.resign(0, 0);
//        DuiShuQiUtil.printArr(heap.arr);

        System.out.println("remove = " + heap.remove(3));
        DuiShuQiUtil.printArr(heap.arr);
//        System.out.println("size = " + heap.size);
//        System.out.println("---------------");

//        heap.add(10);
//        System.out.println("remove = " + heap.remove(0));
//        DuiShuQiUtil.printArr(heap.arr);
//        System.out.println("size = " + heap.size);
//        System.out.println("---------------");
    }
}
