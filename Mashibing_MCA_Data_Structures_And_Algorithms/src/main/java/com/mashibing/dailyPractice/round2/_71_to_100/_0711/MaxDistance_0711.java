package com.mashibing.dailyPractice.round2._71_to_100._0711;

import com.mashibing.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 二叉树最大距离
 * 给定一颗二叉树head，任意两个节点之间都会有距离，每经过一个节点距离加1，最短的距离是节点自己和自己的距离，距离是1，每个节点和其相邻节点的距离是2，求该树的最大距离
 */
public class MaxDistance_0711 {
    static class MaxDistanceInfo_0711 {
        private int height;
        private int maxDis;

        public MaxDistanceInfo_0711(int height, int maxDis) {
            this.height = height;
            this.maxDis = maxDis;
        }
    }

    public static int maxDistance(TreeNode root) {
        if(root == null) {
            return 0;
        }

        return recurse(root).maxDis;
    }

    private static MaxDistanceInfo_0711 recurse(TreeNode x) {
        if(x == null) {
            return new MaxDistanceInfo_0711(0, 0);
        }

        MaxDistanceInfo_0711 left = recurse(x.left);
        MaxDistanceInfo_0711 right = recurse(x.right);

        int p1 = Math.max(left.maxDis, right.maxDis);
        int p2 = left.height + right.height + 1;

        int height = Math.max(left.height, right.height) + 1;
        int maxDis = Math.max(p1, p2);
        return new MaxDistanceInfo_0711(height, maxDis);
    }

    // 对数器
    // for test
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static int maxDistance1(TreeNode head) {
        if (head == null) {
            return 0;
        }
        ArrayList<TreeNode> arr = getPrelist(head);
        HashMap<TreeNode, TreeNode> parentMap = getParentMap(head);
        int max = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i; j < arr.size(); j++) {
                max = Math.max(max, distance(parentMap, arr.get(i), arr.get(j)));
            }
        }
        return max;
    }

    public static HashMap<TreeNode, TreeNode> getParentMap(TreeNode head) {
        HashMap<TreeNode, TreeNode> map = new HashMap<>();
        map.put(head, null);
        fillParentMap(head, map);
        return map;
    }

    public static void fillParentMap(TreeNode head, HashMap<TreeNode, TreeNode> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }

    public static ArrayList<TreeNode> getPrelist(TreeNode head) {
        ArrayList<TreeNode> arr = new ArrayList<>();
        fillPrelist(head, arr);
        return arr;
    }

    public static void fillPrelist(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static int distance(HashMap<TreeNode, TreeNode> parentMap, TreeNode o1, TreeNode o2) {
        HashSet<TreeNode> o1Set = new HashSet<>();
        TreeNode cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        TreeNode lowestAncestor = cur;
        cur = o1;
        int distance1 = 1;
        while (cur != lowestAncestor) {
            cur = parentMap.get(cur);
            distance1++;
        }
        cur = o2;
        int distance2 = 1;
        while (cur != lowestAncestor) {
            cur = parentMap.get(cur);
            distance2++;
        }
        return distance1 + distance2 - 1;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode<Integer> head = generateRandomBST(maxLevel, maxValue);
            if (maxDistance1(head) != maxDistance(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
