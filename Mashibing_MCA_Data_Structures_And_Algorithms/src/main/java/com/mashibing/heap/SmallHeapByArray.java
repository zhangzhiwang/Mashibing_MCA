package com.mashibing.heap;

/**
 * 用数组做一个小根堆
 * 课程：新手班课时48-51
 * 思路：见com.mashibing.preInEclipse.senior.heap.HeapByArray注释
 */
public class SmallHeapByArray {
    private int[] arr;// 小根堆
    private int heapSize;

    public SmallHeapByArray(int arrLength) {
        this.arr = new int[arrLength];
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public void add(int num) {
        if(arr.length == heapSize) {
            throw new RuntimeException("堆已满！");
        }

        arr[heapSize] = num;
        heapInsert(arr, heapSize++);
    }

    public int pop() {
        if(arr.length == 0) {
            throw new RuntimeException("堆已空！");
        }

        int result = arr[0];
        swap(arr, 0, --heapSize);
        heapify(arr, 0);

        return result;
    }

    public int peek() {
        if(arr.length == 0) {
            throw new RuntimeException("堆已空！");
        }

        return arr[0];
    }

    private void heapInsert(int[] arr, int index) {
        while(arr[index] < arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int[] arr, int index) {
        int leftIndex = 2 * index + 1;
        while(leftIndex < heapSize) {
            int smallestValueIndex = leftIndex + 1 < heapSize && arr[leftIndex] > arr[leftIndex + 1] ? leftIndex + 1 : leftIndex;
            smallestValueIndex = arr[index] < arr[smallestValueIndex] ? index : smallestValueIndex;
            if(smallestValueIndex == index) {
                break;
            }
            swap(arr, index, smallestValueIndex);
            index = smallestValueIndex;
            leftIndex = 2 * index + 1;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        SmallHeapByArray shba = new SmallHeapByArray(10);
        shba.add(10);
        shba.add(1);
        shba.add(8);
        System.out.println(shba.peek());
        System.out.println(shba.isEmpty());
        System.out.println("size = " + shba.size());
        System.out.println("-------------");

        System.out.println(shba.pop());
        System.out.println(shba.pop());
        System.out.println(shba.pop());
        System.out.println(shba.isEmpty());
        System.out.println("size = " + shba.size());
    }
}
