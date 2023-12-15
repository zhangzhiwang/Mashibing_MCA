package com.mashibing.bit;

/**
 * 给定一个int类型的数组，有且只有一个元素出现了k次，其它所有元素都出现了m次，其中m>1且k<m，找出出现k次的这个元素是什么，要求时间复杂度是O(N)，空间复杂度是O(1)
 * 课程：体系班课时17-20
 * 思路：见com.mashibing.preInEclipse.senior.bit.ArrayKMTimes注释
 */
public class ArrayKMTimes {
    public static void main(String[] args) {
        int[] arr = {1,1,2,1,3,3,3,5,5,5,2};
        int result = findKTimesElement(arr, 2, 3);
        System.out.println(result);
    }

    public static int findKTimesElement(int[] arr, int k, int m) {
        // TODO 校验略
        // allEleBitSum的每一个元素都代表arr数组所有元素在该位置上的累加和
        int[] allEleBitSum = new int[32];// 由于每一个元素都是int类型，所以在分配内存时每一个元素都初始化成默认的0，所以新创建的int数组中每一个元素都是有值的，值为0
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < 32; j++) {// 由于第二层循环的次数是个固定值，和N无关，所以第二层循环的时间复杂度是O(1)
                if((arr[i] & (1 << j)) != 0) {
                    allEleBitSum[j]++;
                }
            }
        }

        int result = 0;
        for(int i = 0; i < allEleBitSum.length; i++) {
            if(allEleBitSum[i] % m != 0) {// 说明出现k次的元素在i位置为1
                result |= 1 << i;
            }
        }

        return result;
    }
}
