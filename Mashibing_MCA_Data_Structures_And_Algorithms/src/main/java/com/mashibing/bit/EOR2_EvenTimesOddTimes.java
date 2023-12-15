package com.mashibing.bit;

/**
 * 给定一个数组，这个数组里面有且只有一个数出现的次数是奇数次，其它所有元素出现的次数是偶数次，找到这个出现奇数次的元素
 * 课程：体系班课时14
 * 思路：见com.mashibing.preInEclipse.senior.bit.EOR2_EvenTimesOddTimes注释
 */
public class EOR2_EvenTimesOddTimes {
    public static void main(String[] args) {
        int[] arr = {1,3,5,7,7,5,3,1,2,3,3};
        int result = findEvenTimesElement(arr);
        System.out.println(result);
    }

    public static int findEvenTimesElement(int[] arr) {
        // TODO 校验略
        int eor = 0;// eor的初始值必须为0
        for(int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }

        return eor;
    }
}
