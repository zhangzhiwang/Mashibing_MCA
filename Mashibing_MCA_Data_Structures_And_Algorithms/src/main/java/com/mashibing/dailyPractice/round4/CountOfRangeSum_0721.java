package com.mashibing.dailyPractice.round4;

import com.mashibing.array.CountOfRangeSum;

/**
 * 给定一个无序数组arr，求累加和落在[lower,upper]范围内的子数组的个数
 */
public class CountOfRangeSum_0721 {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums == null || nums.length == 0 || lower > upper) {
            return 0;
        }

        int N = nums.length;
        long[] sumArr = new long[N];
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sumArr[i] = sum += nums[i];
        }

        return recurse(sumArr, 0, N - 1, lower, upper);
    }

    private static int recurse(long[] sumArr, int L, int R, int lower, int upper) {
        if(L == R) {
            return sumArr[L] >= lower && sumArr[L] <= upper ? 1 : 0;
        }

        int mid = L + ((R - L) >> 1);
        int count = 0;
        count += recurse(sumArr, L, mid, lower, upper);
        count += recurse(sumArr, mid + 1, R, lower, upper);

        int p2 = mid + 1;
        int ls = L;
        int le = L;
        while (p2 <= R) {
            long smallest = sumArr[p2] - upper;
            long largest = sumArr[p2] - lower;
            while (ls <= mid && sumArr[ls] < smallest) {
                ls++;
            }
            while (le <= mid && sumArr[le] <= largest) {
                le++;
            }

            count += le - ls;
            p2++;
        }

        long[] help = new long[R - L + 1];
        int p1 = L;
        p2 = mid + 1;
        int helpIndex = 0;
        while (p1 <= mid && p2 <= R) {
            help[helpIndex++] = sumArr[p1] <= sumArr[p2] ?  sumArr[p1++] : sumArr[p2++];
        }

        while(p1 <= mid) {
            help[helpIndex++] = sumArr[p1++];
        }

        while(p2 <= R) {
            help[helpIndex++] = sumArr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            sumArr[L + i] = help[i];
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = {-2147483647,0,-2147483647,2147483647};
        new CountOfRangeSum_0721().countRangeSum(arr, -564, 3864);

        // 测试：https://leetcode.com/problems/count-of-range-sum/
    }
}
