package com.mashibing.dailyPractice.round2._0304;

/**
 * 给定一个数组，有且只有两种元素会出现奇数次，其它都为偶数次，找出这两种数。（前一题的升级版）
 */
public class EOR3_EvenTimesOddTimes2_0304 {
    public static int[] EOR3_EvenTimesOddTimes2(int[] arr) {
        if(arr == null || arr.length < 2) {
            throw new RuntimeException("参数有误");
        }

        int eor1 = 0;
        for(int i : arr) {
            eor1 ^= i;
        }

        int tmp = eor1 & (-eor1);
        int eor2 = 0;
        for(int i = 0; i < arr.length; i++) {
            if((arr[i] & tmp) != 0) {
                eor2 ^= arr[i];
            }
        }

        return new int[]{eor2, eor1 ^ eor2};
    }

    public static void main(String[] args) {
        int[] arr = {1, 2};
        int[] ints = EOR3_EvenTimesOddTimes2(arr);
        System.out.println(ints[0]);
        System.out.println(ints[1]);
    }
}
