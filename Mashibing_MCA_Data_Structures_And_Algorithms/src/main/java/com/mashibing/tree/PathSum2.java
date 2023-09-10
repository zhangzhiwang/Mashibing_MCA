package com.mashibing.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 收集达标路径和
 * 题目：给定一棵树和一个sum值，收集这棵树能达到路径和sum的所有路径节点并返回一个列表List<List<Integer>>
 * 课程：新手班课时52
 * 思路：见com.mashibing.preInEclipse.junior.tree.PathSum2注释
 */
public class PathSum2 {
    public static void main(String[] args) {
        TreeNode<Integer> n1 = new TreeNode<>(1);
        n1.left = new TreeNode<>(2);
        n1.right = new TreeNode<>(3);

        n1.left.left = new TreeNode<>(9);
        n1.left.right = new TreeNode<>(8);
        n1.left.right.right = new TreeNode<>(1);

        n1.right.left = new TreeNode<>(6);
        n1.right.left.left = new TreeNode<>(2);
        n1.right.right = new TreeNode<>(7);
        n1.right.right.left = new TreeNode<>(1);

        List<List<Integer>> list = f(n1, 12);
        System.out.println(list);
    }

    public static List<List<Integer>> f(TreeNode<Integer> head, int targetSum) {
        List<List<Integer>> collectList = new ArrayList<>();
        if(head == null) {
            return null;
        }

        collectPathSum(head, new ArrayList<>(), 0, targetSum, collectList);

        return collectList;
    }

    public static void collectPathSum(TreeNode<Integer> head, List<Integer> preNodeList, int preSum, int targetSum, List<List<Integer>> collectList) {
        // base case是判断叶子节点
        if(head.left == null && head.right == null) {
            if(preSum + head.data == targetSum) {
                preNodeList.add(head.data);
                List<Integer> copyList = copyList(preNodeList);// 拷贝一个列表出来，将拷贝后的列表加入到collectList中，目的是为了下面移除元素
                collectList.add(copyList);
                preNodeList.remove(preNodeList.size() - 1);// preNodeList必须将刚才添加的元素移除掉，因为这个preNodeList是要往上层返回的，返回到上层时不应该有本叶子节点
            }
            return;
        }

        // 不是叶子节点，将当前节点的值加入到preNodeList中，同时加进preSum里
        preNodeList.add(head.data);
        preSum += head.data;

        if(head.left != null) {// 如果有左子树就往左子树传递
            collectPathSum(head.left, preNodeList, preSum, targetSum, collectList);
        }
        if(head.right != null) {// 如果有右子树就往右子树传递
            collectPathSum(head.right, preNodeList, preSum, targetSum, collectList);
        }

        preNodeList.remove(preNodeList.size() - 1);// 同样要把刚才加入的本节点移除掉，因为方法运行完了要返回上一层
    }

    public static List<Integer> copyList(List<Integer> srcList) {
        List<Integer> destList = new ArrayList<>();
        if(srcList == null || srcList.size() == 0) {
            return destList;
        }

        for(Integer i : srcList) {
            destList.add(i);
        }

        return destList;
    }
}
