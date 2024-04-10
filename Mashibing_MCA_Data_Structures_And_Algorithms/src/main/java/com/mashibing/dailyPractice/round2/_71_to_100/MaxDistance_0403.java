package com.mashibing.dailyPractice.round2._71_to_100;

import com.mashibing.others.DuiShuQiUtil;
import com.mashibing.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一颗二叉树head，任意两个节点之间都会有距离，每经过一个节点距离加1，
 * 最短的距离是节点自己和自己的距离，距离是1，每个节点和其相邻节点的距离是2，求该树的最大距离
 */
public class MaxDistance_0403 {
    static class MaxDistanceInfo_0403 {
        private int maxDis;
        private int height;

        public MaxDistanceInfo_0403(int maxDis, int height) {
            this.maxDis = maxDis;
            this.height = height;
        }
    }

    public static int maxDistance(TreeNode head) {
        if(head == null) {
            return 0;
        }

        return recurse(head).maxDis;
    }

    private static MaxDistanceInfo_0403 recurse(TreeNode head) {
        if(head == null) {
            return new MaxDistanceInfo_0403(0, 0);
        }

        MaxDistanceInfo_0403 leftInfo = recurse(head.left);
        MaxDistanceInfo_0403 rightInfo = recurse(head.right);

        int maxDis = Math.max(Math.max(leftInfo.maxDis, rightInfo.maxDis), leftInfo.height + rightInfo.height + 1);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new MaxDistanceInfo_0403(maxDis, height);
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
        int maxLevel = 3;
        int maxValue = 10;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            int rightAns = maxDistance1(head);
            int yourAns = maxDistance(head);
            if (rightAns != yourAns) {
                System.out.println("Oops!");
                DuiShuQiUtil.printBinaryTree(head);
                System.out.println("rightAns = " + rightAns);
                System.out.println("yourAns = " + yourAns);
                break;
            }
        }
        System.out.println("finish!");
    }
}
