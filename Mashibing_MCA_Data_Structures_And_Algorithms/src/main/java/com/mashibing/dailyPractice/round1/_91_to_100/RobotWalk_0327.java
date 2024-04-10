package com.mashibing.dailyPractice.round1._91_to_100;

/**
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 */
public class RobotWalk_0327 {
    public static int robotWalk1(int N, int M, int P, int K) {
        if (K < 1) {
            return 0;
        }

        return recurse1(N, M, P, K);
    }

    private static int recurse1(int N, int start, int target, int rest) {
        if (rest == 0) {
            return start == target ? 1 : 0;
        }

        int total = 0;
        if (start == 1) {
            total = recurse1(N, 2, target, rest - 1);
        } else if (start == N) {
            total = recurse1(N, N - 1, target, rest - 1);
        } else {
            total = recurse1(N, start - 1, target, rest - 1) + recurse1(N, start + 1, target, rest - 1);
        }
        return total;
    }

    public static int robotWalk2(int N, int M, int P, int K) {
        if (K < 1) {
            return 0;
        }

        int[][] buf = new int[N + 1][K + 1];
        for (int i = 1; i < buf.length; i++) {
            for (int j = 0; j < buf[0].length; j++) {
                buf[i][j] = -1;
            }
        }

        return recurse2(N, M, P, K, buf);
    }

    private static int recurse2(int N, int start, int target, int rest, int[][] buf) {
        if (buf[start][rest] != -1) {
            return buf[start][rest];
        }

        if (rest == 0) {
            buf[start][rest] = 1;
            return start == target ? 1 : 0;
        }

        int total = 0;
        if (start == 1) {
            total = recurse2(N, 2, target, rest - 1, buf);
        } else if (start == N) {
            total = recurse2(N, N - 1, target, rest - 1, buf);
        } else {
            total = recurse2(N, start - 1, target, rest - 1, buf) + recurse2(N, start + 1, target, rest - 1, buf);
        }

        buf[start][rest] = total;
        return total;
    }

    public static int robotWalk3(int N, int M, int P, int K) {
        if (K < 1) {
            return 0;
        }

        int[][] arr = new int[N + 1][K + 1];
        arr[P][0] = 1;

        for (int j = 1; j < arr[0].length; j++) {
            for (int i = 1; i < arr.length; i++) {
                if (i == 1) {
                    arr[i][j] = arr[2][j - 1];
                } else if (i == N) {
                    arr[i][j] = arr[i - 1][j - 1];
                } else {
                    arr[i][j] = arr[i - 1][j - 1] + arr[i + 1][j - 1];
                }
            }
        }

        return arr[M][K];
    }

    // 老师的代码，用于测试
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
        int M = 1;
        int P = 3;
        int K = 4;
        int ans = ways1(N, M, P, K);
        System.out.println("ans = " + ans);
        int ans1 = robotWalk1(N, M, P, K);
        System.out.println(ans == ans1);
        int ans2 = robotWalk2(N, M, P, K);
        System.out.println(ans == ans2);
        int ans3 = robotWalk3(N, M, P, K);
        System.out.println(ans == ans3);
    }
}
