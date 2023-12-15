package com.mashibing.tree;

import com.mashibing.list.SingleNode;

import java.util.Stack;

/**
 * 使用非递归的方式实现二叉树的先序、中序和后续遍历
 * 思路：使用栈
 * 课程：体系班课时87-88
 */
public class BinaryTreeWithoutRecurse {
    /**
     * 先序遍历
     * 1、准备一个栈，先把头放进去
     * 2、弹出栈顶元素并打印，然后先把弹出元素的右节点放进去，再放入左节点，这样弹出的时候顺序就是先做后右了
     * 3、等到栈空的时候，打印的顺序就是先序遍历的顺序
     * @param head
     */
    public static void pre(TreeNode<String> head) {
        if(head == null) {
            return;
        }

        Stack<TreeNode<String>> stack = new Stack<>();
        stack.push(head);// 先把头压进去再循环
        TreeNode<String> cur = null;
        while(!stack.isEmpty()) {
            cur = stack.pop();
            System.out.print(cur.data+ "\t");
            // 先压右孩子，再压左孩子，由于是栈，所以弹出来的顺序就是先左后右了
            if(cur.right != null) {
                stack.push(cur.right);
            }
            if(cur.left != null) {
                stack.push(cur.left);
            }
        }
        System.out.println();
    }

    /**
     * 后序遍历
     * 思路：
     * 一棵二叉树的遍历顺序其实是有6种的：先序、中序、后续、头右左、右头左、右左头，只不过常见的也是常用到的是前三种。
     * 由于栈先进后出的特性就导致了栈可以实现逆序的效果，后续遍历的顺序是左右头，如果能实现头右左，那么把头右左放到栈里，在从栈弹出来就是左右头的顺序了，就是后序遍历的效果。
     * 下面的关键点就是怎么实现头右左的顺序。前面已经实现了先序遍历的效果，做法是弹出一个元素后打印，然后把它的右孩子和左孩子（如果有的话）压入栈，再弹出来就是头左右的顺序了。
     * 这回相反，弹出一个元素之后先放它的左孩子再放右孩子，弹出来就是头右左的顺序，再把这个顺序放入另一个栈中，最后出来的就是左右头的顺序，就是后序遍历了。
     *
     * @param head
     */
    public static void post(TreeNode<String> head) {
        if(head == null) {
            return;
        }

        Stack<TreeNode<String>> stack1 = new Stack<>();
        Stack<TreeNode<String>> stack2 = new Stack<>();
        stack1.push(head);
        TreeNode<String> cur = null;
        while(!stack1.isEmpty()) {
            cur = stack1.pop();
            // 弹出来之后不打印，将弹出来的这个元素放入另一个栈里
            stack2.push(cur);
            // 第一个栈里面，先压cur的左孩子在压入右孩子
            if(cur.left != null) {
                stack1.push(cur.left);
            }
            if(cur.right != null) {
                stack1.push(cur.right);
            }
        }

        while(!stack2.isEmpty()) {
            System.out.print(stack2.pop().data + "\t");
        }
        System.out.println();
    }

    public static void mid(TreeNode<String> head) {
        if(head == null) {
            return;
        }

        Stack<TreeNode<String>> stack = new Stack<>();
        TreeNode<String> cur = head;
        while(cur != null || !stack.isEmpty()) {// 当cur和栈都为空的时候退出循环，整个过程结束
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.print(cur.data + "\t");
                cur = cur.right;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TreeNode<String> a = new TreeNode<>("a");
        TreeNode<String> b = new TreeNode<>("b");
        TreeNode<String> c = new TreeNode<>("c");
        TreeNode<String> d = new TreeNode<>("d");
        TreeNode<String> e = new TreeNode<>("e");
        TreeNode<String> f = new TreeNode<>("f");
        TreeNode<String> g = new TreeNode<>("g");
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        f.right = g;
        System.out.println("先序遍历：");
        System.out.println("非递归实现：");
        pre(a);
        System.out.println("递归实现：");
        BinaryTree.pre(a);
        System.out.println("------------");

        System.out.println("后序遍历：");
        System.out.println("非递归实现：");
        post(a);
        System.out.println("递归实现：");
        BinaryTree.post(a);
        System.out.println("------------");

        System.out.println("中序遍历：");
        System.out.println("非递归实现：");
        mid(a);
        System.out.println("递归实现：");
        BinaryTree.mid(a);
        System.out.println("------------");
    }
}
