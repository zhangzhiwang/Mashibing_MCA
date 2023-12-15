package com.mashibing.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 将一棵二叉树的先序遍历结果序列化和反序列化
 * 思路：
 * 序列化：
 * 序列化就是将内存里面的东西写到硬盘上，比如写到文件里，这里序列化的结果可以选择输出到字符串也可以选择输出到容器里，这里不妨选择输出到队列里。
 * 使用递归的方式按照中序遍历的顺序遍历二叉树，将原来的打印改为输出到字符串中，这里需要注意的是为了占位需要把空孩子（null）也写进字符串中。
 * 这里规定：字符串中元素之间使用逗号分割，null的占位符为"#"。
 * 反序列化：
 * 用的也是递归，base case是从队列中弹出来的元素为"#"时返回null。
 * 另外，后续遍历的序列化和反序列化可以根据先序遍历的结果改出来，没有中序遍历的序列化和反序列化，应为中序遍历的序列化的结果会有歧义，不同的树序列化的结果可能是一样的，
 * 这样就没有办法反序列化了，所以中序遍历没有序列化。
 * 课程：体系班课时91-92
 */
public class BinaryTreePreSerialization {
    public static Queue<String> serializeBinaryTreePre(TreeNode<String> head) {
        if(head == null) {
            return null;
        }

        return serialize(head, new LinkedList<>());// 由于ArrayList的底层就是数组实现的，所以可以把ArrayList就看做数组
    }

    private static Queue<String> serialize(TreeNode<String> head, Queue<String> queue) {
        if(head == null) {
            queue.add("#");
            return queue;
        }

        queue.add(head.data);
        serialize(head.left, queue);
        serialize(head.right, queue);
        return queue;
    }

    public static TreeNode<String> deserializeBinaryTreePre(Queue<String> queue) {
        if(queue == null || queue.isEmpty()) {
            return null;
        }

        return deserialize(queue);
    }

    private static TreeNode<String> deserialize(Queue<String> queue) {
        String value = queue.poll();
        if("#".equals(value)) {
            return null;
        }
        TreeNode<String> head = new TreeNode<>(value);
        head.left = deserialize(queue);
        head.right = deserialize(queue);
        return head;
    }

    public static void main(String[] args) {
        TreeNode<String> a = new TreeNode<>("a");
        TreeNode<String> b = new TreeNode<>("b");
        TreeNode<String> c = new TreeNode<>("c");
        TreeNode<String> d = new TreeNode<>("d");
        TreeNode<String> e = new TreeNode<>("e");
        TreeNode<String> f = new TreeNode<>("f");
        TreeNode<String> g = new TreeNode<>("g");
        TreeNode<String> h = new TreeNode<>("h");
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        f.right = h;

        Queue<String> queue = serializeBinaryTreePre(a);
        System.out.println(queue);
        System.out.println("----------");

        TreeNode<String> head = deserialize(queue);
        System.out.println(serializeBinaryTreePre(head));
    }
}
