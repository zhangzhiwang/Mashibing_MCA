package com.mashibing.dailyPractice.round3._No11_20;

/**
 * 给定一个无序的数组，该数组任意相邻两个元素不相等，找到任意局部最小值的位置。局部最小值就是一个数比其相邻的数都小。
 */
public class LocalLeastValue_0306 {
    public static int localLeastValue(int[] arr) {
        if(arr == null || arr.length == 0) {
            return -1;
        }
        if(arr.length == 1) {
            return 0;
        }

        return recurse(arr, 0, arr.length - 1);
    }

    private static int recurse(int[] arr, int L, int R) {
        if(L == R) {
            return L;
        }
        if(L + 1 == R) {
            if(arr[L] > arr[R]) {
                return R;
            } else {
                return L;
            }
        }

        int M = L + ((R - L) >> 1);
        if(arr[M] < arr[M - 1] && arr[M] < arr[M + 1]) {
            return M;
        } else if (arr[M] > arr[M - 1]) {
            return recurse(arr, L, M - 1);
        } else {
            return recurse(arr, M + 1, R);
        }
    }

    // 对数器
    // 生成随机数组，且相邻数不相等
    public static int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen);
        int[] arr = new int[len];
        if (len > 0) {
            arr[0] = (int) (Math.random() * maxValue);
            for (int i = 1; i < len; i++) {
                do {
                    arr[i] = (int) (Math.random() * maxValue);
                } while (arr[i] == arr[i - 1]);
            }
        }
        return arr;
    }

    // 也用于测试
    public static boolean check(int[] arr, int minIndex) {
        if (arr.length == 0) {
            return minIndex == -1;
        }
        int left = minIndex - 1;
        int right = minIndex + 1;
        boolean leftBigger = left >= 0 ? arr[left] > arr[minIndex] : true;
        boolean rightBigger = right < arr.length ? arr[right] > arr[minIndex] : true;
        return leftBigger && rightBigger;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int ans = localLeastValue(arr);
            if (!check(arr, ans)) {
                printArray(arr);
                System.out.println("出错了");
                System.out.println(ans);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
