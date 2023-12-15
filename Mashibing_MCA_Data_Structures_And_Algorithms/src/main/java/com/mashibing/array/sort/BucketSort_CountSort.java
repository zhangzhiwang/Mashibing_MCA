package com.mashibing.array.sort;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 桶排序之计数排序
 * 桶排序属于不给予比较的排序，只要是不基于比较的排序对数据的样本就会有限制，比如计数排序的样本限制就是原数组的元素必须是正数
 * 课程：体系班课时68
 * 思路：见com.mashibing.preInEclipse.senior.sort.CountSort注释
 */
public class BucketSort_CountSort {
    public static void countSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }

        int maxValue = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++) {
            maxValue = Math.max(maxValue, arr[i]);
        }

        int[] help = new int[maxValue + 1];
        for(int i = 0; i < arr.length; i++) {
            help[arr[i]]++;
        }

        int arrIndex = 0;
        for(int i = 0; i < help.length; i++) {
            while(help[i]-- > 0) {
                arr[arrIndex++] = i;
//                help[i]--;
//                arrIndex++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 7, 9, 5, 6, 5, 7, 0};
        countSort(arr);
        DuiShuQiUtil.printArr(arr);
    }
}
