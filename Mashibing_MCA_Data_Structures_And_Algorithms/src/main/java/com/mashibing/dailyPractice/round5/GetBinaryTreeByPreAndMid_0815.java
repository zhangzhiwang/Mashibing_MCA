package com.mashibing.dailyPractice.round5;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据先序遍历和中序遍历的结果还原整个二叉树
 */
public class GetBinaryTreeByPreAndMid_0815 {
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

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || preorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }

        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return recurse(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private TreeNode recurse(int[] preorder, int l1, int r1, int[] inorder, int l2, int r2, Map<Integer, Integer> inMap) {
        if(l1 > r1) {
            return null;
        }

        int head = preorder[l1];
        TreeNode node = new TreeNode(head);
        int headIndex = inMap.get(head);
        node.left = recurse(preorder, l1 + 1, l1 + (headIndex - l2), inorder, l2, headIndex - 1, inMap);
        node.right = recurse(preorder, l1 + (headIndex - l2) + 1, r1, inorder, headIndex + 1, r2, inMap);

        return node;
    }

    public static void main(String[] args) {
        // leetcode：https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
    }
}
