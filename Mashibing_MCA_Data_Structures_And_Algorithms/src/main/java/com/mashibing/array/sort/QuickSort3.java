package com.mashibing.array.sort;

import com.mashibing.others.DuiShuQiUtil;

import java.util.Stack;

/**
 * 快速排序3.0版本——随机快排
 * 课程：体系班课时44
 * 思路：快排是以范围的最后一个数作为标准值，而随机快排就是以范围上随机一个位置的数作为标准值，现将该数与范围内的最后一个数做交换，然后再按照典型的快排进行排序，
 * 即随机快排就是比典型的快排多了一步——先随机确定一个位置然后将该位置的值会范围内最后一个数做交换，这样做的目的是降低时间复杂度。
 * 在传统的快排中，最坏的情况是原数组已经是有序的了，这样快排的时间复杂度是O(N^2)，最好的情况是每次排序后范围内的最后一个数都会到中间，这样时间复杂度是O(N*logN)，
 * 如果引入随机机制，即目标数是范围内最后一个数的概率是1/N，是中间的数的概率也是1/N，经过数学推到整个时间复杂度更收敛于O(N*logN)，所以时间复杂度降低了。
 * 课程：体系班课时44
 */
public class QuickSort3 {
    public static void main(String[] args) {
        int[] arr = { 1, 7, 4, 4, 5, 6, 0, 2, 9, 8, 4 };
        quickSort3(arr);
        DuiShuQiUtil.printArr(arr);
    }

    public static void quickSort3(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }

        f(arr, 0, arr.length - 1);
    }

    private static void f(int[] arr, int L, int R) {
        if(L >= R) {// 注意：base case的条件是L >= R，而不是L == R
            return;
        }

        // 在[L,R]范围上随机找出一个位置，然后和R位置上的数做交换
        int randomIndex = (int)(Math.random() * (R - L + 1));
        swap(arr, L + randomIndex, R);// 注意：是L + randomIndex位置上的数和R上的交换，不是randomIndex位置与R位置交换
        int[] hollandFlag = hollandFlag(arr, L, R);
        f(arr, L, hollandFlag[0] - 1);
        f(arr, hollandFlag[0] + 1, R);
    }

    private static int[] hollandFlag(int[] arr, int L, int R) {
        int lessArea = L - 1;
        int moreArea = R;
        int cur = L;

        while (cur < moreArea) {// 时间复杂度是O(N)
            if(arr[cur] < arr[R]) {
//                swap(arr, cur, lessArea + 1);
//                lessArea++;
//                cur++;

                swap(arr, cur++, ++lessArea);
            } else if(arr[cur] > arr[R]) {
//                swap(arr, cur, moreArea - 1);
//                moreArea--;

                swap(arr, cur, --moreArea);
            } else {
                cur++;
            }
        }
        swap(arr, moreArea, R);

        return new int[]{lessArea + 1, moreArea};
    }

    private static void swap(int[] arr, int i, int j) {
//        arr[i] = arr[i] ^ arr[j];
//        arr[j] = arr[i] ^ arr[j];
//        arr[i] = arr[i] ^ arr[j];

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
