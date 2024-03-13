package com.mashibing.dailyPractice.round1._2_5;

/**
 * 用数组实现大根堆，并实现添加元素和弹出元素
 */
public class BigHeapByArray_0205 {
    private int[] arr;
    private int size;

    public BigHeapByArray_0205(int len) {
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

    private void heapInsert(int[] arr, int index) {
        while(index != 0 && arr[(index - 1) >> 1] < arr[index]) {// 其实循环条件可去掉index != 0，因为当index到达根节点时，arr[(index - 1) >> 1]会等于arr[index]，不满足小于的条件
            swap(arr, (index - 1) >> 1, index);
            index = (index - 1) >> 1;
        }
    }

    private void heapify(int[] arr, int index) {
        int leftIndex = (index << 1) + 1;
        while(leftIndex < size) {
            int largerIndex = leftIndex + 1 < size && arr[leftIndex] < arr[leftIndex + 1] ? leftIndex + 1: leftIndex;
            largerIndex = arr[largerIndex] > arr[index] ? largerIndex : index;
            if(largerIndex == index) {
                break;
            }

            swap(arr, index, largerIndex);
            index = largerIndex;
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
        BigHeapByArray_0205 heap = new BigHeapByArray_0205(5);
        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.add(4);
        heap.add(5);

//        DuiShuQiUtil.printArr(heap.arr);
//        System.out.println("size = " + heap.size);
        System.out.println("pop = " + heap.pop());
//        System.out.println("size = " + heap.size);
//        DuiShuQiUtil.printArr(heap.arr);

        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
    }
}
