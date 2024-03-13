package com.mashibing.dailyPractice.round2._0219;

/**
 * 归并排序
 */
public class MergeSort_0219 {
    public static void mergeSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }

        recurse(arr, 0, arr.length - 1);
    }

    private static void recurse(int[] arr, int L, int R) {
        if(L == R) {
            return;
        }

        int M = L + ((R - L) >> 1);
        recurse(arr, L, M);
        recurse(arr, M + 1, R);

        int[] help = new int[R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        int helpIndex = 0;

        while(p1 <= M && p2 <= R) {
            help[helpIndex++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        while(p1 <= M) {
            help[helpIndex++] = arr[p1++];
        }

        while(p2 <= R) {
            help[helpIndex++] = arr[p2++];
        }

        for(int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    // 对数器
    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 要么p1越界了，要么p2越界了
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }
    // 非递归方法实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 步长
        int mergeSize = 1;
        while (mergeSize < N) { // log N
            // 当前左组的，第一个位置
            int L = 0;
            while (L < N) {
                if (mergeSize >= N - L) {
                    break;
                }
                int M = L + mergeSize - 1;
                int R = M + Math.min(mergeSize, N - M - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            // 防止溢出
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
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
//        int L = 2;
//        int R = 2;
//        System.out.println(L + (R - L) / 2);

//        int[] arr = {10,5,7,3,4,2,1,1,1,3};// 10,5,7,3,4,2,1,1,1,3
//        mergeSort(arr);
//        DuiShuQiUtil.printArr(arr);

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
