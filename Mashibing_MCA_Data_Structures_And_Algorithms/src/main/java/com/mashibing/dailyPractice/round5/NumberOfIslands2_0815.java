package com.mashibing.dailyPractice.round5;

/**
 * 并查集题目2——岛问题：
 * 题目：给定一个二维数组arr，里面的值不是0就是1，上、下、左、右相邻的1认为是一片岛，返回arr中岛的数量。
 */
public class NumberOfIslands2_0815 {
    class NumberOfIslands2Info_0815 {
        private int[] parentArr;
        private int[] sizeArr;
        private int[] stack;
        private int size;
        private int colSize;

        public NumberOfIslands2Info_0815(char[][] grid) {
            if(grid == null || grid.length == 0) {
                return;
            }

            int m = grid.length;
            int n = grid[0].length;
            parentArr = new int[m * n];
            sizeArr = new int[m * n];
            stack = new int[m * n];
            colSize = n;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if(grid[i][j] == '1') {
                        int index = findIndex(i, j);
                        parentArr[index] = index;
                        sizeArr[index] = 1;
                        size++;
                    }
                }
            }
        }

        private int findIndex(int i, int j) {
            return i * colSize + j;
        }

        private int findAnc(int index) {
            int stackI = 0;
            while (parentArr[index] != index) {
                stack[stackI++] = index;
                index = parentArr[index];
            }

            for (int i = stackI - 1; i >= 0; i--) {
                parentArr[stack[i]] = index;
            }

            return index;
        }

        private boolean isSameSet(int index1, int index2) {
            return findAnc(index1) == findAnc(index2);
        }

        public void union(int i1, int j1, int i2, int j2) {
            int index1 = findIndex(i1, j1);
            int index2 = findIndex(i2, j2);
            if(isSameSet(index1, index2)) {
                return;
            }

            int anc1 = findAnc(index1);
            int anc2 = findAnc(index2);
            int size1 = sizeArr[anc1];
            int size2 = sizeArr[anc2];
            int longAnc = size1 >= size2 ? anc1 : anc2;
            int shortAnc = longAnc == anc1 ? anc2 : anc1;
            parentArr[shortAnc] = longAnc;
            sizeArr[longAnc] = size1 + size2;
            sizeArr[shortAnc] = 0;
            size--;
        }

        public int size() {
            return size;
        }
    }

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        NumberOfIslands2Info_0815 uf = new NumberOfIslands2Info_0815(grid);
        for (int i = 1; i < n; i++) {
            if(grid[0][i] == grid[0][i - 1] && grid[0][i] == '1') {
                uf.union(0, i, 0, i - 1);
            }
        }

        for (int i = 1; i < m; i++) {
            if(grid[i][0] == grid[i - 1][0] && grid[i][0] == '1') {
                uf.union(i, 0, i - 1, 0);
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(grid[i][j] == grid[i - 1][j] && grid[i][j] == '1') {
                    uf.union(i, j, i - 1, j);
                }
                if(grid[i][j] == grid[i][j - 1] && grid[i][j] == '1') {
                    uf.union(i, j, i, j - 1);
                }
            }
        }

        return uf.size();
    }

    public static void main(String[] args) {
        // leetcode题目地址：https://leetcode.cn/problems/number-of-islands/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
    }
}
