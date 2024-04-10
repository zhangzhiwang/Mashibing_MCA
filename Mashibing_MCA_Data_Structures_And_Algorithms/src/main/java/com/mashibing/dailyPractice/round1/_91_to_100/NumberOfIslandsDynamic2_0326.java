package com.mashibing.dailyPractice.round1._91_to_100;

import com.mashibing.unionFind.NumberOfIslandsDynamic1;
import com.mashibing.unionFind.NumberOfIslandsDynamic2;

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

        public int connect(int[] pos, int m, int n) {
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
            if(!parentMap.containsKey(key1) || !parentMap.containsKey(key2)) {
                return;
            }

            String r1 = findRepr(key1);
            String r2 = findRepr(key2);
            if(r1.equals(r2)) {
                return;
            }

            int size1 = sizeMap.get(r1);
            int size2 = sizeMap.get(r2);
            String longR = size1 >= size2 ? r1 : r2;
            String shortR = longR == r1 ? r2 : r1;
            parentMap.put(shortR, longR);
            sizeMap.put(longR, size1 + size2);
            sizeMap.remove(shortR);
        }
    }

    public List<Integer> numberOfIslandsDynamic2(int m, int n, int[][] positions) {
        List<Integer> list = new ArrayList<>();
        if(m <= 0 || n <= 0 || positions == null || positions.length == 0) {
            return list;
        }

        NumberOfIslandsDynamic2UnionFind_0326 uf = new NumberOfIslandsDynamic2UnionFind_0326();
        for (int[] pos: positions) {
            list.add(uf.connect(pos, m, n));
        }

        return list;
    }

    public static void main(String[] args) {
        int[][] positions = new int[][] {
                new int[] {0,1},
                new int[] {0,2},
                new int[] {0,4},
                new int[] {1,1},
                new int[] {1,4},
                new int[] {2,1},
                new int[] {2,2},
                new int[] {3,0},
                new int[] {4,0},
                new int[] {4,1},
                new int[] {4,5}
        };
        List<Integer> list1 = new NumberOfIslandsDynamic2_0326().numberOfIslandsDynamic2(5, 6, positions);
        System.out.println("my answer1 : " + list1);
        List<Integer> list2 = new NumberOfIslandsDynamic1().numberOfIslandsDynamic(5, 6, positions);
        System.out.println("my answer2 : " + list2);
        List<Integer> list3 = new NumberOfIslandsDynamic2().numberOfIslandsDynamic(5, 6, positions);
        System.out.println("my answer3 : " + list3);

        // leetcode题目连接：https://leetcode.com/problems/number-of-islands-ii/   付费
    }
}
