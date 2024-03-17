package com.mashibing.dailyPractice.round1._71_to_80;

import com.alibaba.fastjson.JSONObject;
import com.mashibing.others.DuiShuQiUtil;
import com.mashibing.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一颗多叉树变成一棵二叉树（序列化），然后再根据这棵二叉树还原成原来的多叉树（反序列化）
 */
public class EncodeNaryTreeToBinaryTree_0315 {
    static class NaryTreeNode<T> {
        public T data;
        public List<NaryTreeNode<T>> children = new ArrayList<>();

        public NaryTreeNode(T data) {
            this.data = data;
        }

        public NaryTreeNode(T data, List<NaryTreeNode<T>> children) {
            this.data = data;
            this.children = children;
        }

        @Override
        public String toString() {
            return "NaryTreeNode{" +
                    "data=" + data +
                    ", children=" + children +
                    '}';
        }
    }
    
    public static TreeNode<String> naryTreeToBinaryTree(NaryTreeNode<String> head) {
        if(head == null) {
            return null;
        }

        TreeNode<String> binaryTreeHead = new TreeNode<>(head.data);
        binaryTreeHead.left = serialize(head.children);
        return binaryTreeHead;
    }

    public static NaryTreeNode<String> binaryTreeToNaryTree(TreeNode<String> head) {
        if(head ==null) {
            return null;
        }

        NaryTreeNode<String> naryTreeHead = new NaryTreeNode<>(head.value);
        naryTreeHead.children = deserialize(head.left);
        return naryTreeHead;
    }

    private static List<NaryTreeNode<String>> deserialize(TreeNode<String> head) {
        List<NaryTreeNode<String>> children = new ArrayList<>();
        if(head == null) {
            return children;
        }

        while(head != null) {
            NaryTreeNode<String> child = new NaryTreeNode<>(head.value);
            children.add(child);
            child.children = deserialize(head.left);
            head = head.right;
        }

        return children;
    }

    private static TreeNode<String> serialize(List<NaryTreeNode<String>> children) {
        if(children.isEmpty()) {
            return null;
        }

        TreeNode<String> head = null;
        TreeNode<String> c = null;
        for (NaryTreeNode<String> child : children) {
            TreeNode<String> node = new TreeNode<>(child.data);
            if(head == null) {
                head = node;
            } else {
                c.right = node;
            }
            
            c = node;
            c.left = serialize(child.children);
        }
        
        return head;
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

        TreeNode<String> treeNodeHead = naryTreeToBinaryTree(a);
        DuiShuQiUtil.printBinaryTree(treeNodeHead);
        System.out.println("---------------------");

        NaryTreeNode<String> head = binaryTreeToNaryTree(treeNodeHead);
        System.out.println(JSONObject.toJSONString(head));
    }
}
