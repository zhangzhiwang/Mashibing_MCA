package com.mashibing.array.sort;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 堆排序
 * 课程：体系班课时52-55
 * 思路：见com.mashibing.preInEclipse.senior.sort.HeapSort注释
 */
public class HeapSort {
    public static void heapSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }

        /*
         使用heapify从下往上建大根堆，时间复杂度是O(N)（如果使用heapInsert从上往下建堆的时间复杂度是O(N*logN)）。
         可以这样认为：题目给定的原始数组看做是一个完全二叉树，数组的每一个元素都是构成这个完全二叉树的组成部分，只不过这个完全二叉树目前还不是大根堆，下面要做的就是把它调成大根堆。
         调的方式就是使用heapify，从叶子节点开始逐层往上调，就是下面这个for循环干的事情，这个过程就是构建整个大根堆的过程。
         */
        int heapSize = arr.length;
        for(int i = arr.length - 1; i >= 0; i--) {// O(N)
            heapify(arr, i, heapSize);
        }

        swap(arr, 0, --heapSize);
        while(heapSize != 0) {// O(N * logN)
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int leftIndex = 2 * index + 1;
        while(leftIndex < heapSize) {// 存在左孩子就进入循环
            // 左右孩子中较大的索引
            int largestValueIndex = leftIndex + 1 < heapSize && arr[leftIndex] < arr[leftIndex + 1] ? leftIndex + 1 : leftIndex;
            // 上面左右孩子的大小比较出来了但是还没有跟父节点比较
            largestValueIndex = arr[index] > arr[largestValueIndex] ? index : largestValueIndex;
            if(index == largestValueIndex) {
                break;
            }

            swap(arr, index, largestValueIndex);
            index = largestValueIndex;
            leftIndex = 2 * index + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,7,9,2,4,6,8,0};
        heapSort(arr);
        DuiShuQiUtil.printArr(arr);
    }
}
