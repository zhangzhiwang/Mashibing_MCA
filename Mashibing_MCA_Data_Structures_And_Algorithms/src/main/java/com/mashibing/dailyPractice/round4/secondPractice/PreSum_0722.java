package com.mashibing.dailyPractice.round4.secondPractice;

/**
 * 给定一个无序数组，求该数组任意区间[i,j]范围内的累加和sum
 */
public class PreSum_0722 {
    public static int preSum(int[] arr, int i, int j) {
        if (arr == null || arr.length == 0 || i < 0 || i >= arr.length || j < 0 || j >= arr.length) {
            throw new RuntimeException("Param error!");
        }

        int[] sumArr = new int[arr.length];
        int sum = 0;
        for (int k = 0; k < arr.length; k++) {
            sumArr[k] = sum += arr[k];
        }

        return i == 0 ? sumArr[j] : sumArr[j] - sumArr[i - 1];
    }

    // 老师的代码
    public static int rangeSum(int[] arr, int L, int R) {
        int sum = 0;
        for (int i = L; i <= R; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        System.out.println("correct ans:");
        System.out.println(rangeSum(arr, 0, 2));
        System.out.println("correct ans:");
        System.out.println(preSum(arr, 0, 2));
    }
}
