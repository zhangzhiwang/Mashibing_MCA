package com.mashibing.array;

/**
 * 前缀和数组
 * 题目：给定一个无序数组，求该数组任意区间[i,j]范围内的累加和sum
 * 思路：定义一个和原数组等长的前缀和数组，原数组区间[i,j]范围内的累加和就等于原数组[0,j]范围内的累加和减去范围[0,i-1]内的累加和。
 * 为什么直接不在i-j范围内循环累加，其实这两种方法的时间复杂度都是O(N)，但是考虑到一个场景：就是这个算法会被大量调用，这个时候用前缀和的方式优势就体现出来了：
 * 计算前缀和的时间复杂度确实是O(N)，但就计算这么一次，以后每次都是取数做减法，这个的时间复杂度是O(1)，在大量调用的情况下只有第一次是O(N)，以后每次都是O(1)，
 * 而另一种方法每次调用都是O(N)。
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
