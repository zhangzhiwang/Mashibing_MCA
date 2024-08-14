package com.mashibing.dailyPractice.round4.secondPractice;

/**
 * 给定一个无序数组arr，求累加和落在[lower,upper]范围内的子数组的个数
 */
public class CountOfRangeSum_0722 {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
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

    private int recurse(long[] sumArr, int l, int r, int lower, int upper) {
        if (l == r) {
            return sumArr[l] >= lower && sumArr[l] <= upper ? 1 : 0;
        }

        int m = l + ((r - l) >> 1);
        int count = 0;
        count += recurse(sumArr, l, m, lower, upper);
        count += recurse(sumArr, m + 1, r, lower, upper);

        int p2 = m + 1;
        int ls = l;
        int le = l;
        while (p2 <= r) {
            long smallest = sumArr[p2] - upper;
            long largest = sumArr[p2] - lower;
            while (ls <= m && sumArr[ls] < smallest) {
                ls++;
            }
            while (le <= m && sumArr[le] <= largest) {
                le++;
            }

            count += le - ls;
            p2++;
        }

        long[] help = new long[r - l + 1];
        int p1 = l;
        p2 = m + 1;
        int helpI = 0;
        while(p1 <= m && p2 <= r) {
            help[helpI++] = sumArr[p1] <= sumArr[p2] ? sumArr[p1++] : sumArr[p2++];
        }

        while(p1 <= m) {
            help[helpI++] = sumArr[p1++];
        }

        while(p2 <= r) {
            help[helpI++] = sumArr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            sumArr[l + i] = help[i];
        }

        return count;
    }

    public static void main(String[] args) {
        int i = Integer.MAX_VALUE + Integer.MAX_VALUE;
        System.out.println(i);
        // 测试：https://leetcode.com/problems/count-of-range-sum/
    }
}
