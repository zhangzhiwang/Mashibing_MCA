package com.mashibing.dailyPractice.round2._71_to_100._0715;

import javax.xml.namespace.QName;

/**
 * 并查集题目2——岛问题：
 * 题目：给定一个二维数组arr，里面的值不是0就是1，上、下、左、右相邻的1认为是一片岛，返回arr中岛的数量。
 */
public class NumberOfIslands2_0715 {
    static class NumberOfIslands2UF_0715 {
        private int[] parentArr;
        private int[] sizeArr;
        private int[] stack;
        private int size;
        private int colSize;

        public NumberOfIslands2UF_0715(char[][] grid) {
            int N = grid.length;
            int M = grid[0].length;
            colSize = M;
            parentArr = new int[M * N];
            sizeArr = new int[M * N];
            stack = new int[M * N];
//            size = M * N;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(grid[i][j] == '1') {
                        int index = findIndex(i, j);
                        parentArr[index] = index;
                        sizeArr[index] = 1;
                        size++;
                    }
                }
            }
        }

        private boolean isSameSet(int i1, int j1, int i2, int j2) {
            return findAnc(findIndex(i1, j1)) == findAnc(findIndex(i2, j2));
        }

        private int findAnc(int i) {// i是拉平后的index
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

        public void union(int i1, int j1, int i2, int j2) {
            if (isSameSet(i1, j1, i2, j2)) {
                return;
            }

            int anc1 = findAnc(findIndex(i1, j1));
            int anc2 = findAnc(findIndex(i2, j2));
            int size1 = sizeArr[anc1];
            int size2 = sizeArr[anc2];
            int longAnc = size1 >= size2 ? anc1 : anc2;
            int shortAnc = longAnc == anc1 ? anc2 : anc1;
            parentArr[shortAnc] = longAnc;
            sizeArr[longAnc] = size1 + size2;
            sizeArr[shortAnc] = 0;
            size--;
        }

        private int findIndex(int i, int j) {
            return i * colSize + j;
        }
    }

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }

        int N = grid.length;
        int M = grid[0].length;
        NumberOfIslands2UF_0715 uf = new NumberOfIslands2UF_0715(grid);
        for (int i = 1; i < M; i++) {// 列
            if(grid[0][i] == grid[0][i - 1] && grid[0][i] == '1') {
                uf.union(0, i, 0, i - 1);
            }
        }
        for (int i = 1; i < N; i++) {// 行
            if(grid[i][0] == grid[i - 1][0] && grid[i][0] == '1') {
                uf.union(i, 0, i - 1, 0);
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if(grid[i][j] == grid[i - 1][j] && grid[i][j] == '1') {
                    uf.union(i, j, i - 1, j);
                }
                if(grid[i][j] == grid[i][j - 1] && grid[i][j] == '1') {
                    uf.union(i, j, i, j - 1);
                }
            }
        }

        return uf.size;
    }

    public static void main(String[] args) {
        // leetcode题目地址：https://leetcode.cn/problems/number-of-islands/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
    }
}
