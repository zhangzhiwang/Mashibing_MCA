package com.mashibing.dailyPractice.round2._0223;

import com.mashibing.dailyPractice.round1._2_1.BinaryTreeLevelOrderTraversal_0201;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 按从低往高（叶子节点到根节点）的顺序收集每一层的所有节点
 */
public class BinaryTreeLevelOrderTraversal_0223 {
    /**
     * leetcode
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
        List<List<Integer>> retList = new LinkedList<>();
        if(root == null) {
            return retList;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if(poll.left != null) {
                    queue.add(poll.left);
                }
                if(poll.right != null) {
                    queue.add(poll.right);
                }
            }
            retList.add(0, list);
        }

        return retList;
    }
}
