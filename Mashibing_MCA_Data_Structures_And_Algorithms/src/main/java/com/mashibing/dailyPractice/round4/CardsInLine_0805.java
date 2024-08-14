package com.mashibing.dailyPractice.round4;

/**
 * 动态规划题目2：
 * 给定一个整型数组arr，代表数值（非负）不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走一张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数（牌面上的值就是分数值）。
 */
public class CardsInLine_0805 {
    public static int cardsInLine1(int[] arr) {
        if(arr == null || arr.length == 0) {
            return -1;
        }

        int p1 = pre(arr, 0, arr.length - 1);
        int p2 = post(arr, 0, arr.length - 1);
        return Math.max(p1, p2);
    }

    private static int pre(int[] arr, int l, int r) {
        if(l == r) {
            return arr[l];
        }

        int p1 = arr[l] + post(arr, l + 1, r);
        int p2 = arr[r] + post(arr, l, r - 1);
        return Math.max(p1, p2);
    }

    private static int post(int[] arr, int l, int r) {
        if(l == r) {
            return 0;
        }

        int p1 = pre(arr, l + 1, r);
        int p2 = pre(arr, l, r - 1);
        return Math.min(p1, p2);
    }

    public static int cardsInLine2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int n = arr.length;
        int[][] buf1 = new int[n][n];
        int[][] buf2 = new int[n][n];
        for (int i = 0; i < n; i++) {
            buf1[i][i] = arr[i];
        }

        for (int j = 1; j < n; j++) {
            for (int i = j - 1; i >= 0; i--) {
                buf1[i][j] = Math.max(arr[i] + buf2[i + 1][j], arr[j] + buf2[i][j - 1]);
                buf2[i][j] = Math.min(buf1[i + 1][j], buf1[i][j - 1]);
            }
        }

        return Math.max(buf1[0][n - 1], buf2[0][n - 1]);
    }

    // 老师的代码
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = f1(arr, 0, arr.length - 1);
        int second = g1(arr, 0, arr.length - 1);
        return Math.max(first, second);
    }

    // arr[L..R]，先手获得的最好分数返回
    public static int f1(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int p1 = arr[L] + g1(arr, L + 1, R);
        int p2 = arr[R] + g1(arr, L, R - 1);
        return Math.max(p1, p2);
    }

    public static int g1(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int p1 = f1(arr, L + 1, R); // 对手拿走了L位置的数
        int p2 = f1(arr, L, R - 1); // 对手拿走了R位置的数
        return Math.min(p1, p2);
    }

    public static void main(String[] args) {
        System.out.println("correct ans:");
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(win1(arr));
        System.out.println("my ans:");
        System.out.println(cardsInLine1(arr));
        System.out.println(cardsInLine2(arr));
    }
}
