package com.mashibing.dailyPractice.round1._1_27;

/**
 * 给定一个有序数组，查找>=某个数的最左位置。
 * 比如：[1,2,3,3,3,6,7,8,9]，大于等于5的最左位置是5。
 */
public class BinarySearchFindMoreLeft_0127 {
    public static int binarySearchFindMoreLeft(int[] arr, int k) {
        if(arr == null || arr.length == 0) {
            return -1;
        }

        return recurse(arr, 0, arr.length - 1, k);
    }

    private static int recurse(int[] arr, int L, int R, int k) {
        if(L == R) {
            return arr[L] >= k ? L : -1;
        }

        int M = L + ((R - L) >> 1);
        if(arr[M] < k) {
            return recurse(arr, M + 1, R, k);
        } else {
            if(M == 0 || arr[M - 1] < k) {
                return M;
            }
            return recurse(arr, L, M - 1, k);
        }
    }

    // 对数器
    // for test
    public static int test(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= value) {
                return i;
            }
        }
        return -1;
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
        int[] arr = {1,2,3,3,3,6,9,11,13};
        int k = 19;
        System.out.println(binarySearchFindMoreLeft(arr, k));

//        int testTime = 500000;
//        int maxSize = 10;
//        int maxValue = 100;
//        boolean succeed = true;
//        for (int i = 0; i < testTime; i++) {
//            int[] arr = generateRandomArray(maxSize, maxValue);
//            Arrays.sort(arr);
//            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
//            if (test(arr, value) != binarySearchFindMoreLeft(arr, value)) {
//                printArray(arr);
//                System.out.println(value);
//                System.out.println(test(arr, value));
//                System.out.println(binarySearchFindMoreLeft(arr, value));
//                succeed = false;
//                break;
//            }
//        }
//        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
