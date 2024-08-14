package com.mashibing.dailyPractice.round4;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据先序遍历和中序遍历的结果还原整个二叉树
 */
public class GetBinaryTreeByPreAndMid_0726 {
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
        if(preorder == null || inorder == null || preorder.length != inorder.length || preorder.length == 0) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return recurse(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    private TreeNode recurse(int[] preorder, int l1, int r1, int[] inorder, int l2, int r2, Map<Integer, Integer> map) {
        if(l1 > r1) {
            return null;
        }

        int headValue = preorder[l1];
        int headIndexInRight = map.get(headValue);
        TreeNode head = new TreeNode(headValue);
        head.left = recurse(preorder, l1 + 1, l1 + (headIndexInRight - l2), inorder, l2, headIndexInRight - 1, map);
        head.right = recurse(preorder, l1 + (headIndexInRight - l2) + 1, r1, inorder, headIndexInRight + 1, r2, map);
        return head;
    }
    
    public static void main(String[] args) {
        // leetcode：https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
    }
}
