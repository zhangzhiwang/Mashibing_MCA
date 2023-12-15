package com.mashibing.array.sort;

import com.mashibing.others.DuiShuQiUtil;

import java.util.Arrays;

/**
 * 桶排序之基数排序
 * 限制条件：基数排序要求数组的元素必须是非负数且能够用十进制表示
 * 思路：
 * 1、遍历一遍原数组找到最大值，看看最大值有几位数
 * 2、准备一个具有10个元素的数组countArr，countArr的下标代表原数组某一位上的数字
 * 3、将countArr变成累加和数组，变成累加和数组之后，比如0位置上的值为3，代表原数组某一位上<=0的元素有3个，以此类推
 * 4、准备一个和原数组等长的help数组，从右往左遍历原数组，根据累加和数组将原数组上的元素依次放入help数组相应的位置
 * 5、将help数组的元素覆盖到原数组里
 * 课程：体系班课时69-71
 */
public class RadixSort {
    public static void radixSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }

        // 找到原数组的最大值，看看有几位数，有几位数就进出桶几次
        int digit = getMaxDigit(arr);// 最大值一共有digit位数，digit=1代表个位，digit=2代表十位，digit=3代表百位，以此类推
        for(int i = 1; i <= digit; i++) {// 最外层的循环控制进出桶的次数
            int[] countArr = new int[10];//元素的索引值代表每一位上0-9这9个数，元素值代表原数组所有元素某一位上该数字一共出现了几次
            for(int j = 0; j < arr.length; j++) {
                int countArrIndex = getDigitNum(arr[j], i);
                countArr[countArrIndex]++;
            }
            // 将countArr变成累加和数组
            int sum = 0;
            for(int j = 0; j < countArr.length; j++) {
                sum += countArr[j];
                countArr[j] = sum;
            }

            // 从右往左遍历原数组，将原数组元素放到help数组的相应位置
            int[] help = new int[arr.length];
            for(int j = arr.length - 1; j >= 0; j--) {
                int countArrIndex = getDigitNum(arr[j], i);
                help[--countArr[countArrIndex]] = arr[j];
            }

            // 将help数组的元素覆盖到原数组
            for(int j = 0; j < arr.length; j++) {
                arr[j] = help[j];
            }
        }
    }

    private static int getMaxDigit(int[] arr) {
        int maxValue = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++) {
            maxValue = Math.max(maxValue, arr[i]);
        }

        int digit = 1;
        while(maxValue / 10 != 0) {
            maxValue = maxValue / 10;
            digit++;
        }

        return digit;
    }

    /**
     * 将一个数某一位上的数字提取出来，比如num=123，digit=2，代表将123的十位提取出来
     * @param num
     * @param digit
     * @return
     */
    private static int getDigitNum(int num, int digit) {
        return (num / (int)Math.pow(10, digit - 1)) % 10;
    }

    // 以下是对数器
    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
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
//        int[] arr = {123, 27, 9, 17, 67};
//        radixSort(arr);
//        DuiShuQiUtil.printArr(arr);

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        radixSort(arr);
        printArray(arr);
    }
}
