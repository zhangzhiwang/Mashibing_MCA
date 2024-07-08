package com.mashibing.dailyPractice.round2._71_to_100;

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
public class EncodeNaryTreeToBinaryTree_0518 {
    public static TreeNode<String> toBinaryTree(NaryTreeNode<String> root) {
        if(root == null) {
            return null;
        }

        TreeNode<String> binaryTreeRoot = new TreeNode<>(root.data);
        binaryTreeRoot.left = recurse1(root.children);
        return binaryTreeRoot;
    }

    private static TreeNode<String> recurse1(List<NaryTreeNode<String>> children) {
        if(children.isEmpty()) {
            return null;
        }

        TreeNode<String> root = null;
        TreeNode<String> cur = null;
        for (NaryTreeNode<String> child : children) {
            if(root == null) {
                root = new TreeNode<>(child.data);
                cur = root;
            } else {
                cur.right = new TreeNode<>(child.data);
                cur = cur.right;
            }

            cur.left = recurse1(child.children);
        }

        return root;
    }

    public static NaryTreeNode<String> toNaryTree(TreeNode<String> root) {
        if(root == null) {
            return null;
        }

        NaryTreeNode<String> naryTreeRoot = new NaryTreeNode<>(root.value);
        naryTreeRoot.children = recurse2(root);
        return naryTreeRoot;
    }

    private static List<NaryTreeNode<String>> recurse2(TreeNode<String> root) {
        List<NaryTreeNode<String>> children = new ArrayList<>();
        if(root.left == null) {
            return children;
        }

        TreeNode<String> cur = root.left;
        while (cur != null) {
            NaryTreeNode<String> naryTreeNode = new NaryTreeNode<>(cur.value);
            children.add(naryTreeNode);
            naryTreeNode.children = recurse2(cur);
            cur = cur.right;
        }

        return children;
    }

    public static void main(String[] args) {
//        TreeNode<String> root = null;
//        TreeNode<String> cur = root;
//        root = new TreeNode<>("1");
//        System.out.println(cur);

        NaryTreeNode<String> a = new NaryTreeNode<>("a");
        NaryTreeNode<String> b = new NaryTreeNode<>("b");
        NaryTreeNode<String> c = new NaryTreeNode<>("c");
        NaryTreeNode<String> d = new NaryTreeNode<>("d");
        NaryTreeNode<String> e = new NaryTreeNode<>("e");
        NaryTreeNode<String> f = new NaryTreeNode<>("f");
        NaryTreeNode<String> g = new NaryTreeNode<>("g");
        NaryTreeNode<String> h = new NaryTreeNode<>("h");
        NaryTreeNode<String> i = new NaryTreeNode<>("i");
        a.children = Arrays.asList(b, c, d);
        b.children = Arrays.asList(e, f);
        d.children = Arrays.asList(g, h, i);

        TreeNode<String> binaryTreeRoot = toBinaryTree(a);
        DuiShuQiUtil.printBinaryTree(binaryTreeRoot);

        NaryTreeNode<String> naryTreeRoot = toNaryTree(binaryTreeRoot);
        System.out.println(JSONObject.toJSONString(naryTreeRoot));
    }
}
