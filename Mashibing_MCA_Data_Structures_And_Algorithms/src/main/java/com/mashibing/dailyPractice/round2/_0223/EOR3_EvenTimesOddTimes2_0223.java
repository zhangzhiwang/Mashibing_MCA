package com.mashibing.dailyPractice.round2._0223;

/**
 * 给定一个数组，有且只有两种元素会出现奇数次，其它都为偶数次，找出这两种数。
 */
public class EOR3_EvenTimesOddTimes2_0223 {
    public static int[] evenTimesOddTimes2(int[] arr) {
        if(arr == null || arr.length < 3) {
            throw new RuntimeException("参数有误");
        }

        int eor1 = 0;
        for(int i : arr) {
            eor1 ^= i;
        }

        int tmp = eor1 & (-eor1);
        int eor2 = 0;
        for(int i : arr) {
            if((tmp & i) != 0) {
                eor2 ^= i;
            }
        }

        return new int[]{eor2, eor1 ^ eor2};
    }

    public static void main(String[] args) {
        int[] arr = {2,1,3,5,7,2,9,9,7,5,2,3,1,8};
        int[] result = evenTimesOddTimes2(arr);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
