package com.mashibing.dailyPractice.round1._91_to_100;

import com.mashibing.dailyPractice.round1._81_to_90.UnionFind2_0322;

/**
 * 并查集题目1——朋友圈问题：
 * 给定一个矩阵arr，这个矩阵是一个正方形，给定一个正整数N，N代表人的数量，同时也是矩阵横纵坐标的索引，索引范围为[0,N-1]，索引值可以看做人的名字，
 * 比如index=0，这个人就叫0，index=N-1，这个人就叫N-1。在这个矩阵中，如果两个人认识值就为1，不认识就是0，比如arr[i][j] = 1，其中i是横坐标，j是纵坐标，
 * 就代表i和j这两个人认识，规定：
 * 1、如果i认识j，那么j也认识i，即arr[i][j] = 1，那么arr[j][i]也必须为1
 * 2、如果i不认识j，那么j也不能认识i，即arr[i][j] = 0，那么arr[j][i]也必须为0
 * 3、自己跟自己认识，即所以arr[i][i]必须为1
 * 现在要划分独立的朋友圈，独立朋友圈组成的条件：朋友圈内至少要有一个共同认识的朋友，且朋友圈之内的任何人都不能再认识朋友圈之外的人。
 * 比如0认识1，0也认识3，但是1和3之间互相不认识，但是他们仨也能组成一个独立的朋友圈，因为1和3都共同认识0，一旦[0,1,3]组成朋友圈之后，
 * 那么这三个人任何人都不能再认识2和4，否则还要把认识的其他人也加进去。
 * 求整个矩阵中能够组成的独立朋友圈的个数。
 * leetcode题目：https://leetcode.cn/problems/number-of-provinces/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 */
public class FriendCircles_0322 {
    public static int friendCircles(int[][] arr) {
        if(arr == null || arr.length == 0) {
            return 0;
        }

        int N = arr.length;
        UnionFind2_0322 uf = new UnionFind2_0322(N);
        for(int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if(arr[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }

        return uf.size();
    }

    public static void main(String[] args) {
        // 第一组测试数据
        int N = 5;
        int[][] arr = new int[N][];

//        int[] a = new int[N];
//        a[0] = 1;
//        a[1] = 1;
//        a[2] = 0;
//        a[3] = 1;
//        a[4] = 0;
//        arr[0] = a;
//
//        a = new int[N];
//        a[0] = 1;
//        a[1] = 1;
//        a[2] = 0;
//        a[3] = 1;
//        a[4] = 0;
//        arr[1] = a;
//
//        a = new int[N];
//        a[0] = 0;
//        a[1] = 0;
//        a[2] = 1;
//        a[3] = 1;
//        a[4] = 0;
//        arr[2] = a;
//
//        a = new int[N];
//        a[0] = 1;
//        a[1] = 1;
//        a[2] = 1;
//        a[3] = 1;
//        a[4] = 0;
//        arr[3] = a;
//
//        a = new int[N];
//        a[0] = 0;
//        a[1] = 0;
//        a[2] = 0;
//        a[3] = 0;
//        a[4] = 1;
//        arr[4] = a;
//
//        int circleCount = friendCircles(arr);
//        System.out.println(circleCount);

        // 第二组测试数据
        int[] a = new int[N];
        a[0] = 1;
        a[1] = 1;
        a[2] = 0;
        a[3] = 1;
        a[4] = 0;
        arr[0] = a;

        a = new int[N];
        a[0] = 1;
        a[1] = 1;
        a[2] = 0;
        a[3] = 0;
        a[4] = 0;
        arr[1] = a;

        a = new int[N];
        a[0] = 0;
        a[1] = 0;
        a[2] = 1;
        a[3] = 1;
        a[4] = 1;
        arr[2] = a;

        a = new int[N];
        a[0] = 1;
        a[1] = 1;
        a[2] = 1;
        a[3] = 1;
        a[4] = 0;
        arr[3] = a;

        a = new int[N];
        a[0] = 0;
        a[1] = 0;
        a[2] = 1;
        a[3] = 0;
        a[4] = 1;
        arr[4] = a;

        int circleCount = friendCircles(arr);
        System.out.println(circleCount);

        // leetcode题目：https://leetcode.cn/problems/number-of-provinces/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
    }
}
