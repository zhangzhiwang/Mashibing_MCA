package com.mashibing.tree;

import com.mashibing.others.DuiShuQiUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一颗多叉树变成一棵二叉树（序列化），然后再根据这棵二叉树还原成原来的多叉树（反序列化）
 * 思路：
 * 序列化：
 * 在多叉树中的每一个节点的所有直接孩子，比如a节点下面有b、c、d三个直接孩子，那么转换成二叉树后，让所有直接孩子成为该父节点的左孩子的右边界，其中第一个直接孩子
 * 是二叉树父节点的左孩子，其它所有孩子都是这个左孩子的右孩子（既右边界）。比如上面的节点转换成二叉树后，a是父节点，b是a节点的第一个直接孩子，那么b就作为a节点的左孩子，
 * 其它孩子c、d作为左孩子b的右孩子，也就是右边界，所以在二叉树中，b、c、d三个孩子成为了父节点a的左孩子的右边界。
 * 需要用到递归。
 * 比如：
 * 多叉树：
 *                     a
 *           b         c         d
 *       e       f            j     k
 *            g  h  i
 * 转换成二叉树的形式：
 *                     a
 *                  b
 *               e     c
 *                 f      d
 *                g     j
 *                 h      k
 *                  i
 * 通过这种方式转换的二叉树，如果想知道某一个节点有没有孩子，就看这个节点有没有左子树就可以了。
 * 反序列化：
 * 递归取出一个节点左孩子的右边界上的所有节点，然后指针往右孩子沉，类似于图的深度优先遍历
 *
 * 该题目的难度级别是hard
 * 课程：体系班课时94-95
 */
public class EncodeNaryTreeToBinaryTree {

    /**
     * 多叉树节点
     */
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

    /**
     * 多叉树转二叉树（序列化）
     * @param head
     * @return
     */
    public static TreeNode<String> naryTreeToBinaryTree(NaryTreeNode<String> head) {
        if (head == null) {
            return null;
        }

        TreeNode<String> newHead = new TreeNode<>(head.data);
        newHead.left = serialize(head.children);
        return newHead;
    }

    private static TreeNode<String> serialize(List<NaryTreeNode<String>> children) {
        if (children == null || children.size() == 0) {
            return null;
        }

        TreeNode<String> head = null;
        TreeNode<String> cur = null;
        for (NaryTreeNode<String> child : children) {
            TreeNode<String> treeNode = new TreeNode<>(child.data);
            if (head == null) {
                head = treeNode;
            } else {
                cur.right = treeNode;// 注意：上面当head是空时给head赋值，当head不是空时，是给cur的右孩子赋值不是给head的右孩子赋值
            }
            cur = treeNode;
            cur.left = serialize(child.children);
        }

        return head;
    }

    /**
     * 二叉树转多叉树（反序列化）
     * @param head
     * @return
     */
    public static NaryTreeNode<String> binaryTreeToNaryTree(TreeNode<String> head) {
        if(head == null) {
            return null;
        }

        // 二叉树的头节点就是多叉树的头结点
        return new NaryTreeNode<>(head.value, deserialize(head.left));
    }

    /**
     *
     * @param head 更高一层的左孩子
     * @return 返回值是如果该二叉树的头部作为多叉树的头部，那么返回该多叉树头部的所有直接孩子，也就是在二叉树中头结点的左孩子的右边界
     */
    private static List<NaryTreeNode<String>> deserialize(TreeNode<String> head) {
        List<NaryTreeNode<String>> retList = new ArrayList<>();
        if(head == null) {
            return retList;
        }

        while(head != null) {
            retList.add(new NaryTreeNode<>(head.value, deserialize(head.left)));
            head = head.right;
        }

        return retList;
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

//        System.out.println(treeNodeHead.data);
//        System.out.println(treeNodeHead.left.data);// b
//        System.out.println(treeNodeHead.left.left.data);// e
//        System.out.println(treeNodeHead.left.left.left.data);// h
//        System.out.println(treeNodeHead.left.left.left.left);// null
//        System.out.println(treeNodeHead.left.left.left.right.data);// i
//        System.out.println(treeNodeHead.left.left.right.data);// f
//        System.out.println(treeNodeHead.left.left.right.right.data);// g
//        System.out.println(treeNodeHead.left.left.right.left);// null
//        System.out.println(treeNodeHead.left.right.data);// c
//        System.out.println(treeNodeHead.left.right.left.data);// j
//        System.out.println(treeNodeHead.left.right.left.right.data);// k
//        System.out.println(treeNodeHead.left.right.left.left);// null
//        System.out.println(treeNodeHead.left.right.right.data);// d
//        System.out.println(treeNodeHead.left.right.right.left);// null
//        System.out.println(treeNodeHead.left.right.right.right);// null
//        System.out.println("--------------");
//
//        // head = a
//        NaryTreeNode<String> naryTreeNodeHead = binaryTreeToNaryTree(treeNodeHead);
//        System.out.println(naryTreeNodeHead.data);
//        for(NaryTreeNode<String> child : naryTreeNodeHead.children) {
//            System.out.print(child.data + "\t");
//        }
//        System.out.println();
//        System.out.println("--------------");
//
//        // head = b
//        System.out.println(naryTreeNodeHead.children.get(0).data);
//        for(NaryTreeNode<String> child : naryTreeNodeHead.children.get(0).children) {
//            System.out.print(child.data + "\t");
//        }
//        System.out.println();
//        System.out.println("--------------");
//
//        // head = e
//        System.out.println(naryTreeNodeHead.children.get(0).children.get(0).data);
//        for(NaryTreeNode<String> child : naryTreeNodeHead.children.get(0).children.get(0).children) {
//            System.out.print(child.data + "\t");
//        }
//        System.out.println();
//        System.out.println("--------------");
//
//        // head = e
//        System.out.println(naryTreeNodeHead.children.get(1).data);
//        for(NaryTreeNode<String> child : naryTreeNodeHead.children.get(1).children) {
//            System.out.print(child.data + "\t");
//        }
//        System.out.println();
//        System.out.println("--------------");
//
//        // head = d
//        System.out.println(naryTreeNodeHead.children.get(2).data);
//        for(NaryTreeNode<String> child : naryTreeNodeHead.children.get(2).children) {
//            System.out.print(child.data + "\t");
//        }
//        System.out.println();
//        System.out.println("--------------");
//
//        // head = j
//        System.out.println(naryTreeNodeHead.children.get(1).children.get(1).data);
//        for(NaryTreeNode<String> child : naryTreeNodeHead.children.get(1).children.get(1).children) {
//            System.out.print(child.data + "\t");
//        }
//        System.out.println();
//        System.out.println("--------------");
    }
}
