package com.mashibing.dailyPractice.round2._71_to_100;

import com.alibaba.fastjson.JSONObject;
import com.mashibing.dailyPractice.round1._71_to_80.EncodeNaryTreeToBinaryTree_0315;
import com.mashibing.others.DuiShuQiUtil;
import com.mashibing.tree.NaryTreeNode;
import com.mashibing.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一颗多叉树变成一棵二叉树（序列化），然后再根据这棵二叉树还原成原来的多叉树（反序列化）
 */
public class EncodeNaryTreeToBinaryTree_0403 {
    public static TreeNode<String> encodeNaryTreeToBinaryTree(NaryTreeNode<String> head) {
        if(head == null) {
            return null;
        }

        TreeNode<String> h = new TreeNode<>(head.data);
        h.left = serialize(head.children);
        return h;
    }

    public static NaryTreeNode<String> decodeNaryTreeToBinaryTree(TreeNode<String> head) {
        if(head == null) {
            return null;
        }

        NaryTreeNode<String> h = new NaryTreeNode<>(head.value);
        h.children = deserialize(head.left);
        return h;
    }
    
    private static TreeNode<String> serialize(List<NaryTreeNode<String>> children) {
        if(children.isEmpty()) {
            return null;
        }

        TreeNode<String> head = null;
        TreeNode<String> cur = null;
        for (NaryTreeNode<String> naryNode : children) {
            TreeNode<String> node = new TreeNode<>(naryNode.data);
            if(head == null) {
                head = node;
            } else {
                cur.right = node;
            }

            cur = node;
            cur.left = serialize(naryNode.children);
        }

        return head;
    }

    private static List<NaryTreeNode<String>> deserialize(TreeNode<String> head) {
        List<NaryTreeNode<String>> list = new ArrayList<>();
        if(head == null) {
            return list;
        }

        TreeNode<String> cur = head;
        while (cur != null) {
            NaryTreeNode<String> naryTreeNode = new NaryTreeNode<>(cur.value);
            list.add(naryTreeNode);
            naryTreeNode.children = deserialize(cur.left);
            cur = cur.right;
        }
        
        return list;
    }

    public static void main(String[] args) {
        // 本题测试链接：https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree，但该题得是LeetCode的会员才可以，这里暂且自测
        NaryTreeNode<String> a = new NaryTreeNode<>("a");
        NaryTreeNode<String> b = new NaryTreeNode<>("b");
        NaryTreeNode<String> c = new NaryTreeNode<>("c");
        NaryTreeNode<String> d = new NaryTreeNode<>("d");
        NaryTreeNode<String> e = new NaryTreeNode<>("e");
        NaryTreeNode<String> f = new NaryTreeNode<>("f");
        NaryTreeNode<String> g = new NaryTreeNode<>("g");
        NaryTreeNode<String> h = new NaryTreeNode<>("h");
        NaryTreeNode<String> i = new NaryTreeNode<>("i");
        NaryTreeNode<String> j = new NaryTreeNode<>("j");
        NaryTreeNode<String> k = new NaryTreeNode<>("k");
        List<NaryTreeNode<String>> children = new ArrayList<>();
        children.add(b);
        children.add(c);
        children.add(d);
        a.children = children;

        children = new ArrayList<>();
        children.add(e);
        children.add(f);
        children.add(g);
        b.children = children;

        children = new ArrayList<>();
        children.add(j);
        children.add(k);
        c.children = children;

        children = new ArrayList<>();
        children.add(h);
        children.add(i);
        e.children = children;

        TreeNode<String> treeNodeHead = encodeNaryTreeToBinaryTree(a);
        DuiShuQiUtil.printBinaryTree(treeNodeHead);
        System.out.println("---------------------");

        NaryTreeNode<String> head = decodeNaryTreeToBinaryTree(treeNodeHead);
        System.out.println(JSONObject.toJSONString(head));
    }
}
