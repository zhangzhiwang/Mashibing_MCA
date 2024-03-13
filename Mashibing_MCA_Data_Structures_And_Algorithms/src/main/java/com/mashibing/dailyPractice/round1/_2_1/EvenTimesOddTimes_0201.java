package com.mashibing.dailyPractice.round1._2_1;

/**
 * 给定一个数组，这个数组里面有且只有一个数出现的次数是奇数次，其它所有元素出现的次数都是偶数次，找到这个出现奇数次的元素。
 */
public class EvenTimesOddTimes_0201 {
    public static int evenTimesOddTimes(int[] arr) {
        if(arr == null || arr.length == 0) {
            throw new RuntimeException("参数有误！");
        }

        int eor = 0;
        for(int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }

        return eor;
    }

    public static void main(String[] args) {
        System.out.println(evenTimesOddTimes(new int[]{1,2,2,9,1,8,2,3,3,8,9,9,9}));
    }
}
