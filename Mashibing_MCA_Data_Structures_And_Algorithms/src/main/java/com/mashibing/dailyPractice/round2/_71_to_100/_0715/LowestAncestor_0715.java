package com.mashibing.dailyPractice.round2._71_to_100._0715;

import com.mashibing.others.DuiShuQiUtil;
import com.mashibing.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一颗二叉树head和任意两个节点a和b，返回a和b的最低公共祖先
 */
public class LowestAncestor_0715 {
    static class LowestAncestorInfo_0715 {
        private TreeNode anc;
        private boolean findA;
        private boolean findB;

        public LowestAncestorInfo_0715(TreeNode anc, boolean findA, boolean findB) {
            this.anc = anc;
            this.findA = findA;
            this.findB = findB;
        }
    }

    public static TreeNode lowestAncestor(TreeNode head, TreeNode a, TreeNode b) {
        if(head == null || a == null || b == null) {
            return null;
        }

        return recurse(head, a, b).anc;
    }

    private static LowestAncestorInfo_0715 recurse(TreeNode x, TreeNode a, TreeNode b) {
        if(x == null) {
            return new LowestAncestorInfo_0715(null, false, false);
        }

        LowestAncestorInfo_0715 left = recurse(x.left, a, b);
        LowestAncestorInfo_0715 right = recurse(x.right, a, b);

        TreeNode anc = null;
        if(left.findA && left.findB) {
            anc = left.anc;
        } else if (right.findA && right.findB) {
            anc = right.anc;
        } else if ((left.findA && right.findB) || (right.findA && left.findB)
        || (x == a && (left.findB || right.findB)) || (x == b && (left.findA || right.findA))
        || a == b && x == a) {
            anc = x;
        }

        boolean findA = x == a || left.findA || right.findA;
        boolean findB = x == b || left.findB || right.findB;
        return new LowestAncestorInfo_0715(anc, findA, findB);
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

    // for test
    public static TreeNode pickRandomOne(TreeNode head) {
        if (head == null) {
            return null;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static TreeNode lowestAncestor1(TreeNode head, TreeNode o1, TreeNode o2) {
        if (head == null) {
            return null;
        }
        // key的父节点是value
        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
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
        return cur;
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

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            TreeNode o1 = pickRandomOne(head);
            TreeNode o2 = pickRandomOne(head);
            TreeNode ans1 = lowestAncestor1(head, o1, o2);
            TreeNode ans2 = lowestAncestor(head, o1, o2);
            if (ans1 != ans2) {
                DuiShuQiUtil.printBinaryTree(head);
                System.out.println();
                System.out.println("head = " + head);
                System.out.println("o1 = " + o1);
                System.out.println("o2 = " + o2);
                System.out.println("correct ans = " + ans1.value);
                System.out.println("my ans = " + ans2.value);
                System.out.println("Oops!");
                return;
            }
        }
        System.out.println("finish!");
    }
}
