package com.mashibing.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 按层遍历二叉树的序列化和反序列化
 * 思路：
 * 序列化：
 * 1、准备两个队列，一个是用于装最终序列化的结果res，一个是装节点的辅助队列help
 * 2、先把头的值和头结点弹进两个队列，然后开始循环从辅助队列中弹出元素。弹出一个元素后先判断它有没有左孩子，如果有那么左孩子的值（data）入res队列，左孩子的节点入help队列，
 * 如果没有左孩子，那么将占位符"#"入res队列，help队列不放入东西；同样的方法再处理右孩子
 * 3、当help队列为空时整个过程结束，res里面装的就是按层遍历的结果。
 * 反序列化：
 * 1、和序列化一样，准备一个help数组用于存放节点
 * 2、先把头结点压入help，从help里面弹出一个节点node，从res里面弹出一个元素，这个元素就是node节点的左孩子，如果左孩子的值是"#"，就给node的左孩子挂null，否则将值封装为节点挂载node的右指针上，
 * 同时，如果孩子节点的值不是空就将孩子节点放入help中
 * 3、当help数组为空时结束，整棵树就建立好了
 * 课程：体系班课时92-93
 */
public class BinaryTreeByLevelSerialization {
    public static Queue<String> serializeBinaryByLevel(TreeNode<String> head) {
        Queue<String> res = new LinkedList<>();
        /*
         如果入参传入的head就是空，那么返回的结果队列中只有一个元素"#"，其实不这么做也可以，返回一个空队列或者返回null都可以，
         这里要说的是序列化怎么处理空head，反序列化时也要以相应的方式处理空head，两个要对上
         */
        if(head == null) {
            res.add("#");
            return res;
        }

        Queue<TreeNode<String>> help = new LinkedList<>();
        res.add(head.data);// 先把头结点的值放入res中
        help.add(head);// 先把头结点放入help中
        while(!help.isEmpty()) {
            /*
            父节点弹出后亲手将自己的左孩子和右孩子放入res和help中，即父节点被弹出后的任务只是操作自己的孩子，而父节点本身是在上一次循环中它作为孩子的时候被放进去的，
            所以本次循环不要把父节点再放到队列里
             */
            TreeNode<String> treeNode = help.poll();
            // 先考察左孩子
            if(treeNode.left == null) {// 孩子为空只入res，不入help
                res.add("#");
            } else {// 孩子不为空，既入res也入help
                res.add(treeNode.left.data);
                help.add(treeNode.left);
            }

            // 再考察右孩子，逻辑和考察所孩子一样
            if(treeNode.right == null) {// 孩子为空只入res，不入help
                res.add("#");
            } else {// 孩子不为空，既入res也入help
                res.add(treeNode.right.data);
                help.add(treeNode.right);
            }
        }

        return res;
    }

    public static TreeNode<String> deserializeBinaryByLevel(Queue<String> queue) {
        if(queue == null || queue.isEmpty()) {
            return null;
        }

        // 如果queue不为空，那么第一个元素一定是整棵树的头结点
        String headValue = queue.poll();
        if("#".equals(headValue)) {// 在序列化中怎么处理的空head，在反序列化时就怎么处理空head
            return null;
        }

        Queue<TreeNode<String>> help = new LinkedList<>();
        TreeNode<String> head = new TreeNode<>(headValue);
        help.add(head);// 现将head节点放入help数组
        while(!help.isEmpty()) {
            TreeNode<String> treeNode = help.poll();
            // 先考察左孩子，再考查右孩子
            String value = queue.poll();
            treeNode.left = "#".equals(value) ?  null : new TreeNode<>(value);
            if(treeNode.left != null) {// 节点不为空时放入help
                help.add(treeNode.left);
            }
            value = queue.poll();
            treeNode.right = "#".equals(value) ?  null : new TreeNode<>(value);
            if(treeNode.right != null) {
                help.add(treeNode.right);
            }
        }

        return head;
    }

    public static void main(String[] args) {
        TreeNode<String> a = new TreeNode<>("a");
        TreeNode<String> b = new TreeNode<>("b");
        TreeNode<String> c = new TreeNode<>("c");
        TreeNode<String> d = new TreeNode<>("d");
        TreeNode<String> e = new TreeNode<>("e");
        a.left = b;
        a.right = c;
        b.right = d;
        c.left = e;

        Queue<String> res = serializeBinaryByLevel(a);
        System.out.println(res);
        System.out.println("--------------");

        TreeNode<String> head = deserializeBinaryByLevel(res);
        res = serializeBinaryByLevel(head);
        System.out.println(res);
        System.out.println("--------------");
    }
}
