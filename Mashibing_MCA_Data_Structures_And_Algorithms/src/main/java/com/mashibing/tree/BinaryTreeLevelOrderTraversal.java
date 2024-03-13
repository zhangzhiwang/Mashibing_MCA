package com.mashibing.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 按从低往高（叶子节点到根节点）的顺序收集每一层的所有节点
 * 课程：新手班课时48
 * 思路：见com.mashibing.preInEclipse.junior.tree.BinaryTreeLevelOrderTraversal注释
 */
public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode<Integer> n1 = new TreeNode<>(1);
        n1.left = new TreeNode<>(2);
        n1.right = new TreeNode<>(3);

        n1.left.left = new TreeNode<>(4);
//        n1.left.right = new TreeNode<>(5);
        n1.right.left = new TreeNode<>(6);
//        n1.right.right = new TreeNode<>(7);

        List<List<Integer>> list = collectNodesFromLeafToRoot(n1);
        System.out.println(list);
    }

    public static List<List<Integer>> collectNodesFromLeafToRoot(TreeNode<Integer> head) {
        List<List<Integer>> retList = new LinkedList<>();// 注意这里要用LinkedList而不是ArrayList，因为要往队头插入，注意时间复杂度
        if(head == null) {
            return retList;
        }

        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(head);
        while(!queue.isEmpty()) {// 每一次循环结束之后queue里面存放的就是下一层的所有节点
            int size = queue.size();// size就是某一层节点的个数，注意这里必须先把size给记下来一共后面的for循环用
            List<Integer> nodeList = new ArrayList<>();
            for(int i = 0; i < size; i++) {// 注意：这里不能写成i < queue.size()，因为每弹出一个节点size就会变小
                TreeNode<Integer> poll = queue.poll();
                nodeList.add(poll.value);
                if(poll.left != null) {
                    queue.add(poll.left);
                }
                if(poll.right != null) {
                    queue.add(poll.right);
                }
            }
            retList.add(0, nodeList);
        }

        return retList;
    }
}
