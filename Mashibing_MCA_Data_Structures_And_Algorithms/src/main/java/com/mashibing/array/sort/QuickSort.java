package com.mashibing.array.sort;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 快速排序
 * 课程：新手班课时57-59
 * 思路：见com.mashibing.preInEclipse.junior.array.sort.QuickSort注释
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2,1,0,5,9,5,3,5,10,20,5};
        quickSort(arr, 0 ,arr.length - 1);
        DuiShuQiUtil.printArr(arr);
    }

    public static void quickSort(int[] arr, int L, int R) {
        if(arr == null || arr.length < 2) {
            return;
        }

        int[] equalArr = f(arr, L, R);
        if(L < equalArr[0]) {// 如果有小于区的再递归，如果等于区的左边界就是数组的左边界，即整个数组就没有小于标注值的元素
            quickSort(arr, L, equalArr[0] - 1);
        }
        if(equalArr[1] < R) {// 如果有大于区的再递归，如果等于区的右边界就是数组的右边界，即整个数组就没有大于标注值的元素
            quickSort(arr, equalArr[1] + 1, R);
        }
    }

    /**
     * 其实这个f函数就是荷兰国旗问题——返回等于区的左边界和右边界
     * 荷兰国旗有三种颜色，返回中间颜色部分的边界
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int[] f(int[] arr, int L, int R) {
        int lessR = L - 1;// 小于区的右边界，起始位置在数组左边界的左侧
        int moreL = R;// 大于区的左边界，起始位置在数组右边界
        int i = L;// 当前指针，起始位置在数组左边界
        int numIndex = R;// 标准值的索引，就是数组的右边界
        while(i < moreL) {
            if(arr[i] < arr[numIndex]) {
//                // 1、当前位置和小于区的下一个位置做交换
//                swap(arr[i], arr[numIndex + 1]);
//                // 2、小于区右扩一个位置
//                numIndex++;
//                // 3、当前位置右移一个位置
//                i++;
                // 以上三个步骤可以简写为：
                swap(arr, i++, ++lessR);
            } else if(arr[i] > arr[numIndex]) {
//                // 1、当前位置和大于区左边界的前一个位置做交换
//                swap(arr[i], arr[moreL - 1]);
//                // 2、大于区的左边界左扩一个位置，当前位置不动
//                moreL--;
                // 以上两个步骤可以简写为：
                swap(arr, i, --moreL);
            } else {// arr[i] = arr[numIndex]
                // 当前位置右移一个位置，小于区和大于区不动
                i++;
            }
        }

        // 退出循环之后的最后一步是交换大于区左边界和数组右边界的值
        swap(arr, moreL, R);

        /*
         最后返回等于区的左边界和右边界。
         左边界是小于区的下一个位置，注意右边界就是大于区的左边界，不是左边界的前一个位置，
         因为上面最后一步已经将大于区左边界和数组右边界的值交换了，而数组的右边界就是标准值本身，所以肯定是等于标准值的，交换后大于区的右边界就是等于区的最后一个值
         */
        return new int[]{lessR + 1, moreL};
    }

    public static void swap(int[] arr, int i1, int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }
}
