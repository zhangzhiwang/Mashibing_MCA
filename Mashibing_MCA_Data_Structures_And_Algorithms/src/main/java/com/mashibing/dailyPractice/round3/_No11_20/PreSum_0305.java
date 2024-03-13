package com.mashibing.dailyPractice.round3._No11_20;

/**
 * 给定一个无序数组，求该数组任意区间[i,j]范围内的累加和sum
 */
public class PreSum_0305 {
    public static int preSum(int[] arr, int i, int j) {
        if(arr == null || arr.length == 0 || i > j || i < 0 || j >= arr.length) {
            throw new RuntimeException("参数有误");
        }

        int[] sumArr = new int[arr.length];
        int sum = 0;
        for (int k = 0; k < arr.length; k++) {
            sumArr[k] = sum += arr[k];
        }

        return i == 0 ? sumArr[j] : sumArr[j] - sumArr[i - 1];
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        System.out.println(preSum(arr,2, 4));
    }
}
