package com.mashibing.bit;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 给定一个数组，有且只有两种元素会出现奇数次，其它都为偶数次，找出这两种数（异或题2的升级版）
 * 课程：体系班课时16
 * 思路：见com.mashibing.preInEclipse.senior.bit.EOR3_EvenTimesOddTimes2注释
 */
public class EOR3_EvenTimesOddTimes2 {
    public static void main(String[] args) {
        int[] arr = {1,3,5,7,9,2,4,6,8,0,0,1,2,3,4,5,6,7,8,9,3,7};
        int[] resultArr = findTwoOddTimesElement(arr);
        DuiShuQiUtil.printArr(resultArr);
    }

    public static int[] findTwoOddTimesElement(int[] arr) {
        // TODO 校验略
        int[] retArr = new int[2];
        int eor = 0;
        for(int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        /*
         假设原数组出现奇数次的两个元素是a和b，那么经过上面的一轮异或操作，eor的结果为a^b。
         根据题目表述a!=b，所以eor!=0。由于eor不等于0，所以它的二进制形式中至少有一个1，不妨获取它二进制形式的最后一个1。
         假设eor的二进制形式的最后一个1在第三位，那么整个原数组的所有元素都可以按照第三位上是否为1分为两大阵营：一个是第三位上为1的，一个是不为1的，
         那么a和b就分别位于这两大阵营里。再设置一个变量eor'，重新遍历整个数组，让eor'只和第三位上为1的数进行异或（或者和第三位上为0的异或也可以），
         这样eor'的最终结果就可以拿到a和b其中的一个，然后让eor和eor'进行异或就得到了另一个。
         */
        // 获取eor二进制形式的最后一个1
        int rightOne = eor & (-eor);// rightOne的二进制形式是只有第三位为1，其它都是0（假设eor最右边的1在第三位）

        int _eor = 0;
        for(int i = 0; i < arr.length; i++) {
            if((arr[i] & rightOne) != 0) {// 说这这个数的第三位是1
                _eor ^= arr[i];
            }
        }
        // 经过上轮的异或操作，eor'最终不是a就是b
        retArr[0] = _eor;
        retArr[1] = eor ^ retArr[0];// 得到a和b中的另一个
        return retArr;
    }
}
