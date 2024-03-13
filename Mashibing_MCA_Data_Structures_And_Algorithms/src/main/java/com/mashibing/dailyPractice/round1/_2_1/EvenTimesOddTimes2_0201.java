package com.mashibing.dailyPractice.round1._2_1;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 给定一个数组，有且只有两种元素会出现奇数次，其它都为偶数次，找出这两种数。（前一题的升级版）
 */
public class EvenTimesOddTimes2_0201 {
    public static int[] evenTimesOddTimes2(int[] arr) {
        int[] retArr = new int[2];
        if(arr == null || arr.length == 0) {
            return retArr;
        }

        int eor1 = 0;// a ^ b
        for(int i = 0; i < arr.length; i++) {
            eor1 ^= arr[i];
        }

        int eor2 = 0;// a和b其中一个
        for(int i = 0; i < arr.length; i++) {
            if((arr[i] & (1 << 3)) != 0) {
                eor2 ^= arr[i];
            }
        }

        retArr[0] = eor2;
        retArr[1] = eor1 ^ eor2;// a和b的另一个
        return retArr;
    }

    public static void main(String[] args) {
        int[] arr = evenTimesOddTimes2(new int[]{1, 2, 2, 9, 1, 8, 2, 3, 3, 8});
        DuiShuQiUtil.printArr(arr);
    }
}
