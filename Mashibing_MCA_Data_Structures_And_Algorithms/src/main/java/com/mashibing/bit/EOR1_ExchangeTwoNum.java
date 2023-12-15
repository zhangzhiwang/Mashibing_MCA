package com.mashibing.bit;

import com.mashibing.array.sort.SelectionSort;

/**
 * 不协助辅助变量交换两个数
 * 课程：体系班课时13
 * 思路：见com.mashibing.preInEclipse.senior.bit.EOR1_ExchangeTwoNum注释
 */
public class EOR1_ExchangeTwoNum {
    public static void main(String[] args) {
        int[] arr = new int[]{10, 5};
        System.out.println("交换前：");
        SelectionSort.printArr(arr);

        exchangeTwoNum(arr, 0, 1);

        System.out.println("交换后：");
        SelectionSort.printArr(arr);
    }

    public static void exchangeTwoNum(int[] arr, int i, int j) {
        // TODO 校验略
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
