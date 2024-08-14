package com.mashibing.unionFind;

import java.util.ArrayList;
import java.util.List;

/**
 * 岛问题的第二种实现方式
 * 思路：
 * 主逻辑的思路和com.mashibing.unionFind.NumberOfIslands1一样，只不过使用的并查集换了，换成了用数组实现的并查集，因为经典并查集是使用Map做的，
 * Map的常数操作是个大常数，数组的操作也是常数操作，但是是小常数。所以本类的实现的执行速度比com.mashibing.unionFind.NumberOfIslands1快。
 *
 * 课程：体系班课时126
 */
public class NumberOfIslands2 {
    public static int numberOfIslands(char[][] grid) {// 为了迎合leetcode的测试，将入参改为(char[][] grid)
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rowCount = grid.length;
        int colCount = grid[0].length;

        UnionFind3 unionFind = new UnionFind3(grid);
        for (int i = 0; i < rowCount; i++) {
            if (i != rowCount - 1 && grid[i][0] == '1' && grid[i + 1][0] == '1') {// 注意：原题目给的是char类型，不是int类型，所以要跟'1'而不是1比较
                unionFind.union(i, 0, i + 1, 0);
            }
        }

        for (int j = 0; j < colCount; j++) {
            if (j != colCount - 1 && grid[0][j] == '1' && grid[0][j + 1] == '1') {
                unionFind.union(0, j, 0, j + 1);
            }
        }

        for (int i = 1; i < rowCount; i++) {
            for (int j = 1; j < colCount; j++) {
                if (grid[i][j] == '1' && grid[i - 1][j] == '1') {
                    unionFind.union(i, j, i - 1, j);
                }
                if (grid[i][j] == '1' && grid[i][j - 1] == '1') {
                    unionFind.union(i, j, i, j - 1);
                }
            }
        }

        return unionFind.size();
    }

    static class UnionFind3 {
        private int[] parentArr;
        private int[] sizeArr;
        private int[] stack;
        private int size;
        private int colCount;// 辅助变量，并非并查集本身需要，岛问题这道题需要

        public UnionFind3(char[][] arr) {
            int rowCount = arr.length;
            int colCount = arr[0].length;
            int totalCount = rowCount * colCount;
            this.colCount = colCount;
            parentArr = new int[totalCount];
            sizeArr = new int[totalCount];
            stack = new int[totalCount];
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    if (arr[i][j] == '1') {
                        int index = findIndex(i, j);
                        parentArr[index] = index;
                        sizeArr[index] = 1;
                        /*
                         初始化的时候二维矩阵里面有多少个1并查集的size就是多少，可不是初始化的时候size为rowCount * colCount。
                         要始终记住size是并查集的个数，而初始并查集的个数就是样本数据的个数，这个size一定要注意
                         */
                        size++;
                    }
                }
            }
        }

        /**
         * 把二维数组拉平成一维数组，二维数组的坐标[i][j]对应一维数组的坐标，换算方法是：i * 列数 + j
         * @param i 二维数组的行坐标
         * @param j 二维数组的列坐标
         * @return
         */
        private int findIndex(int i, int j) {
            return i * colCount + j;
        }

        /**
         * 查找代表节点
         *
         * @param index 原始数组arr[i][j]中通过findIndex方法换算出的index值
         * @return 该样本值的代表节点
         */
        public int findAncestor(int index) {
            int stackIndex = 0;
            while (parentArr[index] != index) {
                stack[stackIndex++] = index;
                index = parentArr[index];
            }

            for (int i = stackIndex - 1; i >= 0; i--) {
                parentArr[stack[i]] = index;// 循环里用i代替stackIndex，i是变化的，所以不能是stack[stackIndex]而是stack[i]
            }

            return index;
        }

        /**
         * 在原始arr数组中(i1,j1)位置和(i2,j2)位置合并
         * @param i1
         * @param j1
         * @param i2
         * @param j2
         */
        public void union(int i1, int j1, int i2, int j2) {
            int index1 = findIndex(i1, j1);
            int index2 = findIndex(i2, j2);
            int ancestor1 = findAncestor(index1);// 找一个节点的代表节点不是在parentArr里面找，parentArr里面只能找到它的直接父节点，直接父节点不一定是它的祖先节点
            int ancestor2 = findAncestor(index2);
            if (ancestor1 == ancestor2) {
                return;
            }

            int size1 = sizeArr[ancestor1];
            int size2 = sizeArr[ancestor2];
            int bigger = size1 >= size2 ? ancestor1 : ancestor2;
            int smaller = bigger == ancestor1 ? ancestor2 : ancestor1;
            parentArr[smaller] = bigger;
            sizeArr[bigger] += sizeArr[smaller];
            sizeArr[smaller] = 0;
            size--;
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        char[][] arr = new char[][]{
                new char[]{'1', '1', '1', '1', '0'},
                new char[]{'1', '1', '0', '1', '0'},
                new char[]{'1', '1', '0', '0', '0'},
                new char[]{'0', '0', '0', '0', '0'},
        };

        int numberOfIslands = numberOfIslands(arr);
        System.out.println("my answer : " + numberOfIslands);
    }
}
