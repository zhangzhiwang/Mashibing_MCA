package com.mashibing.dailyPractice.round2._71_to_100._0708;

/**
 * 动态规划题目2：
 * 给定一个整型数组arr，代表数值（非负）不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走一张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数（牌面上的值就是分数值）。
 */
public class CardsInLine_0708 {
    public static int cardsInLine1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int p1 = first(arr, 0, arr.length - 1);
        int p2 = second(arr, 0, arr.length - 1);
        return Math.max(p1, p2);
    }

    private static int first(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }

        int p1 = arr[L] + second(arr, L + 1, R);
        int p2 = arr[R] + second(arr, L, R - 1);
        return Math.max(p1, p2);
    }

    private static int second(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }

        int p1 = first(arr, L + 1, R);
        int p2 = first(arr, L, R - 1);
        return Math.min(p1, p2);
    }

    public static int cardsInLine2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int N = arr.length;
        int[][] first = new int[N][N];
        int[][] second = new int[N][N];
        for (int i = 0; i < N; i++) {
            first[i][i] = arr[i];
        }

        /*
        之前写过的代码都是for里面套while，因为之前的遍历方式是斜着往上遍历，所以在循环里要对行和列进行拷贝。
        这里的遍历方式是先遍历列，然后每行从右往左遍历，即外层for循环是列顺序遍历，内层for循环是行逆序遍历，
        所以两个for循环即可，循环里也不用对行和列进行拷贝。
         */
        for (int R = 1; R < N; R++) {
            for (int i = R - 1; i >= 0; i--) {
                first[i][R] = Math.max(arr[i] + second[i + 1][R], arr[R] + second[i][R - 1]);
                second[i][R] = Math.min(first[i + 1][R], first[i][R - 1]);
            }
        }

        return Math.max(first[0][N - 1], second[0][N - 1]);
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
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(win1(arr));
        System.out.println("my ans:");
        System.out.println(cardsInLine1(arr));
        System.out.println(cardsInLine2(arr));
    }
}
