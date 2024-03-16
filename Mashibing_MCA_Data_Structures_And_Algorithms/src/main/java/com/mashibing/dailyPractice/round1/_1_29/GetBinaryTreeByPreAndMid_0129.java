package com.mashibing.dailyPractice.round1._1_29;

import com.mashibing.others.DuiShuQiUtil;
import com.mashibing.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据先序遍历和中序遍历的结果还原整个二叉树
 */
public class GetBinaryTreeByPreAndMid_0129 {
    public TreeNode buildTree(TreeNode[] pre, TreeNode[] in) {
        if (pre == null || in == null || pre.length != in.length || pre.length == 0) {
            return null;
        }

        // 对中序遍历建立缓存，防止在查找head位置时反复遍历
        Map<TreeNode, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }

        return recurse(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
    }

    private TreeNode recurse(TreeNode[] pre, int l1, int l2, TreeNode[] in, int r1, int r2, Map<TreeNode, Integer> inMap) {
        if (l1 > l2) {
            return null;
        }

        if (l1 == l2) {
            return pre[l1];
        }

        TreeNode head = pre[l1];
        int h = inMap.get(head);
        head.left = recurse(pre, l1 + 1, l1 + h - r1, in, r1, h - 1, inMap);
        head.right = recurse(pre, l1 + h - r1 + 1, l2, in, h + 1, r2, inMap);

        return head;
    }

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);


        TreeNode[] preorder = {node3, node9, node20, node15, node7};
        TreeNode[] inorder = {node9, node3, node15, node20, node7};
        TreeNode head = new GetBinaryTreeByPreAndMid_0129().buildTree(preorder, inorder);
        DuiShuQiUtil.printBinaryTree(head);

        /*
         leetcode：https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
         leetcode版实现见：com.mashibing.dailyPractice.round3._No31_40.GetBinaryTreeByPreAndMid_0306
         */
    }
}
