package com.mashibing.dailyPractice.round2._71_to_100;

/**
 * 动态规划题目2：
 * 给定一个整型数组arr，代表数值（非负）不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走一张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数（牌面上的值就是分数值）。
 */
public class CardsInLine_0510 {
    public static int cardsInLine1(int[] arr) {
        if(arr == null || arr.length == 0) {
            return -1;
        }

        int N = arr.length;
        return Math.max(pre(arr, 0, N - 1), post(arr, 0, N - 1));
    }

    private static int pre(int[] arr, int L, int R) {
        if(L == R) {
            return arr[L];
        }

        return Math.max(arr[L] + post(arr, L + 1, R), arr[R] + post(arr, L, R - 1));
    }

    private static int post(int[] arr, int L, int R) {
        if(L == R) {
            return 0;
        }

        return Math.min(pre(arr, L + 1, R), pre(arr, L, R - 1));
    }

    public static int cardsInLine2(int[] arr) {
        if(arr == null || arr.length == 0) {
            return -1;
        }

        int N = arr.length;
        return Math.max(pre(arr, 0, N - 1), post(arr, 0, N - 1));
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

    // // arr[L..R]，后手获得的最好分数返回
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
    }
}
