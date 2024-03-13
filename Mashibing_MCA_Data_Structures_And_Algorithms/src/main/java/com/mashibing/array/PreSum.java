package com.mashibing.array;

/**
 * 前缀和数组
 * 题目：给定一个无序数组，求该数组任意区间[i,j]范围内的累加和sum
 * 思路：定义一个和原数组等长的前缀和数组，原数组区间[i,j]范围内的累加和就等于原数组[0,j]范围内的累加和减去范围[0,i-1]内的累加和。
 * 课程：体系班课时36
 */
public class PreSum {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int sum = getSum(arr, 0, 3);
        System.out.println("sum = " + sum);
    }
    
    public static int getSum(int[] arr, int i, int j) {
        if(arr == null || arr.length < 1) {
            throw new RuntimeException("数组为空！");
        }
        
        if(i < 0 || j < 0 || i > j) {
            throw new RuntimeException("范围有误！");
        }
        
        int[] preSum = new int[arr.length];
        int sum = 0;
        for(int k = 0; k < arr.length; k++) {
            sum += arr[k];
            preSum[k] = sum;
            // 以上两行可以简写为
//            preSum[k] = sum += arr[k];// 先算sum+arr[k]，然后将结果赋值给sum，再将sum赋值给preSum[k]
        }
        
        return i == 0 ? preSum[j] : preSum[j] - preSum[i - 1];
    }
}
