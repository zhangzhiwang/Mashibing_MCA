package com.mashibing.dailyPractice.round3._No11_20;

/**
 * 给定一个无序数组arr，求累加和落在[lower,upper]范围内的子数组的个数
 */
public class CountOfRangeSum_0305 {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        long[] sumArr = new long[nums.length];
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sumArr[i] = sum += nums[i];
        }

        return recurse(sumArr, 0, sumArr.length - 1, lower, upper);
    }

    private int recurse(long[] sumArr, int L, int R, int lower, int upper) {
        if (L == R) {
            return sumArr[L] >= lower && sumArr[L] <= upper ? 1 : 0;
        }

        int M = L + ((R - L) >> 1);
        int left = recurse(sumArr, L, M, lower, upper);
        int right = recurse(sumArr, M + 1, R, lower, upper);
        int merge = merge(sumArr, L, M, R, lower, upper);
        return left + right + merge;
    }

    private int merge(long[] sumArr, int L, int M, int R, int lower, int upper) {
        int p2 = M + 1;
        int lS = L;
        int lE = L;
        int count = 0;
        while (p2 <= R) {
            long smallest = sumArr[p2] - upper;
            long largest = sumArr[p2] - lower;
            while (lS <= M && sumArr[lS] < smallest) {
                lS++;
            }

            while (lE <= M && sumArr[lE] <= largest) {
                lE++;
            }

            count += lE - lS;
            p2++;
        }

        long[] help = new long[R - L + 1];
        int p1 = L;
        p2 = M + 1;
        int helpIndex = 0;
        while(p1 <= M && p2 <= R) {
            help[helpIndex++] = sumArr[p1] <= sumArr[p2] ? sumArr[p1++] : sumArr[p2++];
        }

        while (p1 <= M) {
            help[helpIndex++] = sumArr[p1++];
        }

        while (p2 <= R) {
            help[helpIndex++] = sumArr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            sumArr[L + i] = help[i];
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = {1,-2,-1, 0};
        System.out.println(new CountOfRangeSum_0305().countRangeSum(arr, -2, 2));
    }
}
