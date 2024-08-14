package com.mashibing.dailyPractice.round4;

import com.mashibing.tree.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 求一棵二叉树的最大宽度
 */
public class TreeMaxWidth_0802 {
    public static int treeMaxWidth(TreeNode<Integer> root) {
        if(root == null) {
            return 0;
        }

        Queue<TreeNode<Integer>> help = new LinkedList<>();
        help.add(root);
        int thisLvlCount = 1;
        int nextLvlCount = 0;
        int maxCount = 0;
        TreeNode<Integer> thisLvlEnd = root;
        TreeNode<Integer> nextLvlEnd = null;
        while (!help.isEmpty()) {
            TreeNode<Integer> poll = help.poll();
            if(poll.left != null) {
                help.add(poll.left);
                nextLvlEnd = poll.left;
                nextLvlCount++;
            }
            if(poll.right != null) {
                help.add(poll.right);
                nextLvlEnd = poll.right;
                nextLvlCount++;
            }

            if(poll == thisLvlEnd) {
                maxCount = Math.max(maxCount, thisLvlCount);
                thisLvlCount = nextLvlCount;
                nextLvlCount = 0;
                thisLvlEnd = nextLvlEnd;
                nextLvlEnd = null;
            }
        }

        return maxCount;
    }

    // 对数器
    // for test
    public static TreeNode<Integer> generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode<Integer> generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode<Integer> head = new TreeNode<Integer>((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static int maxWidthUseMap(TreeNode<Integer> head) {
        if (head == null) {
            return 0;
        }
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(head);
        // key 在 哪一层，value
        HashMap<TreeNode<Integer>, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1; // 当前你正在统计哪一层的宽度
        int curLevelNodes = 0; // 当前层curLevel层，宽度目前是多少
        int max = 0;
        while (!queue.isEmpty()) {
            TreeNode<Integer> cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode<Integer> head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != treeMaxWidth(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
