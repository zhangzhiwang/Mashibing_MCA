package com.mashibing.dailyPractice.round1._81_to_90;

import com.mashibing.others.DuiShuQiUtil;
import com.mashibing.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一颗二叉树head和任意两个节点a和b，返回a和b的最低公共祖先
 */
public class LowestAncestor_0319 {
    static class LowestAncestorInfo_0319 {
        private boolean containsA;
        private boolean containsB;
        private TreeNode lowestAncestor;

        public LowestAncestorInfo_0319(boolean containsA, boolean containsB, TreeNode lowestAncestor) {
            this.containsA = containsA;
            this.containsB = containsB;
            this.lowestAncestor = lowestAncestor;
        }
    }

    public static TreeNode lowestAncestor(TreeNode head, TreeNode a, TreeNode b) {
        if(head == null || a == null || b == null) {
            return null;
        }

        return recurse(head, a, b).lowestAncestor;
    }

    private static LowestAncestorInfo_0319 recurse(TreeNode x, TreeNode a, TreeNode b) {
        if(x == null) {
            return new LowestAncestorInfo_0319(false, false, null);
        }

        LowestAncestorInfo_0319 leftInfo = recurse(x.left, a, b);
        LowestAncestorInfo_0319 rightInfo = recurse(x.right, a, b);

        TreeNode lowestAncestor = null;
        if((leftInfo.containsA && !leftInfo.containsB && rightInfo.containsB && !rightInfo.containsA)
        || (!leftInfo.containsA && leftInfo.containsB && !rightInfo.containsB && rightInfo.containsA)
        || x == a || x == b) {
            lowestAncestor = x;
        } else if (leftInfo.containsA && leftInfo.containsB) {
            lowestAncestor = leftInfo.lowestAncestor;
        } else if (rightInfo.containsB && rightInfo.containsA) {
            lowestAncestor = rightInfo.lowestAncestor;
        }

        boolean containsA = leftInfo.containsA || rightInfo.containsA || x == a;
        boolean containsB = leftInfo.containsB || rightInfo.containsB || x == b;
        return new LowestAncestorInfo_0319(containsA, containsB, lowestAncestor);
    }

    //以下是对数器
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
                System.out.println("o1 = " + o1.value);
                System.out.println("o2 = " + o2.value);
                System.out.println("ans1 = " + ans1.value);
                System.out.println("ans2 = " + ans2.value);
                System.out.println("Oops!");
                return;
            }
        }
        System.out.println("finish!");
    }
}
