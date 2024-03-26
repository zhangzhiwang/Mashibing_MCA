package com.mashibing.dailyPractice.round1._91_to_100;

import java.util.*;

/**
 * 并查集题目3——岛问题变种，动态岛问题：
 * 给定两个正整数m和n，代表矩阵的长和宽，即横纵坐标的长度，矩阵里面的值初始化都是0，然后给定一个二维数组positions，里面的每一个元素都是一个节点的坐标，
 * 意思是将该坐标上的值改为1，每给出一个坐标就返回岛的个数，返回一个数组，里面是每次给出坐标后岛的个数。
 * 比如:m=10，n=10，先初始化一个有100个元素的二维数组，每一个元素值都为0，然后给出positions=[[5,7],[5,8],[6,7]]，意为将(5,7)这个坐标的值改为1，
 * 改为1后找出岛的个数c1，然后将坐标为(5,8)上的位置改为1，返回岛的个数c2，再将(6,7)上的位置改为1，返回岛的个数c3，最终返回[c1,c2,c3]。
 */
public class NumberOfIslandsDynamic2_0326 {
    class NumberOfIslandsDynamic2UnionFind_0326 {
        private Map<String, String> parentMap = new HashMap<>();
        private Map<String, Integer> sizeMap = new HashMap<>();

        public int union(int[] pos, int m, int n) {
            String key = pos[0] + "_" + pos[1];
            if(parentMap.containsKey(key)) {
                return sizeMap.size();
            }

            parentMap.put(key, key);
            sizeMap.put(key, 1);

            if(pos[0] - 1 >= 0) {
                union(key, (pos[0] - 1) + "_" + pos[1]);
            }
            if(pos[0] + 1 < m) {
                union(key, (pos[0] + 1) + "_" + pos[1]);
            }
            if(pos[1] - 1 >= 0) {
                union(key, pos[0] + "_" + (pos[1] - 1));
            }
            if(pos[1] + 1 < n) {
                union(key, pos[0] + "_" + (pos[1] + 1));
            }

            return sizeMap.size();
        }

        private String findRepr(String key) {
            if(parentMap.get(key) == null || "".equals(parentMap.get(key))) {
                throw new RuntimeException("参数有误！");
            }
            Stack<String> stack = new Stack<>();
            while(!parentMap.get(key).equals(key)) {
                stack.add(key);
                key = parentMap.get(key);
            }

            while(!stack.isEmpty()) {
                parentMap.put(stack.pop(), key);
            }
            return key;
        }

        private void union(String key1, String key2) {

        }
    }

    public static List<Integer> numberOfIslandsDynamic2(int m, int n, int[][] positions) {
        List<Integer> list = new ArrayList<>();
        if(m <= 0 || n <= 0 || positions == null || positions.length == 0) {
            return list;
        }

        for (int[] pos: positions) {

        }

        return list;
    }
}
