package com.mashibing.dailyPractice.round3._No31_40;

import com.mashibing.dailyPractice.round2._0222.GetBinaryTreeByPreAndMid_0222;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据先序遍历和中序遍历的结果还原整个二叉树
 */
public class GetBinaryTreeByPreAndMid_0306 {
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
        if(preorder == null || inorder == null || preorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return recurse(preorder,0 ,preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    private TreeNode recurse(int[] preorder, int L1, int R1, int[] inorder, int L2, int R2, Map<Integer, Integer> map) {
        if(L1 > R1) {
            return null;
        }
        if(L1 == R1) {
            return new TreeNode(preorder[L1]);
        }

        int headValue = preorder[L1];
        int headIndexIn = map.get(headValue);

        TreeNode head = new TreeNode(headValue);
        head.left = recurse(preorder, L1 + 1, L1 + 1 + (headIndexIn - L2) - 1, inorder, L2, headIndexIn - 1, map);
        head.right = recurse(preorder, L1 + 1 + (headIndexIn - L2), R1, inorder, headIndexIn + 1, R2, map);
        return head;
    }
}
