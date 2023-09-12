package com.mashibing.array.sort;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 归并排序
 * 课程：新手班课时53
 * 思路：见com.mashibing.preInEclipse.junior.array.sort.MergeSort注释
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {1, 9, 3, 7, 5};
        mergeSort(arr, 0, arr.length - 1);
        DuiShuQiUtil.printArr(arr);
    }

    public static void mergeSort(int[] arr, int L, int R) {
        if (arr == null || arr.length < 2) {
            return;
        }
        if (L == R) {
            return;
        }

        f(arr, 0, R);
    }

    public static void f(int[] arr, int L, int R) {
        // base case：数组只有一个元素的时候
        if (L == R) {
            return;
        }

        int M = L + ((R - L) >> 2);// 防止越界（超过int范围）的写法
        f(arr, L, M);
        f(arr, M + 1, R);

        merge(arr, L, M, R);
    }

    /**
     * 注意：merge的作用是将左右两个数组进行排序，排序前左数组和右数组已经是有序的了，它俩各自的顺序是在上一次递归时merge方法排的
     *
     * @param arr
     * @param L
     * @param M
     * @param R
     */
    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];// 左右数组相应位置的元素一一进行比对，谁小谁放到help数组里
        int helpIndex = 0;// help数组的下标
        // 准备两个指针，一个是左数组的指针，一个是右数组的指针
        int p1 = L;// 左数组的指针从L位置开始
        int p2 = M + 1;// 右数组的指针从M+1位置开始

        // while循环什么时候停呢？左数组的指针从L位置开始往右移，移到超过M位置（也就是p1越界）的时候停，同理右数组指针从M+1位置开始往右移直到超过R位置停
        while (p1 <= M && p2 <= R) {
            // 两个指针所指的左右数组的两个元素谁小谁放到help数组里，相等放谁无所谓（这里放的是左数组的）
//            boolean isP1LEP2 = arr[p1] <= arr[p2];
//            help[helpIndex] = isP1LEP2 ? arr[p1] : arr[p2];
//            helpIndex++;
//            if(isP1LEP2) {
//                p1++;
//            } else {
//                p2++;
//            }

            // 上面的代码可以简写成一行
            help[helpIndex++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        
        /*
         由于上面的while循环，每循环一次只有一个指针移动，要么左指针p1移动，要么右指针p2移动，不可能它俩同时移动，所以肯定有一个先越界，不可能同时越界。
         所以下面的两个判断只能命中一个：要么左数组越界了，要么右数组越界了。
         */
        while (p1 <= M) {// 如果从上面的循环出来p1仍然小于M，说明左指针没越界，肯定就是右指针越界了，那么就把左数组剩下的元素拷贝到help数组里。在调用本次merge前，左右数组各自的顺序已经是排好序的了
            help[helpIndex++] = arr[p1++];
        }
        while (p2 <= R) {// 如果从上面的循环出来p2仍然小于R，说明右指针没越界，肯定就是左指针越界了，那么就把右数组剩下的元素拷贝到help数组里。
            help[helpIndex++] = arr[p2++];
        }

        // 将help数组里面的元素覆盖到原数组相应的位置
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];// 注意每一次调用merge方法都是新生成一个help数组，覆盖到原数组时要放到对应的原位置，所以是arr[L + i]
        }
    }
}
