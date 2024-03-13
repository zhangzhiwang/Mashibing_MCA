package com.mashibing.dailyPractice.round3._No11_20;

/**
 * 对于一个数组的任意位置i上的数，找出它右边所有乘以2之后仍然小于它的数的个数，找出数组中所有这样的数的个数
 */
public class BiggerThanRightTwice_0305 {
    public static int biggerThanRightTwice(int[] arr) {
        if(arr == null || arr.length < 2) {
            return 0;
        }

        return recurse(arr, 0, arr.length - 1);
    }

    private static int recurse(int[] arr, int L, int R) {
        if(L == R) {
            return 0;
        }

        int M = L + ((R - L) >> 1);
        int left = recurse(arr, L, M);
        int right = recurse(arr, M + 1, R);
        int merge = merge(arr, L, M, R);
        return left + right + merge;
    }

    private static int merge(int[] arr, int L, int M, int R) {
        int p1 = M;
        int p2 = R;
        int count = 0;
        while(p1 >= L && p2 >= M + 1) {
            if(arr[p1] <= arr[p2] * 2) {
                p2--;
            } else {
                count +=  p2 - (M + 1) + 1;
                p1--;
            }
        }

        int[] help = new int[R - L + 1];
        p1 = L;
        p2 = M + 1;
        int helpIndex = 0;
        while(p1 <= M && p2 <= R) {
            help[helpIndex++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= M) {
            help[helpIndex++] = arr[p1++];
        }

        while (p2 <= R) {
            help[helpIndex++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return count;
    }

    // 对数器
    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
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

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (biggerThanRightTwice(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
