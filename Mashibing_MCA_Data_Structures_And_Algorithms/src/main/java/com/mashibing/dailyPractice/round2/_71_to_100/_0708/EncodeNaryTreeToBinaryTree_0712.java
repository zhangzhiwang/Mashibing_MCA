package com.mashibing.dailyPractice.round2._71_to_100._0708;

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
public class EncodeNaryTreeToBinaryTree_0712 {
    public static TreeNode<String> serialize(NaryTreeNode<String> root) {
        if (root == null) {
            return null;
        }

        TreeNode<String> binaryRoot = new TreeNode<>(root.data);
        binaryRoot.left = recurse(root.children);
        return binaryRoot;
    }

    private static TreeNode<String> recurse(List<NaryTreeNode<String>> children) {
        if (children.isEmpty()) {
            return null;
        }

        TreeNode<String> root = null;
        TreeNode<String> cur = null;
        for (NaryTreeNode<String> naryTreeNode : children) {
            if(root == null) {
                root = new TreeNode<>(naryTreeNode.data);
                cur = root;
            } else {
                cur.right = new TreeNode<>(naryTreeNode.data);
                cur = cur.right;
            }

            cur.left = recurse(naryTreeNode.children);
        }

        return root;
    }

    public static NaryTreeNode<String> deserialize(TreeNode<String> root) {
        if(root == null) {
            return null;
        }

        NaryTreeNode<String> naryRoot = new NaryTreeNode<>(root.value);
        naryRoot.children = recurse2(root.left);
        return naryRoot;
    }

    private static List<NaryTreeNode<String>> recurse2(TreeNode<String> root) {
        List<NaryTreeNode<String>> children = new ArrayList<>();
        if(root == null) {
            return children;
        }

        TreeNode<String> cur = root;
        while (cur != null) {
            NaryTreeNode<String> naryTreeNode = new NaryTreeNode<>(cur.value);
            naryTreeNode.children = recurse2(cur.left);
            children.add(naryTreeNode);
            cur = cur.right;
        }

        return children;
    }

    public static void main(String[] args) {
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

        TreeNode<String> binaryTreeRoot = serialize(a);
        DuiShuQiUtil.printBinaryTree(binaryTreeRoot);

        NaryTreeNode<String> naryTreeRoot = deserialize(binaryTreeRoot);
        System.out.println(JSONObject.toJSONString(naryTreeRoot));
    }
}
