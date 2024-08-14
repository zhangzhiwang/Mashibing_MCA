package com.mashibing.dailyPractice.round4;

/**
 * 给定一个数组，有且只有两种元素会出现奇数次，其它都为偶数次，找出这两种数。（前一题的升级版）
 */
public class EOR3_EvenTimesOddTimes2_0729 {
    public static int[] evenTimesOddTimes2(int[] arr) {
        int[] result = new int[2];
        if (arr == null || arr.length < 2) {
            return result;
        }

        int eor1 = 0;
        for (int i : arr) {
            eor1 ^= i;
        }

        int eor2 = 0;
        int tmp = eor1 & (-eor1);
        for (int i : arr) {
            if((i & tmp) != 0) {
                eor2 ^= i;
            }
        }

        result[0] = eor2;
        result[1] = eor1 ^ eor2;
        return result;
    }

    // 老师的代码
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // a 和 b是两种数
        // eor != 0
        // eor最右侧的1，提取出来
        // eor :     00110010110111000
        // rightOne :00000000000001000
        int rightOne = eor & (-eor); // 提取出最右的1


        int onlyOne = 0; // eor'
        for (int i = 0 ; i < arr.length;i++) {
            //  arr[1] =  111100011110000
            // rightOne=  000000000010000
            if ((arr[i] & rightOne) != 0) {
                onlyOne ^= arr[i];
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,7,9,9,7,3,1,13};
        System.out.println("correct ans:");
        printOddTimesNum2(arr);
        System.out.println("my ans:");
        int[] ints = evenTimesOddTimes2(arr);
        System.out.println(ints[0]);
        System.out.println(ints[1]);
    }
}
