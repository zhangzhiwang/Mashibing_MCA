package com.mashibing.dailyPractice.round6;

/**
 * 并查集题目2——岛问题：
 * 题目：给定一个二维数组arr，里面的值不是0就是1，上、下、左、右相邻的1认为是一片岛，返回arr中岛的数量。
 */
public class NumberOfIslands2_1109 {
    class NumberOfIslands2UF_1109 {
        private int[] parentArr;
        private int[] sizeArr;
        private int[] stack;
        private int size;
        private int rowSize;
        private int colSize;

        public NumberOfIslands2UF_1109(char[][] grid) {
            if(grid == null || grid.length == 0) {
                return;
            }

            int m = grid.length;
            int n = grid[0].length;
            rowSize = m;
            colSize = n;
            parentArr = new int[m * n];
            sizeArr = new int[m * n];
            stack = new int[m * n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if(grid[i][j] == '1') {
                        int index = index(i, j);
                        parentArr[index] = index;
                        sizeArr[index] = 1;
                        size++;
                    }
                }
            }
        }

        private int index(int i, int j) {
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

        private void union(int i1, int j1, int i2, int j2) {
            if(i1 < 0 || i1 >= rowSize || i2 < 0 || i2 >= rowSize || j1 < 0 || j1 >= colSize || j2 < 0 || j2 >= colSize) {
                return;
            }

            int index1 = index(i1, j1);
            int index2 = index(i2, j2);
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
        NumberOfIslands2UF_1109 uf = new NumberOfIslands2UF_1109(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(j + 1 < n && grid[i][j] == grid[i][j + 1] && grid[i][j] == '1') {
                    uf.union(i, j, i, j + 1);
                }
                if(j - 1 >= 0 && grid[i][j] == grid[i][j - 1] && grid[i][j] == '1') {
                    uf.union(i, j, i, j - 1);
                }
                if(i - 1 >= 0 && grid[i][j] == grid[i - 1][j] && grid[i][j] == '1') {
                    uf.union(i, j, i - 1, j);
                }
                if(i + 1 < m && grid[i][j] == grid[i + 1][j] && grid[i][j] == '1') {
                    uf.union(i, j, i + 1, j);
                }
            }
        }

        return uf.size();
    }

    public static void main(String[] args) {
        // leetcode题目地址：https://leetcode.cn/problems/number-of-islands/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
    }
}
