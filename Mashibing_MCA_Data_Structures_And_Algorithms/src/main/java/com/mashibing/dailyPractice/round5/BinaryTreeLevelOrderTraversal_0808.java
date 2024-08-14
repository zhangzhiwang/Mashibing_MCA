package com.mashibing.dailyPractice.round5;

import com.mashibing.dailyPractice.round4.BinaryTreeLevelOrderTraversal_0726;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 按从低往高（叶子节点到根节点）的顺序收集每一层的所有节点
 */
public class BinaryTreeLevelOrderTraversal_0808 {
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
        List<List<Integer>> retList = new ArrayList<>();
        if(root == null) {
            return retList;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode end = root;
        TreeNode nextEnd = null;
        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            list.add(poll.val);
            if(poll.left != null) {
                queue.add(poll.left);
                nextEnd = poll.left;
            }
            if(poll.right != null) {
                queue.add(poll.right);
                nextEnd = poll.right;
            }

            if(poll == end) {
                retList.add(0, list);
                list = new ArrayList<>();
                end = nextEnd;
                nextEnd = null;
            }
        }

        return retList;
    }

    public static void main(String[] args) {
        // 测试链接：https://leetcode.com/problems/binary-tree-level-order-traversal-ii
    }
}
