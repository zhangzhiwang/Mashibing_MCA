package com.mashibing.array.sort;

import com.mashibing.others.DuiShuQiUtil;

import java.util.Stack;

/**
 * 快速排序3.0版本——随机快排
 * 课程：体系班课时44
 * 思路：快排是以范围的最后一个数作为标准值，而随机快排就是以范围上随机一个位置的数作为标准值，现将该数与范围内的最后一个数做交换，然后再按照典型的快排进行排序，
 * 即随机快排就是比典型的快排多了一步——先随机确定一个位置然后将该位置的值和范围内最后一个数做交换，这样做的目的是降低时间复杂度。
 * 在传统的快排中，最坏的情况是原数组已经是有序的了，这样快排的时间复杂度是O(N^2)，最好的情况是每次排序后范围内的最后一个数都会到中间，这样时间复杂度是O(N*logN)，
 * 如果引入随机机制，即目标数是范围内最后一个数的概率是1/N，是中间的数的概率也是1/N，如果随机后正好是最好的情况，那么这种情况就可以使用Master公式来计算时间复杂度了，
 * 因为左组和右组的递归数据规模是一样的，都是N/2，带入Master公式：T(N) = 2 * T(N/2) + O(N)，可知a=2，b=2，d=1，所以log(b,a)=d=1，所以最好的情况下最终的时间复杂度是
 * O(N*logN)，但最好的情况出现分概率只有1/N，但是通过计算数学期望并经过数学推导整体时间复杂度更收敛于O(N*logN)，所以时间复杂度降低了。
 * 空间复杂度：
 * 经典快排的额外空间复杂度是O(N)，因为经典快排是按照最坏的情况估算的，即原数组已经排好序了。由于每次递归都要保存等于区的左边界和右边界从而确定下次递归的左组和右组的范围，
 * 但是在对坏的情况下，每次等于区的左边界都是范围内的最后一个数，而数组的每一个元素都可作为某一次递归等于区的左边界（因为原数组就已经排好序了），所以申请保存左边界的变量
 * 不是和数据量无关的，而是和数据量有关的，所以额外空间复杂度是O(N)而不是O(1)。
 * 但是引入随机机制，最好的情况是左边界落在了中间的位置（右边界同理），那么每次递归只记录范围内中间的位置即可，所以额外空间复杂度是O(logN)，同理右边界也是O(logN)，
 * 忽略常数项就是O(logN)。但是最好的情况出现的概率是1/N，通过计算数学期望并经过数学推导最终的额外空间复杂度收敛于O(logN)。
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
