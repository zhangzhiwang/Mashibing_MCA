package com.mashibing.dailyPractice.round2._71_to_100._0708;

/**
 * 朋友圈问题：
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 */
public class FriendCircles_0712 {
    private int[] parentArr;
    private int[] sizeArr;
    private int[] stack;
    private int size;

    public FriendCircles_0712(int N) {
        parentArr = new int[N];
        sizeArr = new int[N];
        stack = new int[N];
        size = N;

        for (int i = 0; i < N; i++) {
            parentArr[i] = i;
            sizeArr[i] = 1;
        }
    }

    private boolean isSameSet(int i1, int i2) {
        return findAns(i1) == findAns(i2);
    }

    private int findAns(int i) {
        int stackIndex = 0;
        while (parentArr[i] != i) {
            stack[stackIndex++] = i;
            i = parentArr[i];
        }

        for (int j = stackIndex - 1; j >= 0; j--) {
            parentArr[stack[j]] = i;
        }

        return i;
    }

    public void union(int i1, int i2) {
        if(isSameSet(i1, i2)) {
            return;
        }

        int ans1 = findAns(i1);
        int ans2 = findAns(i2);
        int size1 = sizeArr[ans1];
        int size2 = sizeArr[ans2];
        int longAns = size1 >= size2 ? ans1 : ans2;
        int shortAns = longAns == ans1 ? ans2 : ans1;
        parentArr[shortAns] = longAns;
        sizeArr[longAns] = size1 + size2;
        sizeArr[shortAns] = 0;
        size--;
    }

    public static int findCircleNum(int[][] isConnected) {
        if(isConnected == null || isConnected.length == 0) {
            return 0;
        }

        int N = isConnected.length;
        FriendCircles_0712 uf = new FriendCircles_0712(N);
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if(isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }

        return uf.size;
    }

    public static void main(String[] args) {
        int N = 4;
        int[][] arr = new int[N][];
        int[] a = new int[N];
        a[0] = 1;
        a[1] = 0;
        a[2] = 0;
        a[3] = 1;
//        a[4] = 0;
        arr[0] = a;

        a = new int[N];
        a[0] = 0;
        a[1] = 1;
        a[2] = 1;
        a[3] = 0;
//        a[4] = 0;
        arr[1] = a;

        a = new int[N];
        a[0] = 0;
        a[1] = 1;
        a[2] = 1;
        a[3] = 1;
//        a[4] = 1;
        arr[2] = a;

        a = new int[N];
        a[0] = 1;
        a[1] = 0;
        a[2] = 1;
        a[3] = 1;
//        a[4] = 0;
        arr[3] = a;

//        a = new int[N];
//        a[0] = 0;
//        a[1] = 0;
//        a[2] = 1;
//        a[3] = 0;
//        a[4] = 1;
//        arr[4] = a;

        int circleCount = findCircleNum(arr);
        System.out.println(circleCount);

        // leetcode题目：https://leetcode.cn/problems/number-of-provinces/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
    }
}
