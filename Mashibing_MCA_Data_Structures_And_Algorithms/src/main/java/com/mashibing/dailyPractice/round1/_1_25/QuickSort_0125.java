package com.mashibing.dailyPractice.round1._1_25;

/**
 * 快速排序
 */
public class QuickSort_0125 {
    public static void quickSort(int[] arr) {
        if(arr == null || arr.length <= 1) {
            return;
        }

        recurse(arr,  0, arr.length - 1);
    }

    private static void recurse(int[] arr, int L, int R) {
        if(L >= R) {
            return;
        }

        /*
         这里采用的是随机快排的实现
         为什么使用随机快排：
         1、先解释下传统快排的实现思路
         2、随机快排的时间复杂度更低，原因见com.mashibing.array.sort.QuickSort3的注释，课程见：体系班课时44
         */
        // 产生一个在[L,R]范围内的随机数
        int randomIndex = (int)(Math.random() * (R - L + 1)) + L;
        swap(arr, randomIndex, R);

        int lessArea = L - 1;
        int moreArea = R;
        int index = L;
        while(index != moreArea) {
            if(arr[index] < arr[R]) {
                swap(arr, index++, ++lessArea);
            } else if(arr[index] == arr[R]) {
                index++;
            } else {
                swap(arr, index, --moreArea);
            }
        }
        swap(arr, moreArea, R);

        recurse(arr, L, lessArea);
        recurse(arr, index + 1, R);
    }

    private static void swap(int[] arr, int i, int j) {
        if(i == j) {
            return;
        }

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    // 对数器
    // 生成随机数组（用于测试）
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // 拷贝数组（用于测试）
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

    // 对比两个数组（用于测试）
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

    // 打印数组（用于测试）
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[] { -1, -1 };
        }
        if (L == R) {
            return new int[] { L, R };
        }
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, R);
        return new int[] { less + 1, more };
    }

    public static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process(arr, L, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, R);
    }

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
//        int[] arr = {3,2,5,7,6,5,8};
//        quickSort(arr);
//        DuiShuQiUtil.printArr(arr);

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort(arr2);
//            quickSort3(arr3);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println("test end");
        System.out.println("测试" + testTime + "组是否全部通过：" + (succeed ? "是" : "否"));
    }
}
