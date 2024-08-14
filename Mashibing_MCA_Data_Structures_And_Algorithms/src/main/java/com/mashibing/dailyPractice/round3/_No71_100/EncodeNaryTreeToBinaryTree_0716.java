package com.mashibing.dailyPractice.round3._No71_100;

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
public class EncodeNaryTreeToBinaryTree_0716 {
    public static TreeNode serialize(NaryTreeNode root) {
        if(root == null) {
            return null;
        }

        TreeNode binaryRoot = new TreeNode(root.data);
        binaryRoot.left = recurse1(root.children);
        return binaryRoot;
    }

    private static TreeNode recurse1(List<NaryTreeNode> children) {
        if(children.isEmpty()) {
            return null;
        }

        TreeNode root = null;
        TreeNode cur = null;
        for (NaryTreeNode naryNode : children) {
            TreeNode binaryNode = new TreeNode(naryNode.data);
            if (root == null) {
                root = binaryNode;
                cur = root;
            } else {
                cur.right = binaryNode;
                cur = cur.right;
            }

            cur.left = recurse1(naryNode.children);
        }

        return root;
    }

    public static NaryTreeNode deserialize(TreeNode root) {
        if(root == null) {
            return null;
        }

        NaryTreeNode naryRoot = new NaryTreeNode(root.value);
        naryRoot.children = recurse2(root.left);
        return naryRoot;
    }

    private static List<NaryTreeNode> recurse2(TreeNode root) {
        List<NaryTreeNode> children = new ArrayList<>();
        if(root == null) {
            return children;
        }

        TreeNode cur = root;
        while (cur != null) {
            NaryTreeNode naryNode = new NaryTreeNode(cur.value);
            naryNode.children = recurse2(cur.left);
            children.add(naryNode);
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
        System.out.println("多叉树转二叉树：");
        DuiShuQiUtil.printBinaryTree(binaryTreeRoot);

        NaryTreeNode<String> naryTreeRoot = deserialize(binaryTreeRoot);
        System.out.println("二叉树树转多叉树：");
        System.out.println(JSONObject.toJSONString(naryTreeRoot));
    }
}
