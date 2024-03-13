package com.mashibing.dailyPractice.round2._0304;

/**
 * 给定一个int类型的数组，有且只有一个元素出现了k次，其它所有元素都出现了m次，其中m>1且k<m，
 * 找出出现k次的这个元素是什么，要求时间复杂度是O(N)，空间复杂度是O(1)。
 */
public class ArrayKMTimes_0304 {
    public static int arrayKMTimes(int[] arr, int k, int m) {
        if(arr == null || arr.length < 3) {
            throw new RuntimeException("参数有误");
        }

        int[] help = new int[32];
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < 32; j++) {
                help[j] += (arr[i] & (1 << j)) == 0 ? 0 : 1;
            }
        }

        int result = 0;
        for(int i = 0; i < help.length; i++) {
            if(help[i] % m != 0) {
                result += (1 << i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,7,9,1,3,5,7,9,3,5,7,1};
        System.out.println(arrayKMTimes(arr, 2, 3));
    }
}
