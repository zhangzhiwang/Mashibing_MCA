package com.mashibing.dailyPractice.round5;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一棵树和一个sum值，收集这棵树能达到路径和sum的所有路径节点并返回一个列表List<List<Integer>>
 */
public class PathSum2_0830 {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
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

        recurse(root, targetSum, 0, new ArrayList<Integer>(), retList);
        return retList;
    }

    private void recurse(TreeNode root, int targetSum, int sum, List<Integer> list, List<List<Integer>> retList) {
        if(root == null) {
            return;
        }

        if(root.left == null && root.right == null) {
            sum += root.val;
            if(sum == targetSum) {
                list.add(root.val);
                retList.add(copyList(list));
                list.remove(list.size() - 1);
            }
            return;
        }

        sum += root.val;
        list.add(root.val);
        recurse(root.left, targetSum, sum, list, retList);
        recurse(root.right, targetSum, sum, list, retList);
        list.remove(list.size() - 1);
    }

    private List<Integer> copyList(List<Integer> list) {
        List<Integer> copyList = new ArrayList<>();
        for (int i : list) {
            copyList.add(i);
        }
        return copyList;
    }

    public static void main(String[] args) {
        // 测试链接：https://leetcode.com/problems/path-sum-ii
    }
}
