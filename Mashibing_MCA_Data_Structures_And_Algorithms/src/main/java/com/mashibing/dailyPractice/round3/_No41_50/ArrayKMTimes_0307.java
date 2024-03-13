package com.mashibing.dailyPractice.round3._No41_50;

/**
 * 给定一个int类型的数组，有且只有一个元素出现了k次，其它所有元素都出现了m次，其中m>1且k<m，
 * 找出出现k次的这个元素是什么，要求时间复杂度是O(N)，空间复杂度是O(1)。
 */
public class ArrayKMTimes_0307 {
    public static int arrayKMTimes(int[] arr, int k, int m) {
        if(arr == null || arr.length < 3) {
            throw new RuntimeException("参数有误");
        }

        int[] help = new int[32];
        for (int i : arr) {
            for (int j = 0; j < 32; j++) {
//                if((i & (1 << j)) != 0) {
//                    help[j]++;
//                }
                help[j] += (i & (1 << j)) != 0 ? 1 : 0;
            }
        }

        int r = 0;
        for (int i = 0; i < help.length; i++) {
            if (help[i] % m != 0) {
                r += (1 << i);
            }
        }

        return r;
    }

    public static void main(String[] args) {
        int[] arr = {8,1,3,5,7,9,9,7,5,3,1};
        System.out.println(arrayKMTimes(arr, 1, 2));
    }
}
