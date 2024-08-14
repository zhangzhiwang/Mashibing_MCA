package com.mashibing.dailyPractice.round4;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一棵树和一个sum值，收集这棵树能达到路径和sum的所有路径节点并返回一个列表List<List<Integer>>
 */
public class PathSum2_0727 {
    public static class TreeNode {
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
        if(root == null) {
            return retList;
        }

        return recurse(root, 0, targetSum, retList, new ArrayList<Integer>());
    }

    private List<List<Integer>> recurse(TreeNode root, int sum, int targetSum, List<List<Integer>> retList, List<Integer> list) {
        if(root == null) {
            return retList;
        }

        if(root.left == null && root.right == null) {
            if (sum + root.val == targetSum) {
                list.add(root.val);
                retList.add(copyList(list));
                list.remove(list.size() - 1);
            }
            return retList;
        }

        sum += root.val;
        list.add(root.val);
        recurse(root.left, sum, targetSum, retList, list);
        recurse(root.right, sum, targetSum, retList, list);

        list.remove(list.size() - 1);
        return retList;
    }

    private List<Integer> copyList(List<Integer> list) {
        List<Integer> copyList = new ArrayList<>();
        for (Integer i : list) {
            copyList.add(i);
        }

        return copyList;
    }
}
