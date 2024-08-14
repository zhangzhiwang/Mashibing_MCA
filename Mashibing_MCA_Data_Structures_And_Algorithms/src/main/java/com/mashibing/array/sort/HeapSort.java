package com.mashibing.array.sort;

import com.mashibing.others.DuiShuQiUtil;

import java.util.Arrays;

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
         下面建堆的过程虽然外层循环的时间复杂度是O(N)，循环内时间复杂度是O(logN)，但是整体的时间复杂度不是O(N * logN)，而是最终收敛于O(N)，
         具体解释见com.mashibing.preInEclipse.senior.sort.HeapSort
         */
        int heapSize = arr.length;
        for(int i = arr.length - 1; i >= 0; i--) {// O(N)
            /*
             heapSize不用动，千万不能自减
             可以这样理解：把arr看做一棵完全二叉树，只不过它还不是堆，现在用heapify把它调整成堆，堆的大小就是完全二叉树的大小，
             所以调整的过程中heapSize是不用变的，不要和pop的过程里面调用heapify混淆，那个是需要将heapSize减1的。
             */
            heapify(arr, i, heapSize);// O(logN)
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

    // 对数器
    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
