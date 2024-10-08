package com.mashibing.dailyPractice.round4;

import com.mashibing.dailyPractice.round2._71_to_100._0715.NumberOfIslandsDynamic2_0715;
import com.mashibing.unionFind.NumberOfIslandsDynamic1;
import com.mashibing.unionFind.NumberOfIslandsDynamic2;

import java.util.ArrayList;
import java.util.List;

/**
 * 并查集题目3——岛问题变种，动态岛问题：
 * 给定两个正整数m和n，代表矩阵的长和宽，即横纵坐标的长度，矩阵里面的值初始化都是0，然后给定一个二维数组positions，里面的每一个元素都是一个节点的坐标，
 * 意思是将该坐标上的值改为1，每给出一个坐标就算出岛的个数，返回一个数组，里面是每次给出坐标后岛的个数。
 * 比如:m=10，n=10，先初始化一个有100个元素的二维数组，每一个元素值都为0，然后给出positions=[[5,7],[5,8],[6,7]]，意为将(5,7)这个坐标的值改为1，
 * 改为1后找出岛的个数c1，然后将坐标为(5,8)上的位置改为1，返回岛的个数c2，再将(6,7)上的位置改为1，返回岛的个数c3，最终返回[c1,c2,c3]。
 */
public class NumberOfIslandsDynamic2_0804 {
    private int[] parentArr;
    private int[] sizeArr;
    private int[] stack;
    private int size;
    private int colSize;

    public NumberOfIslandsDynamic2_0804(int m, int n) {
        parentArr = new int[m * n];
        sizeArr = new int[m * n];
        stack = new int[m * n];
        colSize = n;
    }

    public void connect(int i, int j) {
        int index = findIndex(i, j);
        if(sizeArr[index] != 0) {
            return;
        }

        parentArr[index] = index;
        sizeArr[index] = 1;
        size++;
    }

    private int findIndex(int i, int j) {
        return i * colSize + j;
    }

    private int findAnc(int index) {
        int si = 0;
        while (parentArr[index] != index) {
            stack[si++] = index;
            index = parentArr[index];
        }

        for (int i = si - 1; i >= 0; i--) {
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
        size--;
    }

    public boolean contains(int i, int j) {
        int index = findIndex(i, j);
        return sizeArr[index] != 0;
    }

    public int size() {
        return size;
    }

    public ArrayList<Integer> numberOfIslandsDynamic2(int m, int n, int[][] positions) {
        ArrayList<Integer> list = new ArrayList<>();
        if(positions == null || positions.length == 0 || m <= 0 || n <= 0) {
            return list;
        }

        NumberOfIslandsDynamic2_0804 uf = new NumberOfIslandsDynamic2_0804(m, n);
        for (int[] position : positions) {
            int i = position[0];
            int j = position[1];
            if(uf.contains(i, j)) {
                list.add(uf.size());
                continue;
            }

            uf.connect(i, j);
            if(j + 1 < n && uf.contains(i, j + 1)) {
                uf.union(i, j, i, j + 1);
            }
            if(j - 1 >= 0 && uf.contains(i, j - 1)) {
                uf.union(i, j, i, j - 1);
            }
            if(i - 1 >= 0 && uf.contains(i - 1, j)) {
                uf.union(i, j, i - 1, j);
            }
            if(i + 1 < m && uf.contains(i + 1, j)) {
                uf.union(i, j, i + 1, j);
            }

            list.add(uf.size());
        }

        return list;
    }

    public static void main(String[] args) {
        int[][] positions = new int[][]{
                new int[]{0, 1}
                , new int[]{0, 2}
                , new int[]{0, 4}
                , new int[]{1, 1}
                , new int[]{1, 4}
                , new int[]{2, 1}
                , new int[]{2, 2}
                , new int[]{3, 0}
                , new int[]{4, 0}
                , new int[]{4, 1}
                , new int[]{4, 5}
        };
        List<Integer> list1 = new NumberOfIslandsDynamic2_0804(5, 6).numberOfIslandsDynamic2(5, 6, positions);
        System.out.println("my answer1 : " + list1);
        List<Integer> list2 = new NumberOfIslandsDynamic1().numberOfIslandsDynamic(5, 6, positions);
        System.out.println("my answer2 : " + list2);
        List<Integer> list3 = new NumberOfIslandsDynamic2().numberOfIslandsDynamic(5, 6, positions);
        System.out.println("my answer3 : " + list3);

        // leetcode题目连接：https://leetcode.com/problems/number-of-islands-ii/   付费

        /*
        0  0  0  0  0
        0  0  0  0  0
        0  0  0  0  0
        0  0  0  0  0
        1  0  0  0  0
        0  0  0  0  0
         */
    }
}
