package com.mashibing.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 求一棵二叉树的最大宽度
 * 思路：
 * 解释：二叉树某一层的宽度就是该层的节点数，求二叉树的最大宽度就是找二叉树哪一层的节点数最多。
 * 1、准备一个队列，4个临时变量：当前层节点的个数curLevelNodeCount、每一层节点数的最大值maxLevelNodeCount、当前层的结束节点curLevelEnd、下一层的结束节点nextLevelEnd。
 * 2、从头结点开始按层遍历二叉树，不由分说先把头结点放入队列中，curLevelNodeCount加1，curLevelEnd=head，然后循环从队列中弹出元素。
 * 3、在队列中每弹出一个元素，就看看它有没有左孩子，如果有将左孩子加入队列中，并将nextLevelEnd更新为左孩子；如果有右孩子就将右孩子加入队列中，然后更新nextLevelEnd为右孩子。
 * 4、每弹出一个元素都要看这个元素是不是和curLevelEnd相等，如果是说明弹出的这个元素是本层的最后一个元素，本层的元素弹完之后，比较curLevelNodeCount和maxLevelNodeCount的值，并更新maxLevelNodeCount，
 * 将nextLevelEnd更新到curLevelEnd中，nextLevelEnd清空，下次再弹出元素就是下一层的第一个元素了，以此类推，直到队列弹空为止。
 *
 * 课程：体系班课时96
 */
public class TreeMaxWidth {
    public static int treeMaxWidth(TreeNode<String> head) {
        if(head == null) {
            return 0;
        }

        Queue<TreeNode<String>> queue = new LinkedList<>();
        queue.add(head);// 先把头结点加进来
        int curLevelNodeCount = 0;// 当前层的节点数
        int maxLevelNodeCount = 0;// 所有层当中节点数最大的值
        TreeNode<String> curLevelEnd = head;// 当前层的结束节点，由于上面已经把头结点加进来了，所以初始值就是head节点
        TreeNode<String> nextLevelEnd = null;// 下一层的结束节点
        while(!queue.isEmpty()) {
            TreeNode<String> treeNode = queue.poll();
            curLevelNodeCount++;
            if(treeNode.left != null) {
                queue.add(treeNode.left);
                nextLevelEnd = treeNode.left;
            }
            if(treeNode.right != null) {
                queue.add(treeNode.right);
                nextLevelEnd = treeNode.right;
            }

            if(treeNode == curLevelEnd) {// 说明弹出来的这个节点是本层的最后一个节点
                maxLevelNodeCount = Math.max(curLevelNodeCount, maxLevelNodeCount);
                curLevelNodeCount = 0;// 将当前层数的节点数清零，因为从本节点之后如果再弹出来就是下一层的节点了
                curLevelEnd = nextLevelEnd;
                nextLevelEnd = null;
            }
        }

        return maxLevelNodeCount;
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
        TreeNode<String> i = new TreeNode<>("i");
        a.left = b;
        a.right = c;
        b.right = d;
        d.left = f;
        d.right = g;
        g.left = i;
        c.left = e;
        e.right = h;

        BinaryTreeUtil.printBinaryTree(a);
        int maxWidth = treeMaxWidth(a);
        System.out.println("maxWidth = " + maxWidth);
    }
}
