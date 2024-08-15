package com.mashibing.dailyPractice.round5;

import java.util.*;

/**
 * 抽奖
 */
public class ChouJiang {
    /**
     * 已抽过的编号
     */
    private static Set<Integer> set = new HashSet<>();

    static {
        set.addAll(Arrays.asList(
                71, 72, 87, 88, 78, 79, 84, 94, 85, 76,
                77, 38, 39, 40, 80, 81, 76, 77, 38, 39,
                40, 80, 81, 95, 96, 9, 47, 97, 37, 1,
                74, 57, 100, 7, 63, 31, 58, 35, 23, 46,
                36, 6, 69, 4
        ));
    }

    /**
     * 抽奖
     *
     * @param start 抽奖范围起始值
     * @param end   抽奖范围结束值
     * @param count 抽奖结果包含几个数
     * @return
     */
    public static List<Integer> chouJiang(int start, int end, int count) {
        List<Integer> list = new ArrayList<>();
        if (count <= 0) {
            return list;
        }

        while (count > 0) {
            int num = (int) (Math.random() * (end - start + 1)) + start;
            if (set.contains(num)) {
                continue;
            }

            list.add(num);
            count--;
        }

        System.out.println("本次抽奖结果：" + list);
        return list;
    }

    public static void main(String[] args) {
        int start = 1;
        int end = 100;
        int count = 5;
        chouJiang(start, end, count);
    }
}
