package com.mashibing.dailyPractice.round2._71_to_100;

/**
 * 并查集题目2——岛问题：
 * 题目：给定一个二维数组arr，里面的值不是0就是1，上、下、左、右相邻的1认为是一片岛，返回arr中岛的数量。
 */
public class NumberOfIslands2_0409 {
    private int[] parentArr;
    private int[] sizeArr;
    private int[] stack;
    private int size;
    private int row;
    private int col;

    public NumberOfIslands2_0409(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return;
        }

        row = grid.length;
        col = grid[0].length;
        parentArr = new int[row * col];
        sizeArr = new int[row * col];
        stack = new int[row * col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(grid[i][j] == '1') {
                    int index = index(i, j);
                    parentArr[index] = index;
                    sizeArr[index] = 1;
                    size++;
                }
            }
        }
    }

    private boolean isSameSet(int i1, int j1, int i2, int j2) {
        return findAnc(i1, j1) == findAnc(i2, j2);
    }

    private int findAnc(int i, int j) {
        int index = index(i, j);
        int stackIndex = 0;
        while (parentArr[index] != index) {
            stack[stackIndex++] = index;
            index = parentArr[index];
        }

        for (int k = stackIndex - 1; k >= 0; k--) {
            parentArr[stack[k]] = index;
        }

        return index;
    }

    private void union(int i1, int j1, int i2, int j2) {
        if(isSameSet(i1, j1, i2, j2)) {
            return;
        }

        int a1 = findAnc(i1, j1);
        int a2 = findAnc(i2, j2);
        int size1 = sizeArr[a1];
        int size2 = sizeArr[a2];
        int longA = size1 >= size2 ? a1 : a2;
        int shortA = longA == a1 ? a2 : a1;
        parentArr[shortA] = longA;
        sizeArr[longA] = size1 + size2;
        sizeArr[shortA] = 0;
        size--;
    }

    private int index(int i, int j) {
        return i * col + j;
    }

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }

        NumberOfIslands2_0409 uf = new NumberOfIslands2_0409(grid);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(i - 1 >= 0 && grid[i - 1][j] == '1' && grid[i][j] == '1') {
                    uf.union(i, j, i - 1, j);
                }
                if(j + 1 < col && grid[i][j + 1] == '1' && grid[i][j] == '1') {
                    uf.union(i, j, i, j + 1);
                }
            }
        }

        return uf.size;
    }

    public static void main(String[] args) {
        char[][] arr = new char[][]{
                new char[]{'0', '1', '1', '0', '1', '0'},
                new char[]{'0', '1', '0', '0', '1', '0'},
                new char[]{'0', '1', '1', '0', '0', '0'},
                new char[]{'1', '0', '0', '0', '0', '0'},
                new char[]{'1', '1', '0', '0', '0', '1'},
        };

        int numberOfIslands = new NumberOfIslands2_0409(arr).numIslands(arr);
        System.out.println("my answer : " + numberOfIslands);

        // leetcode：https://leetcode.cn/problems/number-of-islands/submissions/516915142/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
    }
}
