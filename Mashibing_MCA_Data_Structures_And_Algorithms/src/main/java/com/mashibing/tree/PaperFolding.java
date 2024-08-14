package com.mashibing.tree;

/**
 * 折纸问题：拿出一个没有任何折痕的纸条，先对折一次，会在中间出现一条凹折痕，对折两个会出现：凹、凹、凸折痕，对折三次会出现：凹、凹、凸、凹、凹、凸、凸折痕，
 * 如果这个纸条对折N次，请按顺序打印折痕。
 * 思路：
 * 它就是一棵二叉树，按顺序打印折痕就是这棵二叉树中序遍历的结果。这棵二叉树有一个特点，就是整棵树的头结点是凹，所有左子树的头结点都是凹，所有右子树的头结点都是凸。
 * 具体可以参考截图：折纸问题分析.png
 *
 * 课程：体系班课时98
 */
public class PaperFolding {
    /**
     * 纸条一共对折N次
     * @param N
     */
    public static void printPaperFolding(int N) {
        if(N <= 0) {
            return;
        }
        f(1, N, true);
    }

    /**
     *
     * @param i 随便在二叉树中找一个节点，这个节点在这棵树中的第i层
     * @param N 纸条对折的次数（固定不变）
     * @param flag 规定凹折痕定为true，凸折痕定为false
     */
    private static void f(int i, int N, boolean flag) {
        /*
         base case：当任意指定的节点所在层次比整棵树的层次都高，这种节点不存在，直接返回，说明上一层就是叶子节点了，没有下一层。
         纸条对折N次，二叉树就有N层。
         */
        if(i > N) {
            return;
        }

        // 中序遍历
        f(i + 1, N, true);// 左
        System.out.println(flag);// 头
        f(i + 1, N, false);// 右
    }

    public static void main(String[] args) {
        printPaperFolding(3);// 打印结果true代表凹，false代表凸
    }
}
