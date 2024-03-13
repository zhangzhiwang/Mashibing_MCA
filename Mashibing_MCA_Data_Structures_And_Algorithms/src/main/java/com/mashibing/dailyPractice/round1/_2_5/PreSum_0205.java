package com.mashibing.dailyPractice.round1._2_5;

/**
 * 给定一个无序数组，求该数组任意区间[i,j]范围内的累加和sum
 */
public class PreSum_0205 {
    public static int preSum(int[] arr, int i , int j) {
        if(arr == null || arr.length == 0 || i > j || j >= arr.length || i < 0) {
            throw new RuntimeException("参数有误");
        }

        int[] preSumArr = new int[arr.length];
        int sum = 0;
        for(int k = 0; k < arr.length; k++) {
            sum += arr[k];
            preSumArr[k] = sum;
        }

        return i == 0 ? preSumArr[j] : preSumArr[j] - preSumArr[i - 1];
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,2,4,6};
        int sum = preSum(arr, 2, 4);
        System.out.println(sum);
    }
}
