package com.mashibing.dailyPractice.round3._No71_100;

import com.mashibing.unionFind.NumberOfIslandsDynamic1;
import com.mashibing.unionFind.NumberOfIslandsDynamic2;

import java.util.*;

/**
 * 并查集题目3——岛问题变种，动态岛问题：
 * 给定两个正整数m和n，代表矩阵的长和宽，即横纵坐标的长度，矩阵里面的值初始化都是0，然后给定一个二维数组positions，里面的每一个元素都是一个节点的坐标，
 * 意思是将该坐标上的值改为1，每给出一个坐标就算出岛的个数，返回一个数组，里面是每次给出坐标后岛的个数。
 * 比如:m=10，n=10，先初始化一个有100个元素的二维数组，每一个元素值都为0，然后给出positions=[[5,7],[5,8],[6,7]]，意为将(5,7)这个坐标的值改为1，
 * 改为1后找出岛的个数c1，然后将坐标为(5,8)上的位置改为1，返回岛的个数c2，再将(6,7)上的位置改为1，返回岛的个数c3，最终返回[c1,c2,c3]。
 */
public class NumberOfIslandsDynamic2_0716 {
    static class NumberOfIslandsDynamic2UF_0716 {
        private Map<String, String> parentMap = new HashMap<>();
        private Map<String, Integer> sizeMap = new HashMap<>();

        public void connect(int i, int j) {
            String key = i + "_" + j;
            if(parentMap.containsKey(key)) {
                return;
            }

            parentMap.put(key, key);
            sizeMap.put(key, 1);
        }

        private String findAnc(String s) {
            Stack<String> stack = new Stack<>();
            while(!parentMap.get(s).equals(s)) {
                stack.add(s);
                s = parentMap.get(s);
            }

            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), s);
            }

            return s;
        }

        private boolean isSameSet(int i1, int j1, int i2, int j2) {
            return findAnc(i1 + "_" + j1) == findAnc(i2 + "_" + j2);
        }

        private void union(int i1, int j1, int i2, int j2, int m, int n) {
            if(i1 < 0 || i1 >= m || j1 < 0 || j1 >= n || i2 < 0 || i2 >= m || j2 < 0 || j2 >= n) {
                return;
            }

            String key1 = i1 + "_" + j1;
            String key2 = i2 + "_" + j2;
            String anc1 = parentMap.get(key1);
            String anc2 = parentMap.get(key2);
            if(anc1 == null || anc2 == null) {
                return;
            }

            if(isSameSet(i1, j1, i2, j2)) {
                return;
            }

            int size1 = sizeMap.get(anc1);
            int size2 = sizeMap.get(anc2);
            String longAnc = size1 >= size2 ? anc1 : anc2;
            String shortAnc = longAnc == anc1 ? anc2 : anc1;
            parentMap.put(shortAnc, longAnc);
            sizeMap.put(longAnc, size1 + size2);
            sizeMap.remove(shortAnc);
        }

        public int size() {
            return sizeMap.size();
        }
    }

    public static ArrayList<Integer> numberOfIslandsDynamic2(int m, int n, int[][] positions) {
        ArrayList<Integer> list = new ArrayList<>();
        if(m <= 0 || n <= 0 || positions == null || positions.length == 0) {
            return list;
        }

        NumberOfIslandsDynamic2UF_0716 uf = new NumberOfIslandsDynamic2UF_0716();
        for (int[] position : positions) {
            if(uf.parentMap.containsKey(position[0] + "_" + position[1])) {
                list.add(uf.size());
                continue;
            }

            uf.connect(position[0], position[1]);
            uf.union(position[0], position[1], position[0], position[1] + 1, m, n);
            uf.union(position[0], position[1], position[0], position[1] - 1, m, n);
            uf.union(position[0], position[1], position[0] - 1, position[1], m, n);
            uf.union(position[0], position[1], position[0] + 1, position[1], m, n);
            list.add(uf.size());
        }

        return list;
    }

    public static void main(String[] args) {
        int[][] positions = new int[][]{
                new int[]{0, 1}
                , new int[]{0, 2}
                ,new int[] {0,4}
                ,new int[] {1,1}
                ,new int[] {1,4}
                ,new int[] {2,1}
                ,new int[] {2,2}
                ,new int[] {3,0}
                ,new int[] {4,0}
                ,new int[] {4,1}
                ,new int[] {4,5}
        };
        List<Integer> list1 = new NumberOfIslandsDynamic2_0716().numberOfIslandsDynamic2(5, 6, positions);
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
