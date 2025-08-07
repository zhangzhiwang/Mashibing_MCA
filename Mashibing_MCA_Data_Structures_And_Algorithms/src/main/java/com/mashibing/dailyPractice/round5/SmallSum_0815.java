package com.mashibing.dailyPractice.round5;

/**
 * 给定一个数组，求该数组的最小和（假定给出的数组元素都是非负数）
 * 解释：比如给定数组[1,3,5,2]：
 * 1、把第一个元素1左侧所有比1小的元素全部加起来得到一个sum1，由于是第一个元素，所以它的左侧没有数也不可能存在比它小的，所以对于元素1来说最小和sum1=0；
 * 2、把第二个元素3左侧所有比3小的元素全部加起来得到一个sum2，所以sum2=1；
 * 3、把第三个元素5左侧所有比5小的元素全部加起来得到一个sum3，所以sum3=1 + 3；
 * 4、把第四个元素2左侧所有比2小的元素全部加起来得到一个sum4，所以sum4=1；
 * sumArr = sum1 + sum2 + sum3 + sum4，返回sumArr。
 */
public class SmallSum_0815 {
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return recurse(arr, 0, arr.length - 1);
    }

    private static int recurse(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }

        int m = l + ((r - l) >> 1);
        int sum = 0;
        sum += recurse(arr, l, m);
        sum += recurse(arr, m + 1, r);

        int p1 = l;
        int p2 = m + 1;
        int[] help = new int[r - l + 1];
        int helpI = 0;
        while (p1 <= m && p2 <= r) {
            sum += arr[p1] < arr[p2] ? arr[p1] * (r - p2 + 1) : 0;
            help[helpI++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[helpI++] = arr[p1++];
        }
        while (p2 <= r) {
            help[helpI++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }

        return sum;
    }

    // 对数器
    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
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
//        int[] arr = new int[]{1,2,3,4};
//        System.out.println(comparator(arr));
//        System.out.println(smallSum(arr));

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
