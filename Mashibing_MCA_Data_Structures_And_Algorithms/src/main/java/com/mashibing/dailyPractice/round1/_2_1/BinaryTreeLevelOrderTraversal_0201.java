package com.mashibing.dailyPractice.round1._2_1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 按从低往高（叶子节点到根节点）的顺序收集每一层的所有节点
 */
public class BinaryTreeLevelOrderTraversal_0201 {
    /**
     * 由于本题目要在leetcode上去验证，所以要使用leetcode给出的节点类
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> resultList = new LinkedList<>();
        if (root == null) {
            return resultList;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int father = 1;
        int son = 0;
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            father--;
            if (node.left != null) {
                queue.add(node.left);
                son++;
            }
            if (node.right != null) {
                queue.add(node.right);
                son++;
            }
            if (father == 0) {
                resultList.add(0, list);
                if (!queue.isEmpty()) {
                    list = new ArrayList<>();
                    father = son;
                    son = 0;
                }
            }
        }

        return resultList;
    }

    public static void main(String[] args) {
        // 测试链接：https://leetcode.com/problems/binary-tree-level-order-traversal-ii
    }
}
