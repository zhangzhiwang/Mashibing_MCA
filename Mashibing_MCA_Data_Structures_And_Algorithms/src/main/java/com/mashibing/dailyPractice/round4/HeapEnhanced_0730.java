package com.mashibing.dailyPractice.round4;

import com.mashibing.others.DuiShuQiUtil;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 加强堆：实现add、pop、resign、remove方法（本算法假设堆中没有重复的元素）
 */
public class HeapEnhanced_0730 {
    private int[] arr;
    private int size;
    private Map<Integer, Integer> indexMap = new HashMap<>();
    private Comparator<Integer> comparator;

    public HeapEnhanced_0730(int n, Comparator<Integer> comparator) {
        if (n <= 0 || comparator == null) {
            return;
        }

        arr = new int[n];
        this.comparator = comparator;
    }

    public void add(int i) {
        if (size == arr.length) {
            throw new RuntimeException("Heap is full!");
        }

        arr[size] = i;
        indexMap.put(i, size);
        heapInsert(arr, size++);
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("Heap is empty!");
        }

        int result = arr[0];
        indexMap.remove(result);
        swap(arr, 0, --size);
        heapify(arr, 0, size);
        return result;
    }

    private void heapInsert(int[] arr, int i) {
        while (comparator.compare(arr[(i - 1) / 2], arr[i]) > 0) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private void heapify(int[] arr, int i, int size) {
        int l = i * 2 + 1;
        while (l < size) {
            int targetIndex = l + 1 < size && comparator.compare(arr[l], arr[l + 1]) > 0 ? l + 1 : l;
            targetIndex = comparator.compare(arr[i], arr[targetIndex]) > 0 ? targetIndex : i;
            if (targetIndex == i) {
                break;
            }

            swap(arr, i, targetIndex);
            i = targetIndex;
            l = i * 2 + 1;
        }
    }

    /**
     * 当改变任意节点的值的时候，重新调整堆
     * 改值的时候是先改数组中的值，然后再改indexMap中的对应的值，但是这个节点的位置没有调整，resign方法就是调整该节点的位置
     *
     * @param newValue 改变后的值，这个值以前是什么不关心
     */
    public void resign(int newValue) {
        Integer index = indexMap.get(newValue);
        if (index == null) {
            return;
        }
        heapInsert(arr, index);
        heapify(arr, index, size);
    }

    public void remove(int value) {
        Integer index = indexMap.get(value);
        if (index == null) {
            return;
        }

        indexMap.remove(value);
        swap(arr, index, --size);
        heapify(arr, index, size);
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];

        indexMap.put(arr[i], i);
        indexMap.put(arr[j], j);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        HeapEnhanced_0730 heap = new HeapEnhanced_0730(5, new Comparator<Integer>() {
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

        System.out.println("indexMap = " + heap.indexMap);
        int targetValue = heap.arr[1];
        Integer index = heap.indexMap.get(targetValue);
        heap.indexMap.remove(targetValue);
        heap.arr[1] = targetValue = 10;
        heap.indexMap.put(targetValue, index);
        System.out.println("after change:");
        DuiShuQiUtil.printArr(heap.arr);
        System.out.println("indexMap = " + heap.indexMap);
        System.out.println("-----------------");

        System.out.println("resign:");
        heap.resign(targetValue);
        DuiShuQiUtil.printArr(heap.arr);
        System.out.println("indexMap = " + heap.indexMap);
//        while (!heap.isEmpty()) {
//            System.out.println(heap.pop());
//        }
        System.out.println("-----------------");

        System.out.println("remove:");
        heap.remove(4);
        DuiShuQiUtil.printArr(heap.arr);
        System.out.println("indexMap = " + heap.indexMap);
        System.out.println("size = " + heap.size());
        while (!heap.isEmpty()) {
            System.out.println(heap.pop());
        }
        System.out.println("-----------------");
    }
}
