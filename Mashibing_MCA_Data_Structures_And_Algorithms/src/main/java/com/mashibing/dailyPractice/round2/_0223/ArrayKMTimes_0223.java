package com.mashibing.dailyPractice.round2._0223;

import com.mashibing.others.DuiShuQiUtil;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一个int类型的数组，有且只有一个元素出现了k次，其它所有元素都出现了m次，其中m>1且k<m，
 * 找出出现k次的这个元素是什么，要求时间复杂度是O(N)，空间复杂度是O(1)。
 */
public class ArrayKMTimes_0223 {
    public static int arrayKMTimes(int[] arr, int k, int m) {
        if(arr == null || arr.length == 0) {
            throw new RuntimeException("参数有误");
        }

        int[] help = new int[32];
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < 32; j++) {
                int tmp = (arr[i] & (1 << j)) == 0 ? 0 : 1;
                help[j] += tmp;
            }
        }

        int result = 0;
        for(int i = 0; i < help.length; i++) {
            if(help[i] % m != 0) {
                result += 1 << i;
            }
        }

        return result;
    }

    // 更简洁的写法
    public static int km(int[] arr, int k, int m) {
        int[] help = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                help[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            help[i] %= m;
            if (help[i] != 0) {
                ans |= 1 << i;
            }
        }
        int real = 0;
        for (int num : arr) {
            if (num == ans) {
                real++;
            }
        }
        return real == k ? ans : -1;
    }

    // 为了测试
    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        int ktimeNum = randomNumber(range);
        // 真命天子出现的次数
        int times = Math.random() < 0.5 ? k : ((int) (Math.random() * (m - 1)) + 1);
        // 2
        int numKinds = (int) (Math.random() * maxKinds) + 2;
        // k * 1 + (numKinds - 1) * m
        int[] arr = new int[times + (numKinds - 1) * m];
        int index = 0;
        for (; index < times; index++) {
            arr[index] = ktimeNum;
        }
        numKinds--;
        HashSet<Integer> set = new HashSet<>();
        set.add(ktimeNum);
        while (numKinds != 0) {
            int curNum = 0;
            do {
                curNum = randomNumber(range);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
        }
        // arr 填好了
        for (int i = 0; i < arr.length; i++) {
            // i 位置的数，我想随机和j位置的数做交换
            int j = (int) (Math.random() * arr.length);// 0 ~ N-1
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    // 为了测试
    // [-range, +range]
    public static int randomNumber(int range) {
        return (int) (Math.random() * (range + 1)) - (int) (Math.random() * (range + 1));
    }

    public static int test(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                return num;
            }
        }
        return -1;
    }

    // 为了测试
    public static void main(String[] args) {
//        int kinds = 5;
//        int range = 30;
//        int testTime = 100000;
//        int max = 9;
//        System.out.println("测试开始");
//        for (int i = 0; i < testTime; i++) {
//            int a = (int) (Math.random() * max) + 1; // a 1 ~ 9
//            int b = (int) (Math.random() * max) + 1; // b 1 ~ 9
//            int k = Math.min(a, b);
//            int m = Math.max(a, b);
//            // k < m
//            if (k == m) {
//                m++;
//            }
//            int[] arr = randomArray(kinds, range, 1, 2);
//            int ans1 = test(arr, k, m);
//            int ans2 = arrayKMTimes(arr, k, m);
////            int ans3 = km(arr, k, m);
////            if (ans1 != ans2 || ans1 != ans3) {
////                System.out.println(ans1);
////                System.out.println(ans3);
////                System.out.println("出错了！");
////            }
//            if (ans1 != ans2) {
//                DuiShuQiUtil.printArr(arr);
//                System.out.println(ans1);
//                System.out.println(ans2);
////                System.out.println(ans3);
//                System.out.println("出错了！");
//                break;
//            }
//        }
//        System.out.println("测试结束");

        int[] arr = {-2,	-8,	2,	-2,	-8,	2,	-14};
        System.out.println(arrayKMTimes(arr, 1, 2));
    }
}
