package com.mashibing.dailyPractice.round5;

import com.mashibing.dailyPractice.round4.HeapEnhanced_0730;
import com.mashibing.others.DuiShuQiUtil;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 加强堆：实现add、pop、resign、remove方法（本算法假设堆中没有重复的元素）
 */
public class HeapEnhanced_0815 {
    private int[] arr;
    private int size;
    private Comparator<Integer> comp;
    private Map<Integer, Integer> indexMap = new HashMap<>();

    public HeapEnhanced_0815(int len, Comparator<Integer> comp) {
        arr = new int[len];
        this.comp = comp;
    }

    public void add(int num) {
        if(size == arr.length) {
            throw new RuntimeException("Heap is full!");
        }

        arr[size] = num;
        indexMap.put(num, size);
        heapInsert(arr, size++);
    }

    public int pop() {
        if(size == 0) {
            throw new RuntimeException("Heap is empty!");
        }

        int result = arr[0];
        swap(arr, 0, --size);
        heapify(arr, 0);
        indexMap.remove(result);
        return result;
    }

    public void resign(int newValue) {
        Integer index = indexMap.get(newValue);
        if(index == null) {
            return;
        }

        heapInsert(arr, index);
        heapify(arr, index);
    }

    public void remove(int value) {
        Integer index = indexMap.get(value);
        if(index == null) {
            return;
        }

        swap(arr, --size, index);
        heapify(arr, 0);
        indexMap.remove(value);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void heapInsert(int[] arr, int i) {
        while (comp.compare(arr[i], arr[(i - 1) / 2]) < 0) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private void heapify(int[] arr, int i) {
        int left = 2 * i + 1;
        while (left < size) {
            int targetIndex = left + 1 < size && comp.compare(arr[left], arr[left + 1]) > 0 ? left + 1 : left;
            targetIndex = comp.compare(arr[targetIndex], arr[i]) > 0 ? i : targetIndex;
            if(targetIndex == i) {
                break;
            }

            swap(arr, i, targetIndex);
            i = targetIndex;
            left = 2 * i + 1;
        }
    }

    private void swap(int[] arr, int i, int j) {
        if(i == j) {
            return;
        }

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];

        indexMap.put(arr[i], i);
        indexMap.put(arr[j], j);
    }

    public static void main(String[] args) {
        HeapEnhanced_0815 heap = new HeapEnhanced_0815(5, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
                return o1 - o2;
            }
        });
        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.add(4);
        heap.add(5);

        System.out.println("size = " + heap.size());
        DuiShuQiUtil.printArr(heap.arr);
//        while (!heap.isEmpty()) {
//            System.out.println(heap.pop());
//        }
        System.out.println("-----------------");

//        heap.arr[2] = 0;
//        heap.indexMap.put(0,2);
//        heap.indexMap.remove(3);
//        System.out.println("resign:");
//        heap.resign(0);
//        DuiShuQiUtil.printArr(heap.arr);
//        System.out.println("indexMap = " + heap.indexMap);
//        while (!heap.isEmpty()) {
//            System.out.println(heap.pop());
//        }
//        System.out.println("-----------------");
//
        System.out.println("remove:");
        heap.remove(3);
        DuiShuQiUtil.printArr(heap.arr);
        System.out.println("indexMap = " + heap.indexMap);
        System.out.println("size = " + heap.size());
        while (!heap.isEmpty()) {
            System.out.println(heap.pop());
        }
        System.out.println("-----------------");
    }
}
