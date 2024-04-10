package com.mashibing.dailyPractice.round1._91_to_100;

/**
 * 给定一个二维数组arr，里面的值不是0就是1，上、下、左、右相邻的1认为是一片岛，返回arr中岛的数量（使用数组实现并查集）
 */
public class NumberOfIslands2_0326 {
    class NumberOfIslands2UnionFind_0326 {
        private int[] parentArr;
        private int[] setSizeArr;
        private int[] stack;
        private int size;
        private int colLen;

        public NumberOfIslands2UnionFind_0326(char[][] grid) {
            if(grid == null || grid.length == 0) {
                return;
            }

            int rowLen = grid.length;
            int colLen = grid[0].length;
            int totalLen = rowLen * colLen;
            parentArr = new int[totalLen];
            setSizeArr = new int[totalLen];
            stack = new int[totalLen];
            this.colLen = colLen;

            for (int i = 0; i < rowLen; i++) {
                for (int j = 0; j < colLen; j++) {
                    if(grid[i][j] == '1') {
                        int k = index(i, j);
                        parentArr[k] = k;
                        setSizeArr[k] = 1;
                        size++;
                    }
                }
            }
        }

        public boolean isSameSet(int i1, int j1, int i2, int j2) {
            return findRepr(index(i1, j1)) == findRepr(index(i2, j2));
        }

        public void union(int i1, int j1, int i2, int j2) {
            if(isSameSet(i1, j1, i2, j2)) {
                return;
            }

            int r1 = findRepr(index(i1, j1));
            int r2 = findRepr(index(i2, j2));
            int longR = setSizeArr[r1] >= setSizeArr[r2] ? r1 : r2;
            int shortR = longR == r1 ? r2 : r1;
            parentArr[shortR] = longR;
            setSizeArr[longR] += setSizeArr[shortR];
            setSizeArr[shortR] = 0;
            size--;
        }

        public int size() {
            return size;
        }

        private int findRepr(int i) {
            int stackIndex = 0;
            while (parentArr[i] != i) {
                stack[stackIndex++] = i;
                i = parentArr[i];
            }

            for (int j = stackIndex - 1; j >= 0; j--) {
                stack[j] = i;
            }
            return i;
        }

        private int index(int i, int j) {
            return i * colLen + j;
        }
    }

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }

        int rowLen = grid.length;
        int colLen = grid[0].length;
        NumberOfIslands2UnionFind_0326 uf = new NumberOfIslands2UnionFind_0326(grid);
        for (int i = rowLen - 1; i > 0; i--) {
            if(grid[i - 1][0] == grid[i][0] && grid[i][0] == '1') {
                uf.union(i - 1, 0, i, 0);
            }
        }
        for (int i = colLen - 1; i > 0; i--) {
            if(grid[0][i - 1] == grid[0][i] && grid[0][i] == '1') {
                uf.union(0, i - 1, 0, i);
            }
        }

        for (int i = 1; i < rowLen; i++) {
            for (int j = 1; j < colLen; j++) {
                if(grid[i - 1][j] == grid[i][j] && grid[i][j] == '1') {
                    uf.union(i - 1, j, i, j);
                }
                if(grid[i][j - 1] == grid[i][j] && grid[i][j] == '1') {
                    uf.union(i, j - 1, i, j);
                }
            }
        }

        return uf.size();
    }

    public static void main(String[] args) {
        char[][] arr = new char[][]{
                new char[]{'0', '1', '1', '0', '1', '0'},
                new char[]{'0', '1', '0', '0', '1', '0'},
                new char[]{'0', '1', '1', '0', '0', '0'},
                new char[]{'1', '0', '0', '0', '0', '0'},
                new char[]{'1', '1', '0', '0', '0', '1'},
        };

        int numberOfIslands = new NumberOfIslands2_0326().numIslands(arr);
        System.out.println("my answer : " + numberOfIslands);

        // leetcode：https://leetcode.cn/problems/number-of-islands/submissions/516915142/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
    }
}
