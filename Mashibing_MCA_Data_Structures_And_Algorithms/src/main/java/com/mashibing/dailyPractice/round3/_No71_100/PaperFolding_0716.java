package com.mashibing.dailyPractice.round3._No71_100;

/**
 * 折纸问题：拿出一个没有任何折痕的纸条，先对折一次，会在中间出现一条凹折痕，对折两次会出现：凹、凹、凸折痕，
 * 对折三次会出现：凹、凹、凸、凹、凹、凸、凸折痕，如果这个纸条对折N次，请按顺序打印折痕。
 */
public class PaperFolding_0716 {
    public static void paperFolding(int N) {
        if (N <= 0) {
            return;
        }

        recurse(N, 1, true);
    }

    private static void recurse(int N, int i, boolean isAo) {
        if (i > N) {
            return;
        }

        recurse(N, i + 1, true);
        System.out.print(isAo ? "凹 " : "凸 ");
        recurse(N, i + 1, false);
    }

    // 老师的代码
    public static void printAllFolds(int N) {
        process(1, N, true);
        System.out.println();
    }

    // 当前你来了一个节点，脑海中想象的！
    // 这个节点在第i层，一共有N层，N固定不变的
    // 这个节点如果是凹的话，down = T
    // 这个节点如果是凸的话，down = F
    // 函数的功能：中序打印以你想象的节点为头的整棵树！
    public static void process(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        process(i + 1, N, true);
        System.out.print(down ? "凹 " : "凸 ");
        process(i + 1, N, false);
    }

    public static void main(String[] args) {
        int N = 3;
        System.out.println("my ans:");
        paperFolding(N);
        System.out.println();

        System.out.println("correct ans:");
        printAllFolds(N);
    }
}
