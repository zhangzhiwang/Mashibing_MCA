package com.mashibing.dailyPractice.round1._2_16;

import com.mashibing.tree.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 打印一个字符串的全部子序列（子序列的字符顺序不能颠倒），要求打印的子序列不能重复
 */
public class PrintAllSubsequences2_0216 {
    public static void printAllSubsequences2(String str) {
        if(str == null || str.length() == 0) {
            return;
        }

        recurse(str.toCharArray(), 0, "", new HashSet<String>());
    }

    private static void recurse(char[] charArr, int index, String path, Set<String> set) {
        if(index == charArr.length) {
            if(!set.contains(path)) {
                System.out.println(path);
                set.add(path);
            }
            return;
        }

        recurse(charArr, index + 1, path + charArr[index], set);
        recurse(charArr, index + 1, path, set);
    }

    public static void main(String[] args) {
        String str = "ccc";
        printAllSubsequences2(str);
    }

    /**
     * 二叉树按层遍历
     */
    public static class BinaryTreeByLevel_0217 {
        public static void binaryTreeByLevel(TreeNode<Integer> head) {
            if(head == null) {
                return;
            }

            Queue<TreeNode<Integer>> queue = new LinkedList<>();
            queue.add(head);
            while(!queue.isEmpty()) {
                TreeNode<Integer> node = queue.poll();
                System.out.println(node.value);
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        public static void main(String[] args) {
            TreeNode<Integer> n1 = new TreeNode<>(1);
            TreeNode<Integer> n2 = new TreeNode<>(2);
            TreeNode<Integer> n3 = new TreeNode<>(3);
            TreeNode<Integer> n4 = new TreeNode<>(4);
            TreeNode<Integer> n5 = new TreeNode<>(5);
            n1.left = n2;
            n1.right = n3;
            n2.right = n4;
            n3.left = n5;

            binaryTreeByLevel(n1);
        }
    }
}
