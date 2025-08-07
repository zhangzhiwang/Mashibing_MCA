package com.mashibing.dailyPractice.round5;

import com.alibaba.fastjson.JSONObject;
import com.mashibing.others.DuiShuQiUtil;
import com.mashibing.tree.NaryTreeNode;
import com.mashibing.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 将一颗多叉树变成一棵二叉树（序列化），然后再根据这棵二叉树还原成原来的多叉树（反序列化）
 */
public class EncodeNaryTreeToBinaryTree_0815 {
    public static TreeNode<Integer> naryTreeToBinaryTree(NaryTreeNode<Integer> root) {
        if(root == null) {
            return null;
        }

        TreeNode<Integer> head = new TreeNode<>(root.data);
        head.left = serialize(root.children);
        return head;
    }

    private static TreeNode<Integer> serialize(List<NaryTreeNode<Integer>> children) {
        if(children.isEmpty()) {
            return null;
        }

        TreeNode<Integer> root = null;
        TreeNode<Integer> cur = null;
        for (NaryTreeNode<Integer> child : children) {
            TreeNode<Integer> node = new TreeNode<>(child.data);
            if(root == null) {
                root = node;
            } else {
                cur.right = node;
            }

            cur = node;
            cur.left = serialize(child.children);
        }

        return root;
    }

    public static NaryTreeNode<Integer> binaryTreeToNaryTree(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }

        NaryTreeNode<Integer> head = new NaryTreeNode<>(root.value);
        head.children = deserialize(root.left);
        return head;
    }

    private static List<NaryTreeNode<Integer>> deserialize(TreeNode<Integer> root) {
        List<NaryTreeNode<Integer>> children = new ArrayList<>();
        if(root == null) {
            return children;
        }

        TreeNode<Integer> cur = root;
        while (cur != null) {
            NaryTreeNode<Integer> node = new NaryTreeNode<>(cur.value);
            node.children = deserialize(cur.left);
            children.add(node);
            cur = cur.right;
        }

        return children;
    }

    public static void main(String[] args) {
        NaryTreeNode<Integer> a = new NaryTreeNode<>(1);
        NaryTreeNode<Integer> b = new NaryTreeNode<>(2);
        NaryTreeNode<Integer> c = new NaryTreeNode<>(3);
        NaryTreeNode<Integer> d = new NaryTreeNode<>(4);
        NaryTreeNode<Integer> e = new NaryTreeNode<>(5);
        NaryTreeNode<Integer> f = new NaryTreeNode<>(6);
        NaryTreeNode<Integer> g = new NaryTreeNode<>(7);
        NaryTreeNode<Integer> h = new NaryTreeNode<>(8);
        NaryTreeNode<Integer> i = new NaryTreeNode<>(9);
        a.children = Arrays.asList(b, c, d);
        b.children = Arrays.asList(e, f);
        d.children = Arrays.asList(g, h, i);

        TreeNode<Integer> binaryTreeRoot = naryTreeToBinaryTree(a);
        System.out.println("多叉树转二叉树：");
        DuiShuQiUtil.printBinaryTree(binaryTreeRoot);

        NaryTreeNode<Integer> naryTreeRoot = binaryTreeToNaryTree(binaryTreeRoot);
        System.out.println("二叉树树转多叉树：");
        System.out.println(JSONObject.toJSONString(naryTreeRoot));
    }
}
