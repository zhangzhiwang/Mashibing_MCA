package com.mashibing.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 题目：给定一颗二叉树head，和任意两个节点a和b，返回a和b的最低公共祖先
 * 思路：
 * 按照递归通用套路，分析可能的情况：
 * 1、最终结果和x有关
 * 如果最终结果和x有关，那么x一定就是a和b的最低公共祖先，包括两种情况：
 * （1）如果x的左子树包括a或者b，右子树包括另一个
 * （2）x就是a和b其中的一个
 * 2、最终结果和x无关
 * 情况：
 * （1）左子树或者右子树已经发现了a和b的最低公共祖先
 * （2）左子树和者右子树只发现了a和b其中的一个，另一个在两颗子树中都没有发现
 * 综上，x管子树要的信息有：是否发现了a、是否发现了b、a和b的最低公共祖先（如果发现了肯定有值，如果没发现就是null）
 *
 * 课程：体系班课时108
 */
public class LowestAncestor {
    static class LowestAncestorInfo {
        public boolean containsA;// 子树是否包括a节点
        public boolean containsB;// 子树是否包括b节点
        public TreeNode lowestAncestor;// 子树中的最低祖先

        public LowestAncestorInfo(boolean containsA, boolean containsB, TreeNode lowestAncestor) {
            this.containsA = containsA;
            this.containsB = containsB;
            this.lowestAncestor = lowestAncestor;
        }
    }

    public static TreeNode lowestAncestor(TreeNode head, TreeNode a, TreeNode b) {
        if(head == null) {
            return null;
        }

        return recurse(head, a, b).lowestAncestor;
    }

    public static LowestAncestorInfo recurse(TreeNode x, TreeNode a, TreeNode b) {
        if(x == null) {
            return new LowestAncestorInfo(false, false, null);
        }

        LowestAncestorInfo leftInfo = recurse(x.left, a, b);
        LowestAncestorInfo rightInfo = recurse(x.right, a, b);

        boolean containsA = leftInfo.containsA || rightInfo.containsA || (x == a);
        boolean containsB = leftInfo.containsB || rightInfo.containsB || (x == b);

        TreeNode lowestAncestor = null;
        // 最终结果和x有关：
        // 如果x就是a、b其中的一个，那么最低祖先一定是x
        // 上面的containsA和containsB已经包含这个逻辑了
//        if(x == a || x == b) {
//            lowestAncestor = x;
//        }
        // 如果x的左子树包括a或者b，右子树包括另一个，那么x一定是a和b的最低公共祖先
        if(containsA && containsB) {
            lowestAncestor = x;
        }

        // 最终结果和x无关：
        // 情况1：x的左右子树其中之一发现了最低公共祖先，那么最终的答案就是它
        if(leftInfo.lowestAncestor != null) {
            lowestAncestor = leftInfo.lowestAncestor;
        }
        if(rightInfo.lowestAncestor != null) {
            lowestAncestor = rightInfo.lowestAncestor;
        }
        /*
         情况2：x的左右子树没发现全a和b，即x的左右子树中只发现了a和b其中的一个，这种情况lowestAncestor就应该为null，因为站在x的角度来看lowestAncestor不知道是谁，
         由于默认值就是null，所以这种情况什么都不做
         */


        return new LowestAncestorInfo(containsA, containsB, lowestAncestor);
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
                BinaryTreeUtil.printBinaryTree(head);
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
