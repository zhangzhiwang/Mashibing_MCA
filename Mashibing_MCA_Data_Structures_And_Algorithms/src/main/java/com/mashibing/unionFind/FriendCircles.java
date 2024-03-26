package com.mashibing.unionFind;

import java.util.HashMap;
import java.util.Map;

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
 * 另外要强调的是——要举一反三，leetcode上的这道题描述的背景和朋友圈问题的背景不一样，换汤不换药，本质上说的是一个意思，
 * leetcode题目：https://leetcode.cn/problems/number-of-provinces/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 * <p>
 *
 * 思路：互相认识的二个人所代表的集合union，最终返回集合的数量
 *
 * 课程：体系班课时126
 */
public class FriendCircles {
    public static int friendCircleCount(int[][] arr) {
        int N = arr.length;
        UnionFind2 unionFind = new UnionFind2(N);
        /*
         遍历矩阵
         根据题目要求，arr[i][j] = arr[j][i]，所以从arr[0][0]到arr[N-1][N-1]画一条对角线，遍历整个矩阵一半儿即可
         */
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {// 两层嵌套循环就是以对角线分割遍历矩阵的一半儿
                if (arr[i][j] == 1) {
                    // 如果i和j认识，那么就合并i背后所在的集合和j背后所在的集合
                    unionFind.union(i, j);
                }
            }
        }

        return unionFind.size();
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
//        int circleCount = friendCircleCount(arr);
//        System.out.println(circleCount);

        // 第二组测试数据
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
//        a[3] = 0;
//        a[4] = 0;
//        arr[1] = a;
//
//        a = new int[N];
//        a[0] = 0;
//        a[1] = 0;
//        a[2] = 1;
//        a[3] = 1;
//        a[4] = 1;
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
//        a[2] = 1;
//        a[3] = 0;
//        a[4] = 1;
//        arr[4] = a;
//
//        int circleCount = friendCircleCount(arr);
//        System.out.println(circleCount);

        // 第三组测试数据
//        N = 15;
//        arr = new int[N][];
//        arr[0] = new int[]{1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0};
//        arr[1] = new int[]{1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//        arr[2] = new int[]{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//        arr[3] = new int[]{0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};
//        arr[4] = new int[]{0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0};
//        arr[5] = new int[]{0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0};
//        arr[6] = new int[]{0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0};
//        arr[7] = new int[]{1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0};
//        arr[8] = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0};
//        arr[9] = new int[]{0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1};
//        arr[10] = new int[]{0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0};
//        arr[11] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0};
//        arr[12] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0};
//        arr[13] = new int[]{0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0};
//        arr[14] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1};
//        int circleCount = friendCircleCount(arr);
//        System.out.println("circleCount1 = " + circleCount);

//        Integer i1 = new Integer(1);
//        Integer i2 = new Integer(1);
//        System.out.println(i1 == i2);
//
//        Map<Integer, String> map = new HashMap<>();
//        map.put(i1, "aaa");
//        map.put(i2, "bbb");
//        System.out.println("size = " + map.size());
//        System.out.println(map.get(i1));
//        System.out.println(map.get(i2));

//        String s1 = new String("a");
//        String s2 = new String("a");
//        System.out.println(s1 == s2);
//
//        Map<String, Integer> map = new HashMap<>();
//        map.put(s1, 123);
//        map.put(s2, 456);
//        System.out.println("size = " + map.size());
//        System.out.println(map.get(s1));
//        System.out.println(map.get(s2));

        Character c1 = new Character('1');
        if(c1.charValue() == '1') {
            System.out.println("ok");
        }
    }
}
