package com.mashibing.dailyPractice.round1._2_5;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 用数组实现小根堆，并实现添加元素和弹出元素
 */
public class SmallHeapByArray_0205 {
    private int[] arr;
    private int size;

    public SmallHeapByArray_0205(int len) {
        this.arr = new int[len];
    }

    public void add (int num) {
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
        /*
        这里注意：如果将除以写成向右移动一位的形式，那么必须前面加上index>0的判断，因为-1/2=0，而-1>>1=-1
         */
        while(index > 0 && arr[index] < arr[(index - 1) >> 1]) {
            swap(arr, index, (index - 1) >> 1);
            index = (index - 1) >> 1;
        }
    }

    private void heapify(int[] arr, int index) {
        int leftIndex = (index << 1) + 1;
        while(leftIndex < size) {
            int smallerIndex = leftIndex + 1 < size && arr[leftIndex + 1] < arr[leftIndex] ? leftIndex + 1 : leftIndex;
            smallerIndex = arr[smallerIndex] < arr[index] ? smallerIndex : index;
            if(smallerIndex == index) {
                return;
            }

            swap(arr, index, smallerIndex);
            index = smallerIndex;
            leftIndex = (index << 1) + 1;
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
//        System.out.println(-1 >> 1);

        SmallHeapByArray_0205 heap = new SmallHeapByArray_0205(5);
        heap.add(5);
        heap.add(4);
        heap.add(3);
        heap.add(2);
        heap.add(1);

        DuiShuQiUtil.printArr(heap.arr);
        System.out.println("pop = " + heap.pop());
        DuiShuQiUtil.printArr(heap.arr);
        System.out.println("size =" + heap.size);
        System.out.println("---------------");

        System.out.println("pop = " + heap.pop());
        DuiShuQiUtil.printArr(heap.arr);
        System.out.println("size =" + heap.size);
    }
}
