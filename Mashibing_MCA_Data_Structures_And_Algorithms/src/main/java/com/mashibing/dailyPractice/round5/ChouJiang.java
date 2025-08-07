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
                26, 77, 43, 10, 67, 97, 7,
                55, 86, 50, 96, 14, 74, 88,
                51, 9, 56, 8, 29, 24, 99,
                71, 46, 19, 66, 90, 53, 98,
                85, 78, 4
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
            throw new RuntimeException("参数有误！");
        }
        if (set.size() == 100) {
            System.out.println("本轮抽奖已结束！");
            return list;
        }

        Set<Integer> set2 = new HashSet<>();
        while (count > 0) {
            int num = (int) (Math.random() * (end - start + 1)) + start;
            if (set.contains(num) || set2.contains(num)) {
                continue;
            }

            list.add(num);
            set2.add(num);
            count--;
        }

        System.out.println("本次抽奖结果：" + list);
        return list;
    }

    public static void main(String[] args) {
        int start = 1;
        int end = 100;
        int count = 7;
        chouJiang(start, end, count);
    }
}
