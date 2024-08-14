package com.mashibing.dailyPractice.round4;

/**
 * 动态规划题目1：
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 */
public class RobotWalk_0805 {
    public static int robotWalk1(int n, int m, int k, int p) {
        if(k <= 0) {
            return 0;
        }

        return recurse(m, n, k, p);
    }

    private static int recurse(int start, int n, int rest, int p) {
        if(rest == 0) {
            return start == p ? 1 : 0;
        }

        int result = 0;
        if(start == 1) {
            result = recurse(2, n, rest - 1, p);
        } else if (start == n) {
            result = recurse(n - 1, n, rest - 1, p);
        } else {
            int p1 = recurse(start - 1, n, rest - 1, p);
            int p2 = recurse(start + 1, n, rest - 1, p);
            result = p1 + p2;
        }

        return result;
    }

    public static int robotWalk2(int n, int m, int k, int p) {
        if (k <= 0) {
            return 0;
        }

        int[][] buf = new int[n + 1][k + 1];
        for (int i = 1; i < n + 1; i++) {
            buf[i][0] = i == p ? 1 : 0;
        }

        for (int j = 1; j < k + 1; j++) {
            for (int i = 1; i < n + 1; i++) {
                if(i == 1) {
                    buf[i][j] = buf[2][j - 1];
                } else if (i == n) {
                    buf[n][j] = buf[n - 1][j - 1];
                } else {
                    buf[i][j] = buf[i - 1][j - 1] + buf[i + 1][j - 1];
                }
            }
        }

        return  buf[m][k];
    }

    // 老师的代码
    public static int ways1(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        return process1(start, K, aim, N);
    }

    // 机器人当前来到的位置是cur，
    // 机器人还有rest步需要去走，
    // 最终的目标是aim，
    // 有哪些位置？1~N
    // 返回：机器人从cur出发，走过rest步之后，最终停在aim的方法数，是多少？
    public static int process1(int cur, int rest, int aim, int N) {
        if (rest == 0) { // 如果已经不需要走了，走完了！
            return cur == aim ? 1 : 0;
        }
        // (cur, rest)
        if (cur == 1) { // 1 -> 2
            return process1(2, rest - 1, aim, N);
        }
        // (cur, rest)
        if (cur == N) { // N-1 <- N
            return process1(N - 1, rest - 1, aim, N);
        }
        // (cur, rest)
        return process1(cur - 1, rest - 1, aim, N) + process1(cur + 1, rest - 1, aim, N);
    }

    public static void main(String[] args) {
        int N = 5;
        int M = 2;
        int K = 6;
        int P = 4;
        System.out.println("correct ans:");
        System.out.println(ways1(N, M, P, K));
        System.out.println("my ans1:");
        System.out.println(robotWalk1(N, M, K, P));
        System.out.println("my ans2:");
        System.out.println(robotWalk2(N, M, K, P));
    }
}
