package com.mashibing.dailyPractice.round2._71_to_100;

import com.mashibing.preInEclipse.junior.PrintIntBinary;

import java.util.Collection;

/**
 * 并查集题目2——岛问题：
 * 题目：给定一个二维数组arr，里面的值不是0就是1，上、下、左、右相邻的1认为是一片岛，返回arr中岛的数量。
 */
public class NumberOfIslands2_0522 {
    class NumberOfIslands2UF_0522 {
        private int[] parentArr;
        private int[] sizeArr;
        private int[] stack;
        private int size;
        private int rowSize;
        private int colSize;

        public NumberOfIslands2UF_0522(char[][] charArr) {
            if(charArr == null || charArr.length == 0) {
                return;
            }

            int rowSize = charArr.length;
            int colSize = charArr[0].length;
            parentArr = new int[rowSize * colSize];
            sizeArr = new int[rowSize * colSize];
            stack = new int[rowSize * colSize];
//            size = rowSize * colSize;
            this.rowSize = rowSize;
            this.colSize = colSize;

            for (int i = 0; i < rowSize; i++) {
                for (int j = 0; j < colSize; j++) {
                    if(charArr[i][j] == '1') {
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

        private boolean isSameSet(int i1, int j1, int i2, int j2) {
            return findAnc(i1, j1) == findAnc(i2, j2);
        }

        private int findAnc(int i, int j) {
            int stackIndex = 0;
            int index = index(i, j);
            while (parentArr[index] != index) {
                stack[stackIndex++] = index;
                index = parentArr[index];
            }

            for (int k = stackIndex - 1; k >= 0; k--) {
                parentArr[stack[k]] = index;
            }

            return index;
        }

        public void union(int i1, int j1, int i2, int j2) {
            if(i1 < 0 || i1 >= rowSize
                    || i2 < 0 || i2 >= rowSize
            || j1 < 0 || j1 >= colSize
            || j2 < 0 || j2 >= colSize) {
                return;
            }

            if(isSameSet(i1, j1, i2, j2)) {
                return;
            }

            int anc1 = findAnc(i1, j1);
            int anc2 = findAnc(i2, j2);
            int size1 = sizeArr[anc1];
            int size2 = sizeArr[anc2];
            int longAnc = size1 >= size2 ? anc1 : anc2;
            int shortAnc = longAnc == anc1 ? anc2 : anc1;
            parentArr[shortAnc] = longAnc;
            sizeArr[shortAnc] = 0;
            sizeArr[longAnc] = size1 + size2;
            size--;
        }
    }

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }

        int rowSize = grid.length;
        int colSize = grid[0].length;
        NumberOfIslands2UF_0522 uf = new NumberOfIslands2UF_0522(grid);
//        for (int i = 1; i < colSize; i++) {
//            if(grid[0][i] == grid[0][i - 1] && grid[0][i] == '1') {
//                uf.union(0,i, 0, i - 1);
//            }
//        }
        for (int i = 0; i < rowSize - 1; i++) {
            if (grid[i][0] == grid[i + 1][0] && grid[i][0] == '1') {
                uf.union(i, 0, i + 1, 0);
            }
        }

        for (int i = 0; i < colSize - 1; i++) {
            if(grid[0][i] == grid[0][i + 1] && grid[0][i] == '1') {
                uf.union(0,i, 0, i + 1);
            }
        }


        for (int i = 1; i < rowSize; i++) {
            for (int j = 1; j < colSize; j++) {
                if(grid[i][j] == grid[i - 1][j] && grid[i][j] == '1') {
                    uf.union(i, j, i - 1, j);
                }

                if(grid[i][j] == grid[i][j - 1] && grid[i][j] == '1') {
                    uf.union(i ,j , i, j - 1);
                }
            }
        }

        return uf.size;
    }

    public static void main(String[] args) {
        // leetcode题目地址：https://leetcode.cn/problems/number-of-islands/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china

        char[][] grid = {
                new char[] {'1','1','1','1','0'},
                new char[] {'1','1','0','1','0'},
                new char[] {'1','1','0','0','0'},
                new char[] {'0','0','0','0','0'}
        };

        System.out.println(new NumberOfIslands2_0522().numIslands(grid));
    }
}
