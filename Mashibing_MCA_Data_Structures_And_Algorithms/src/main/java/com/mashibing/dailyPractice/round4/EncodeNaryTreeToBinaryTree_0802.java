package com.mashibing.dailyPractice.round4;

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
public class EncodeNaryTreeToBinaryTree_0802 {
    public static TreeNode<Integer> serialize(NaryTreeNode<Integer> root) {
        if(root == null) {
            return null;
        }

        TreeNode<Integer> head = new TreeNode<>(root.data);
        head.left = recurse1(root.children);
        return head;
    }

    private static TreeNode<Integer> recurse1(List<NaryTreeNode<Integer>> children) {
        if (children.isEmpty()) {
            return null;
        }

        TreeNode<Integer> root = null;
        TreeNode<Integer> cur = null;
        for (NaryTreeNode<Integer> naryNode : children) {
            TreeNode<Integer> node = new TreeNode<>(naryNode.data);
            if(root == null) {
                root = node;
            } else {
                cur.right = node;
            }

            cur = node;
            cur.left = recurse1(naryNode.children);
        }

        return root;
    }

    public static NaryTreeNode<Integer> deserialize(TreeNode<Integer> root) {
        if(root == null) {
            return null;
        }

        NaryTreeNode<Integer> head = new NaryTreeNode<>(root.value);
        head.children = recurse2(root.left);
        return head;
    }

    private static List<NaryTreeNode<Integer>> recurse2(TreeNode<Integer> root) {
        List<NaryTreeNode<Integer>> children = new ArrayList<>();
        if(root == null) {
            return children;
        }

        TreeNode<Integer> cur = root;
        while (cur != null) {
            NaryTreeNode<Integer> naryNode = new NaryTreeNode<>(cur.value);
            naryNode.children = recurse2(cur.left);
            children.add(naryNode);
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

        TreeNode<Integer> binaryTreeRoot = serialize(a);
        System.out.println("多叉树转二叉树：");
        DuiShuQiUtil.printBinaryTree(binaryTreeRoot);

        NaryTreeNode<Integer> naryTreeRoot = deserialize(binaryTreeRoot);
        System.out.println("二叉树树转多叉树：");
        System.out.println(JSONObject.toJSONString(naryTreeRoot));
    }
}
