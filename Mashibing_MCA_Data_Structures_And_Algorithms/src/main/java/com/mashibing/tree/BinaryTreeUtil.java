package com.mashibing.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 二叉树工具类
 */
public class BinaryTreeUtil {
    private static Map<TreeNode, Integer> map = new HashMap<>();
    private static List<TreeNode> mid = new ArrayList<>();

    /**
     * 以直观的形式打印二叉树
     *
     * @param root
     * @param <T>
     */
    public static <T> void printBinaryTree(TreeNode<T> root) {
        init(root);
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        treePrint(nodes);
    }

    private static void midOrder(TreeNode node) {
        if (node == null) return;
        midOrder(node.left);
        mid.add(node);
        midOrder(node.right);
    }

    private static <T> void init(TreeNode<T> root) {
        if (root == null) return;
        midOrder(root);
        for (int i = 0; i < mid.size(); i++) {
            map.put(mid.get(i), i);
        }
    }

    private static void treePrint(List<TreeNode> nodes) {
        if (nodes.isEmpty()) return;
        // nodes : 同一层节点
        printLevel(nodes);//打印同一层的节点
        List<TreeNode> children = new ArrayList<>();
        //顺序遍历下一层节点;
        for (TreeNode node : nodes) {
            if (node.left != null) children.add(node.left);
            if (node.right != null) children.add(node.right);
        }
        treePrint(children);//递归打印下一层节点
    }

    private static void printLevel(List<TreeNode> nodes) {
        String VLine = "";
        String dataLine = "";
        String line = "";
        int lastNodeIndex = 0;
        int lastRightIndex = 0;
        for (TreeNode node : nodes) {
            int x = map.get(node);
            String addEmpty = getEmpty(x - lastNodeIndex);
            lastNodeIndex = x;
            VLine += addEmpty + "|";//竖线拼接
            //数字拼接
            dataLine += addEmpty + node.value;
            //红黑树可以用下面打印语句，打印红色；
            //if(node.red)
            //    dataLine+= addEmpty +"\033[91;1m"+node.data+"\033[0m";//打印红色
            //else
            //    dataLine += addEmpty+node.data;
            TreeNode left = node.left;
            TreeNode right = node.right;
            String leftLine = null;
            String rightLine = null;
            int leftIndex = -1;
            int rightIndex = -1;
            if (left != null) {
                leftIndex = map.get(left);
                leftLine = getLineToSon(x - leftIndex);
            }
            if (right != null) {
                rightIndex = map.get(right);
                rightLine = getLineToSon(rightIndex - x);
            }
            String curLine = (leftLine == null ? "" : leftLine) + "|" + (rightLine == null ? "" : rightLine);
            if (leftLine == null && rightLine == null) curLine = "";
            //线段之间的间隔
            int dif = (leftIndex == -1 ? x : leftIndex) - lastRightIndex;
            String difEmpty = getEmpty(dif);
            line += difEmpty + curLine;//拼接线段
            lastRightIndex = rightIndex == -1 ? x : rightIndex;
        }
        System.out.println(VLine + "\n" + dataLine + "\n" + line);
    }

    private static String getEmpty(int x) {
        String empty = "";
        for (int i = 0; i < x; i++) {
            empty += "\t";
        }
        return empty;
    }

    /**
     * 链接子线段的长度
     *
     * @param end
     * @return
     */
    private static String getLineToSon(int end) {
        String line = "";
        if (end == 0) return line;
        for (int i = 0; i < end; i++) {
            line += "____";
        }
        return line;
    }
}
