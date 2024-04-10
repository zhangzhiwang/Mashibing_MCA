package com.mashibing.dailyPractice.round2._71_to_100;

import com.mashibing.dailyPractice.round1._91_to_100.NumberOfIslandsDynamic2_0326;
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
public class NumberOfIslandsDynamic2_0409 {
    private Map<String, String> parentMap = new HashMap<>();
    private Map<String, Integer> sizeMap = new HashMap<>();
    private int size;
    private int rowLen;
    private int colLen;

    private void connect(int i, int j) {
        String key = i + "_" + j;
        parentMap.put(key, key);
        sizeMap.put(key, 1);
        size++;
    }

    private boolean isSameSet(int i1, int j1, int i2, int j2) {
        return findAnc(i1 + "_" + j1) == findAnc(i2 + "_" + j2);
    }

    private String findAnc(String s) {
        Stack<String> stack = new Stack<>();
        while (!parentMap.get(s).equals(s)) {
            stack.add(s);
            s = parentMap.get(s);
        }

        while (!stack.isEmpty()) {
            parentMap.put(stack.pop(), s);
        }

        return s;
    }

    private void union(int i1, int j1, int i2, int j2) {
        if(i1 < 0 || j1 < 0 || i1 >= rowLen || j1 >= colLen
        ||i2 < 0 || j2 < 0 || i2 >= rowLen || j2 >= colLen) {
            return;
        }

        if(sizeMap.get(i2 + "_" + j2) == null) {
            return;
        }

        if(isSameSet(i1, j1, i2, j2)) {
            return;
        }

        String a1 = findAnc(i1 + "_" + j1);
        String a2 = findAnc(i2 + "_" + j2);
        int size1 = sizeMap.get(a1);
        int size2 = sizeMap.get(a2);
        String longA = size1 >= size2 ? a1 : a2;
        String shortA = longA == a1 ? a2 : a1;
        parentMap.put(shortA, longA);
        sizeMap.put(longA, size1 + size2);
        size--;
    }

    public List<Integer> numberOfIslandsDynamic2(int m, int n, int[][] positions) {
        List<Integer> list = new ArrayList<>();
        if(positions == null || positions.length == 0) {
            return list;
        }

        rowLen = m;
        colLen = n;
        NumberOfIslandsDynamic2_0409 uf = new NumberOfIslandsDynamic2_0409();
        for (int[] position : positions) {
            String key = position[0] + "_" + position[1];
            if (sizeMap.containsKey(key)) {
                int count = sizeMap.get(findAnc(key));
                list.add(count);
                continue;
            }

            connect(position[0],position[1]);
            union(position[0],position[1], position[0], position[1] + 1);
            union(position[0],position[1], position[0], position[1] - 1);
            union(position[0],position[1], position[0] - 1, position[1]);
            union(position[0],position[1], position[0] + 1, position[1]);
            list.add(size);
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
        List<Integer> list1 = new NumberOfIslandsDynamic2_0409().numberOfIslandsDynamic2(5, 6, positions);
        System.out.println("my answer1 : " + list1);
        List<Integer> list2 = new NumberOfIslandsDynamic1().numberOfIslandsDynamic(5, 6, positions);
        System.out.println("my answer2 : " + list2);
        List<Integer> list3 = new NumberOfIslandsDynamic2().numberOfIslandsDynamic(5, 6, positions);
        System.out.println("my answer3 : " + list3);

        // leetcode题目连接：https://leetcode.com/problems/number-of-islands-ii/   付费
    }
}
