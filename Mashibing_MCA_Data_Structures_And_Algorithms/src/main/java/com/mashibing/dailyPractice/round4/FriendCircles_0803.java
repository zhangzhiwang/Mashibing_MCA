package com.mashibing.dailyPractice.round4;

/**
 * 并查集题目1——朋友圈问题：
 * 给定一个矩阵arr，这个矩阵是一个正方形，给定一个正整数N，N代表人的数量，同时也是矩阵横纵坐标的索引，索引范围为[0,N-1]，索引值可以看做人的名字，
 * 比如index=0，这个人就叫0，index=N-1，这个人就叫N-1。在这个矩阵中，如果两个人认识值就为1，不认识就是0，比如arr[i][j] = 1，其中i是横坐标，j是纵坐标，
 * 就代表i和j这两个人认识，规定：
 * 1、如果i认识j，那么j也认识i，即arr[i][j] = 1，那么arr[j][i]也必须为1
 * 2、如果i不认识j，那么j也不能认识i，即arr[i][j] = 0，那么arr[j][i]也必须为0
 * 3、自己跟自己认识，即所以arr[i][i]必须为1
 * 现在要划分独立的朋友圈，独立朋友圈组成的条件：朋友圈内至少要有一个共同认识的朋友，且朋友圈之内的任何人都不能再认识朋友圈之外的人。
 * 比如0认识1，0也认识3，虽然1和3之间互相不认识但是他们仨也能组成一个独立的朋友圈，因为1和3都共同认识0，一旦[0,1,3]组成朋友圈之后，
 * 那么这三个人任何人都不能再认识2和4，否则还要把认识的其他人也加进去。
 * 求整个矩阵中能够组成的独立朋友圈的个数。
 * leetcode题目：https://leetcode.cn/problems/number-of-provinces/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 */
public class FriendCircles_0803 {
    class FriendCirclesUF_0803 {
        private int[] parentArr;
        private int[] sizeArr;
        private int[] stack;
        private int size;

        public FriendCirclesUF_0803(int n) {
            if(n <= 0) {
                return;
            }

            parentArr = new int[n];
            sizeArr = new int[n];
            stack = new int[n];
            for (int i = 0; i < n; i++) {
                parentArr[i] = i;
                sizeArr[i] = 1;
                size++;
            }
        }

        private int findAnc(int i) {
            int stackI = 0;
            while (parentArr[i] != i) {
                stack[stackI++] = i;
                i = parentArr[i];
            }

            for (int j = stackI - 1; j >= 0; j--) {
                parentArr[stack[j]] = i;
            }

            return i;
        }

        private boolean isSameSet(int i1, int i2) {
            return parentArr[findAnc(i1)] == parentArr[findAnc(i2)];
        }

        private void union(int i1, int i2) {
            if(isSameSet(i1, i2)) {
                return;
            }

            int anc1 = parentArr[i1];
            int anc2 = parentArr[i2];
            int size1 = sizeArr[anc1];
            int size2 = sizeArr[anc2];
            int longAnc = size1 >= size2 ? anc1 : anc2;
            int shortAnc = longAnc == anc1 ? anc2 : anc1;
            parentArr[shortAnc] = longAnc;
            sizeArr[longAnc] = size1 + size2;
            sizeArr[shortAnc] = 0;
            size--;
        }

        private int size() {
            return size;
        }
    }

    public int findCircleNum(int[][] isConnected) {
        if(isConnected == null || isConnected.length == 0) {
            return 0;
        }

        int n = isConnected.length;
        FriendCirclesUF_0803 uf = new FriendCirclesUF_0803(n);
        for (int j = 1; j < n; j++) {
            for (int i = j - 1; i >= 0; i--) {
                if(isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }

        return uf.size();
    }
}
