package com.mashibing.dailyPractice.round3._No51_60;

import com.mashibing.others.DuiShuQiUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 加强堆：实现add、pop、resign、remove方法（本算法假设堆中没有重复的元素）
 */
public class HeapEnhanced_0310 {
    private int[] arr;// 这里用小根堆实现
    private Map<Integer, Integer> map = new HashMap<>();
    private int size;

    public HeapEnhanced_0310(int len) {
        this.arr = new int[len];
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

    public void resign(int index) {
        if(index < 0 || index >= arr.length) {
            return;
        }

        heapInsert(arr, index);
        heapify(arr, index);
    }

    public void remove(int value) {
        if(!map.containsKey(value)) {
            return;
        }

        int index = map.get(value);
        swap(arr, index, --size);
        heapify(arr, index);
        map.remove(value);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void heapInsert(int[] arr, int index) {
        while(arr[index] < arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int[] arr, int index) {
        int left = index * 2 + 1;
        while(left < size) {
            int smallest = left + 1 < size && arr[left + 1] < arr[left] ? left + 1 : left;
            smallest = arr[smallest] < arr[index] ? smallest : index;
            if(smallest == index) {
                break;
            }

            swap(arr, smallest, index);
            index = smallest;
            left = index * 2 + 1;
        }
    }

    private void swap(int[] arr, int i, int j) {
        if(i == j) {
            return;
        }

        map.put(arr[i], j);
        map.put(arr[j], i);

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        HeapEnhanced_0310 h = new HeapEnhanced_0310(5);
        h.add(5);
        h.add(4);
        h.add(3);
        h.add(2);
        h.add(1);

        DuiShuQiUtil.printArr(h.arr);
        System.out.println("size = " + h.size());
        System.out.println("isEmpty = " + h.isEmpty());
        System.out.println("-------------");

//        h.arr[4] = 0;
//        h.resign(4);
//        DuiShuQiUtil.printArr(h.arr);

        h.remove(3);
        DuiShuQiUtil.printArr(h.arr);
        while(!h.isEmpty()) {
            System.out.print(h.pop()+"\t");
        }
        System.out.println();
    }
}
