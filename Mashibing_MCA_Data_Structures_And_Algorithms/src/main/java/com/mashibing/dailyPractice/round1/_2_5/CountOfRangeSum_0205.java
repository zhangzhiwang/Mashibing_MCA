package com.mashibing.dailyPractice.round1._2_5;

/**
 * 给定一个无序数组arr，求累加和落在[lower,upper]范围内的子数组的个数
 */
public class CountOfRangeSum_0205 {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums == null || nums.length == 0 || lower > upper) {
            return 0;
        }

        // 如果把Integer的边界数值考虑在内的话，那么要用long数组来装nums的前缀和，否则两个数相加很可能超出Integer的范围
        long[] sumArr = new long[nums.length];
        long sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sumArr[i] = sum += nums[i];
        }

        return recurse(sumArr, 0, sumArr.length - 1, lower, upper);
    }

    private int recurse(long[] sumArr, int L, int R, int lower, int upper) {
        if(L == R) {
            if(sumArr[L] >= lower && sumArr[L] <= upper) {
                return 1;
            } else {
                return 0;
            }
        }

        int M = L + ((R - L) >> 1);
        int leftCount = recurse(sumArr, L, M, lower, upper);
        int rightCount = recurse(sumArr, M + 1, R, lower, upper);
        int mergeCount = merge(sumArr, L, M, R, lower, upper);
        return leftCount + rightCount + mergeCount;
    }

    private int merge(long[] sumArr, int L, int M, int R, int lower, int upper) {
        int leftX = L;
        int leftY = L;// 左组有效范围是[leftX, leftY)
        int count = 0;

        for(int i = M + 1; i <= R; i++) {
            long min = sumArr[i] - upper;
            long max = sumArr[i] - lower;
            while(leftX <= M && sumArr[leftX] < min) {
                leftX++;
            }

            while(leftY <= M && sumArr[leftY] <= max) {
                leftY++;
            }

            count += leftY - leftX;
        }

        long[] help = new long[R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        int helpIndex = 0;
        while(p1 <= M && p2 <= R) {
            help[helpIndex++] = sumArr[p1] < sumArr[p2] ? sumArr[p1++] : sumArr[p2++];
        }

        while(p1 <= M) {
            help[helpIndex++] = sumArr[p1++];
        }

        while(p2 <= R) {
            help[helpIndex++] = sumArr[p2++];
        }

        for(int i = 0; i < help.length; i++) {
            sumArr[L + i] = help[i];
        }

        return count;
    }

    public static void main(String[] args) {
//        int[] arr = {-2147483647,0,-2147483647,2147483647};
//        int lower = -564;
//        int upper = 3864;
//        int count = new CountOfRangeSum_0205().countRangeSum(arr, lower, upper);
//        System.out.println(count);

//        System.out.println(Integer.MAX_VALUE);

         // 测试：https://leetcode.com/problems/count-of-range-sum/
    }
}
