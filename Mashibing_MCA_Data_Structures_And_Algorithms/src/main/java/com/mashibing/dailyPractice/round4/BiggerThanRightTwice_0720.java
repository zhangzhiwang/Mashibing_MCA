package com.mashibing.dailyPractice.round4;

/**
 * 对于一个数组的任意位置i上的数，找出它右边所有乘以2之后仍然小于它的数，找出数组中所有这样的数的个数
 */
public class BiggerThanRightTwice_0720 {
    public static int biggerThanRightTwice(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return recurse(arr, 0, arr.length - 1);
    }

    private static int recurse(int[] arr, int L, int R) {
        if(L == R) {
            return 0;
        }

        int mid = L + ((R - L) >> 1);
        int count = 0;
        count += recurse(arr, L, mid);
        count += recurse(arr, mid + 1, R);

        int p1 = mid;
        int p2 = R;
        while(p1 >= L && p2 >= mid + 1) {
            if(arr[p1] <= 2 * arr[p2]) {
                p2--;
            } else {
                p1--;
                count += p2 - (mid + 1) + 1;
            }
        }

        int[] help = new int[R - L + 1];
        p1 = L;
        p2 = mid + 1;
        int helpIndex = 0;
        while (p1 <= mid && p2 <= R) {
            help[helpIndex++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= mid) {
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
