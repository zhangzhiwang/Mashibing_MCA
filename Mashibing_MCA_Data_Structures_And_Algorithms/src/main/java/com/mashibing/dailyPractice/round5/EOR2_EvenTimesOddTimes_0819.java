package com.mashibing.dailyPractice.round5;

/**
 * 给定一个数组，这个数组里面有且只有一个数出现的次数是奇数次，其它所有元素出现的次数都是偶数次，找到这个出现奇数次的元素。
 */
public class EOR2_EvenTimesOddTimes_0819 {
    public static int eOR2_EvenTimesOddTimes(int[] arr) {
        if(arr == null || arr.length == 0) {
            return 0;
        }

        int num = 0;
        for (int i : arr) {
            num ^= i;
        }

        return num;
    }

    // 老师的代码
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    public static void main(String[] args) {
        int[] arr = {3,5,7,9,1,2,1,3,5,7,9};
        System.out.println("correct ans:");
        printOddTimesNum1(arr);
        System.out.println("my ans:");
        System.out.println(eOR2_EvenTimesOddTimes(arr));
    }
}
