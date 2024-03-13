package com.mashibing.dailyPractice.round2._0222;

import java.util.HashMap;
import java.util.Objects;

/**
 * 根据先序遍历和中序遍历的结果还原整个二叉树
 */
public class GetBinaryTreeByPreAndMid_0222 {
    /**
     * leetcode
     */
    public static class TreeNode {
        public int val;
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
        if ((preorder == null && inorder == null) || (preorder == null ^ inorder == null)) {
            return null;
        }
        if ((preorder.length != inorder.length) || preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return recurse(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    private TreeNode recurse(int[] preorder, int L1, int R1, int[] inorder, int L2, int R2, HashMap<Integer, Integer> map) {
        if(L1 > R1) {
            return null;
        }

        if (L1 == R1) {
            return new TreeNode(preorder[L1]);
        }

        int headValue = preorder[L1];
        int headIndex = map.get(headValue);

        TreeNode head = new TreeNode(headValue);
        head.left = recurse(preorder, L1 + 1, L1 + 1 + (headIndex - L2) -1, inorder, L2, headIndex - 1, map);
        head.right = recurse(preorder, L1 + headIndex - L2 + 1, R1, inorder, headIndex + 1, R2, map);
        return head;
    }

    public static void main(String[] args) {
        int[] preorder = {1,2};
        int[] inorder = {2,1};
        new GetBinaryTreeByPreAndMid_0222().buildTree(preorder, inorder);
    }
}
