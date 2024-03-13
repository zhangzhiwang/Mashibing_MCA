package com.mashibing.dailyPractice.round2._0221;

/**
 * 给定一个无序数组，求该数组任意区间[i,j]范围内的累加和sum
 */
public class PreSum_0221 {
    public static int preSum(int[] arr, int i, int j) {
        if(arr == null || arr.length < 1 || i > j) {
            throw new RuntimeException("参数有误");
        }

        int sum = 0;
        int[] help = new int[arr.length];
        for(int k = 0; k < arr.length; k++) {
            sum += arr[k];
            help[k] = sum;
        }

        return i == 0 ? help[j] : help[j] - help[i - 1];
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int sum = preSum(arr, 0, 2);
        System.out.println(sum);
    }
}
