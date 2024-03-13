package com.mashibing.dailyPractice.round3._No41_50;

/**
 * 给定一个数组，有且只有两种元素会出现奇数次，其它都为偶数次，找出这两种数。
 */
public class EOR3_EvenTimesOddTimes2_0307 {
    public static int[] evenTimesOddTimes2(int[] arr) {
        if(arr == null || arr.length < 3) {
            return null;
        }

        int eor1 = 0;
        for (int i : arr) {
            eor1 ^= i;
        }

        int tmp = eor1 & (-eor1);
        int eor2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if((arr[i] & tmp) != 0) {
                eor2 ^= arr[i];
            }
        }

        return new int[]{eor2, eor1 ^ eor2};
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,7,9,9,7,3,1,13};
        int[] ints = evenTimesOddTimes2(arr);
        System.out.println(ints[0]);
        System.out.println(ints[1]);
    }
}
