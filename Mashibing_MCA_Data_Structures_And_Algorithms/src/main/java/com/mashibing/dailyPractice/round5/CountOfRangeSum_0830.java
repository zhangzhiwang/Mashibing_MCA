package com.mashibing.dailyPractice.round5;

/**
 * 给定一个无序数组arr，求累加和落在[lower,upper]范围内的子数组的个数
 */
public class CountOfRangeSum_0830 {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums == null || nums.length == 0 || lower > upper) {
            return 0;
        }

        long sum = 0;
        long[] sumArr = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sumArr[i] = sum += nums[i];
        }

        return recurse(sumArr, 0, sumArr.length - 1, lower, upper);
    }

    private int recurse(long[] sumArr, int l, int r, int lower, int upper) {
        if(l == r) {
            return sumArr[l] >= lower && sumArr[l] <= upper ? 1 : 0;
        }

        int m = l + ((r - l) >> 1);
        int count = 0;
        count += recurse(sumArr, l, m, lower, upper);
        count += recurse(sumArr, m + 1, r, lower, upper);

        int s = l;
        int e = l;
        int p2 = m + 1;
        while(p2 <= r) {
            long min = sumArr[p2] - upper;
            long max = sumArr[p2] - lower;
            while (s <= m && sumArr[s] < min) {
                s++;
            }
            while (e <= m && sumArr[e] <= max) {
                e++;
            }
            count += e - s;
            p2++;
        }

        int p1 = l;
        p2 = m + 1;
        long[] help = new long[r - l + 1];
        int helpI = 0;
        while (p1 <= m && p2 <= r) {
            help[helpI++] = sumArr[p1] <= sumArr[p2] ? sumArr[p1++] : sumArr[p2++];
        }
        while (p1 <= m) {
            help[helpI++] = sumArr[p1++];
        }
        while (p2 <= r) {
            help[helpI++] = sumArr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            sumArr[l + i] = help[i];
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = {2147483647,-2147483648,-1,0};
        new CountOfRangeSum_0830().countRangeSum(arr, -1, 0);
        // 测试：https://leetcode.com/problems/count-of-range-sum/
    }
}
