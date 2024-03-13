package com.mashibing.dailyPractice.round1._2_5;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 已知给定一个大根堆数组，但是其中的一个元素被换掉了，重新调整这个堆
 */
public class AdjustHeap_0205 {
    private int[] arr;
    private int size;

    public AdjustHeap_0205(int len) {
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

        int result = arr[0];
        swap(arr, 0, --size);
        heapify(arr, 0);
        return result;
    }

    public void adjustHeap(int[] arr) {
        if(arr == null || arr.length == 0) {
            return;
        }

        // 将一个随机的index位置上的数替换一个随机值
        int N = arr.length;
        int randIndex = (int)(Math.random() * N);
        int randValue = (int)(Math.random() * 5);

        System.out.println("原始数组：");
        DuiShuQiUtil.printArr(arr);
        arr[randIndex] = randValue;
        System.out.println("将第" + randIndex + "位置的数替换为" + randValue);
        System.out.println("替换后调整前：");
        DuiShuQiUtil.printArr(arr);

        heapInsert(arr, randIndex);
        heapify(arr, randIndex);

        System.out.println("调整后：");
        DuiShuQiUtil.printArr(arr);
    }

    private void heapInsert(int[] arr, int index) {
        while(arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }
    private void heapify(int[] arr, int index) {
        int leftIndex = index * 2 + 1;
        while(leftIndex < size) {
            int largerIndex = leftIndex + 1 < size && arr[leftIndex + 1] > arr[leftIndex] ? leftIndex + 1 : leftIndex;
            largerIndex = arr[largerIndex] > arr[index] ? largerIndex : index;
            if(largerIndex == index) {
                break;
            }

            swap(arr, index, largerIndex);
            index = largerIndex;
            leftIndex = index * 2 + 1;
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
        AdjustHeap_0205 a = new AdjustHeap_0205(10);
        a.add(10);
        a.add(20);
        a.add(30);
        a.add(40);
        a.add(50);
        a.add(60);
        a.add(70);
        a.add(80);
        a.add(90);
        a.add(100);

//        System.out.println("pop = " + a.pop());
//        System.out.println("pop = " + a.pop());
//        System.out.println("pop = " + a.pop());
//        System.out.println("pop = " + a.pop());
//        System.out.println("pop = " + a.pop());
//        System.out.println("pop = " + a.pop());
//        System.out.println("pop = " + a.pop());
//        System.out.println("pop = " + a.pop());
//        System.out.println("pop = " + a.pop());
//        System.out.println("pop = " + a.pop());

        a.adjustHeap(a.arr);
    }
}
