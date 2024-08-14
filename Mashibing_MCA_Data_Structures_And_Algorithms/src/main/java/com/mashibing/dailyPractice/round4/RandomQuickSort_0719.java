package com.mashibing.dailyPractice.round4;

/**
 * 快速排序
 */
public class RandomQuickSort_0719 {
    public static void quickSort3(int[] arr) {
        if(arr == null || arr.length == 0) {
            return;
        }

        recurse(arr, 0, arr.length - 1);
    }

    private static void recurse(int[] arr, int L, int R) {
        if(L >= R) {// 这里要注意有一个特殊情况，就是当上一轮调整完没有小于区的时候，下一轮就会出现L>R的情况
            return;
        }

        int less = L - 1;
        int more = R;
        int index = L;
        // 面试时要会解释为什么要引入随机机制
        int targetIndex = (int)(Math.random() * (R - L + 1)) + L;
        swap(arr, targetIndex, R);
        int target  = arr[R];
        while (index != more) {
            if(arr[index] < target) {
                swap(arr, index++, ++less);
            } else if(arr[index] == target) {
                index++;
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, index, R);

        /*
         本题是从大范围往小范围递归直到base case停止，整个过程结束，所以递归函数的调用放到了递归主逻辑的后面，
         如果是从base case开始由小范围往大范围递归，递归函数的调用就放到主逻辑的前面，因为大范围需要收集小范围的信息后再开始主逻辑运算
         */
        recurse(arr, L, less);
        recurse(arr, index, R);
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
            quickSort3(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println("test end");
        System.out.println("测试" + testTime + "组是否全部通过：" + (succeed ? "是" : "否"));
    }
}
