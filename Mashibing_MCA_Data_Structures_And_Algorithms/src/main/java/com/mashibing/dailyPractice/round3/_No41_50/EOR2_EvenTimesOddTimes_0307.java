package com.mashibing.dailyPractice.round3._No41_50;

/**
 * 给定一个数组，这个数组里面有且只有一个数出现的次数是奇数次，其它所有元素出现的次数都是偶数次，找到这个出现奇数次的元素。
 */
public class EOR2_EvenTimesOddTimes_0307 {
    public static int evenTimesOddTimes(int[] arr) {
        if(arr == null || arr.length < 3) {
            throw new RuntimeException("参数有误");
        }

        int eor = 0;
        for (int i : arr) {
            eor ^= i;
        }
        return eor;
    }

    public static void main(String[] args) {
        int[] arr = {3,5,7,9,1,2,1,3,5,7,9};
        System.out.println(evenTimesOddTimes(arr));
    }
}
