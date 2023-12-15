package com.mashibing.greed;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 贪心算法题目4：
 * 输入: 正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目，串行就是同一时间只能做一个项目，等上一个项目结束才能开始做下一个
 * M表示你初始的资金
 * 说明: 每做完一个项目，会马上获得收益，收益可以支持你去做下一个项目，在资金允许的情况下你可以选择做不同的项目，做的项目不同收益也不同。
 * 输出：你最后获得的最大收益。
 * <p>
 * 思路：
 * 1、准备一个小根堆，按照花费正序排序，再准备一个大根堆，按照收益倒序排序
 * 2、把所有能做的项目（花费小于M的）从小根堆中弹出来放入大根堆，从大根堆弹出来将利润累加到M中，等大根堆弹完继续从小根堆解锁能做的项目，周而复始，直到做过的项目数量等于K结束。
 * 3、返回M
 * 思路不证明了，记住即可。
 * <p>
 * 课程：体系班课时118-119
 */
public class IPO {
    static class Program {
        public int cost;
        public int profit;

        public Program(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    static class CostAsc implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.cost - o2.cost;
        }
    }

    static class ProfitDesc implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o2.profit - o1.profit;
        }
    }

    public static int ipo(int[] costs, int[] profits, int K, int M) {
        if (costs == null || costs.length == 0 || profits == null || profits.length == 0 || costs.length != profits.length) {
            return 0;
        }

        // 将花费和收益数组封装成Program对象数组
        Program[] programs = intArrToProgram(costs, profits);

        PriorityQueue<Program> costHeap = new PriorityQueue<>(new CostAsc());// 装花费的小根堆
        PriorityQueue<Program> profitHeap = new PriorityQueue<>(new ProfitDesc());// 装收益的大根堆

        for (Program program : programs) {
            costHeap.add(program);
        }

        for (int i = 0; i < K; i++) {// 以最多做几个项目作为最外层循环
            while (!costHeap.isEmpty() && costHeap.peek().cost <= M) {
                profitHeap.add(costHeap.poll());
            }

            // 题目要求最多做K个项目，但是有可能实际的资金M不足以支撑做K个项目，有可能做完了N个项目（N<K）之后发现资金已经不足以支撑继续往下做了，这中情况直接返回M
            if(profitHeap.isEmpty()) {
                return M;
            }

            M += profitHeap.poll().profit;
        }

        return M;
    }

    private static Program[] intArrToProgram(int[] costs, int[] profits) {
        Program[] programs = new Program[costs.length];
        for (int i = 0; i < costs.length; i++) {
            programs[0] = new Program(costs[i], profits[i]);
        }

        return programs;
    }
}
