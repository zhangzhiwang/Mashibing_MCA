package com.mashibing.dailyPractice.round1._2_1;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一棵树和一个sum值，收集这棵树能达到路径和sum的所有路径节点并返回一个列表List<List<Integer>>
 */
public class PathSum2_0201 {
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
        List<List<Integer>> list = new ArrayList<>();
        if(root == null) {
            return list;
        }

        return recurse(root, 0, targetSum, new ArrayList<Integer>(), new ArrayList<List<Integer>>());
    }

    private List<List<Integer>> recurse(TreeNode head, int sum, int targetSum, List<Integer> path, List<List<Integer>> retList) {
        if(head.left == null && head.right == null) {
            if(sum + head.val == targetSum) {
                path.add(head.val);
                retList.add(copyList(path));
                path.remove(path.size() - 1);
            }

            return retList;
        }

        path.add(head.val);
        if(head.left != null) {
            recurse(head.left, sum + head.val, targetSum, path, retList);
        }

        if(head.right != null) {
            recurse(head.right, sum + head.val, targetSum, path, retList);
        }

        path.remove(path.size() - 1);
        return retList;
    }

    private List<Integer> copyList(List<Integer> srcList) {
        List<Integer> list = new ArrayList<>();
        for(Integer i : srcList) {
            list.add(i);
        }

        return list;
    }

    public static void main(String[] args) {
        // 测试链接：https://leetcode.com/problems/path-sum-ii
    }
}
