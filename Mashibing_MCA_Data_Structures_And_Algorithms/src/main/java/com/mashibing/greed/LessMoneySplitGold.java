package com.mashibing.greed;

import java.util.PriorityQueue;

/**
 * 贪心算法第三个题目：
 * 给定一个数组，数组里面的元素都是正整数，每一个元素代表一个人拥有金条的长度，比如给定数组：[3,2,5]，代表有三个人，他们手中分别有长度为3、2、5的金条，
 * 现在把所有人的金条都摆在一起，首尾相接，粘合成一条完整的长金条，金条总长度为3+2+5=10。现在要把这个大金条进行切分，还切割成原来的长度，分别为3、2、5，
 * 切割的顺序无所谓：先切出5、再切2、再切3，或者先切出2，再切出5，再切出3等等都可以。切割的时候要给切割的师傅钱，钱数等于被切割金条在切割前的长度。
 * 比如最开始整根金条的长度为10，那么就要花费10块钱，如果从中间切一刀分成两个长度为5的金条，第二次切割的时候拿出其中一个长度为5的金条切分为长度为2和3的两块，
 * 第二次的开销就为5块钱，总控开销为10+5=15元；第二种切法就是把长度为10的金条切为长度为2和8的两块，第一刀的开销为10元，第二刀拿着长度为8的金条且为长度为5和3的两块，
 * 开销为8，总开销为10+8=18。即不同的切法总开销不一样，其中肯定有一个方案是开销最小的，返回那个最小开销。
 *
 * 思路：
 * 1、将整个数组添加到一个小根堆中
 * 2、从小根堆弹出两个元素，也就是整个堆中最小的两个元素，将这两个元素加起来得到一个sum1，将这个sum1再放入小根堆中，然后再弹出两个元素，再加和，得到sum2，再把sum2放入小根堆中，
 * 依次重复上面的过程，直到小根堆中剩一个元素停止，将所有的的sum都加起来（sum1+sum2+sum3+...sumN）得到一个总和，这个总和就是最小的开销。
 * 这道题的本质是哈夫曼编码，证明过程实际上就是在证明哈弗曼编码的过程，所以证明过程略。
 *
 * 课程：体系班课时116-117
 */
public class LessMoneySplitGold {
    /**
     *
     * @param arr 原始数组
     * @return 返回最小开销
     */
    public static int lessMoneySplitGold(int[] arr) {
        if(arr == null || arr.length == 0) {
            return 0;
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        // 将所有数加入到小根堆中
        for(int num : arr) {
            heap.add(num);
        }

        int leastSpend = 0;// 最小开销，如果数组只有一个元素，开销就是0，因为不用切
        int sum = 0;
        while(heap.size() > 1) {
            sum = heap.poll() + heap.poll();
            heap.add(sum);
            leastSpend += sum;
        }

        return leastSpend;
    }

    // 以下是对数器
    public static int lessMoney1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0);
    }

    // 等待合并的数都在arr里，pre之前的合并行为产生了多少总代价
    // arr中只剩一个数字的时候，停止合并，返回最小的总代价
    public static int process(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
            }
        }
        return ans;
    }

    public static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        int ansi = 0;
        for (int arri = 0; arri < arr.length; arri++) {
            if (arri != i && arri != j) {
                ans[ansi++] = arr[arri];
            }
        }
        ans[ansi] = arr[i] + arr[j];
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 6;
        int maxValue = 1000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            if (lessMoney1(arr) != lessMoneySplitGold(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
