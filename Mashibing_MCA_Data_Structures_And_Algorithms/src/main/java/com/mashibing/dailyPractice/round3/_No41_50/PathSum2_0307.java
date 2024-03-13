package com.mashibing.dailyPractice.round3._No41_50;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一棵树和一个sum值，收集这棵树能达到路径和sum的所有路径节点并返回一个列表List<List<Integer>>
 */
public class PathSum2_0307 {
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

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> retList = new ArrayList<>();
        if (root == null) {
            return retList;
        }

        recurse(root, targetSum, 0, retList, new ArrayList<Integer>());
        return retList;
    }

    private void recurse(TreeNode root, int targetSum, int sum, List<List<Integer>> retList, List<Integer> list) {
//        if (root == null) {
//            return;
//        }
        if (root.left == null && root.right == null) {
            if (sum + root.val == targetSum) {
                list.add(root.val);
                retList.add(copyList(list));
                list.remove(list.size() - 1);
            }
            return;
        }

        sum += root.val;
        list.add(root.val);
        if(root.left != null) {
            recurse(root.left, targetSum, sum, retList, list);
        }
        if(root.right != null) {
            recurse(root.right, targetSum, sum, retList, list);
        }
        list.remove(list.size() - 1);
    }

    private List<Integer> copyList(List<Integer> list) {
        List<Integer> copyList = new ArrayList<>();
        for (Integer i : list) {
            copyList.add(i);
        }
        return copyList;
    }
}
