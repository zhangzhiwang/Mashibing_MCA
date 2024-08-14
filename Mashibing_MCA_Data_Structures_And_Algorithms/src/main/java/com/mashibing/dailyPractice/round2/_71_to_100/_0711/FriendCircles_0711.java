package com.mashibing.dailyPractice.round2._71_to_100._0711;

/**
 * 朋友圈问题：
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 */
public class FriendCircles_0711 {
    static class FriendCirclesUF_0711 {
        private int[] parentArr;
        private int[] sizeArr;
        private int[] stack;
        private int size;

        public FriendCirclesUF_0711(int N) {
            if(N <= 0) {
                return;
            }

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
            return findAnc(i1) == findAnc(i2);
        }

        private int findAnc(int i) {
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

            int anc1 = parentArr[i1];
            int anc2 = parentArr[i2];
            int size1 = sizeArr[i1];
            int size2 = sizeArr[i2];
            int longAnc = size1 >= size2 ? anc1 : anc2;
            int shortAnc = longAnc == anc1 ? anc2 : anc1;
            parentArr[shortAnc] = longAnc;
            sizeArr[longAnc] = size1 + size2;
            sizeArr[shortAnc] = 0;
            size--;
        }
    }
    
    public int findCircleNum(int[][] isConnected) {
        if(isConnected == null || isConnected.length == 0) {
            return 0;
        }
        
        int N = isConnected.length;
        FriendCirclesUF_0711 uf = new FriendCirclesUF_0711(N);
        for (int j = 1; j < N; j++) {
            for (int i = 0; i < N - 1; i++) {
                if(isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }

        return uf.size;
    }
}
