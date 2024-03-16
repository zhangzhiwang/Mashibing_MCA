package com.mashibing.others;

import com.mashibing.list.DoubleNode;
import com.mashibing.list.SingleNode;
import com.mashibing.tree.TreeNode;

import java.util.*;

/**
 * 对数器
 */
public class DuiShuQiUtil {
    private static Map<TreeNode, Integer> map = new HashMap<>();
    private static List<TreeNode> mid = new ArrayList<>();
    public static void main(String[] args) {
//        int[] arr = {3, 2, 1, 3, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 3, 2, 9};
//        BubbleSort.bubbleSort(arr);
//
//        if (!isAsc(arr)) {
//            SelectionSort.printArr(arr);
//        } else {
//            System.out.println("准确无误");
//        }

//        System.out.println(arrContains(arr, 10));

        int[] arr1 = createRandArr(5, 10);
        printArr(arr1);
    }

    /**
     * 创建一个随机长度和值的数组
     * @param maxLen 随机长度的最大值
     * @param maxValue 随机值的最大值
     * @return
     */
    public static int[] createRandArr(int maxLen, int maxValue) {
        int len = (int)(Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for(int i = 0; i < len; i++) {
            arr[i] = (int)(Math.random() * (maxValue + 1));
        }

        return arr;
    }

    /**
     * 判断一个数组是否是升序的
     *
     * @param arr
     * @return
     */
    public static boolean isAsc(int[] arr) {
        if (arr == null) {
            return false;
        }

        if (arr.length < 2) {
            return true;
        }

        int tmp = arr[0];// 始假最开始位置的值是最小的
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < tmp) {// 后面的数比前面的小
                return false;
            }
            tmp = arr[i];// tmp往后移
        }

        return true;
    }

    /**
     * 判断数组是否至少包含一个指定值
     *
     * @param arr
     * @param targetValue
     * @return
     */
    public static boolean arrContains(int[] arr, int targetValue) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        for (int i : arr) {
            if (i == targetValue) {
                return true;
            }
        }

        return false;
    }

    /**
     * 数组里指定值的索引
     * @param arr
     * @param targetValue 返回-1代表不包含指定值
     * @return
     */
    public static int targetValueIndex(int[] arr, int targetValue) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == targetValue) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 打印数组
     * @param arr
     */
    public static void printArr(int[] arr) {
        if(arr == null || arr.length == 0) {
            System.out.println("Param is empty!");
        }

        for(int i : arr) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }

    public static <T> void printSingleList(SingleNode<T> head) {
        if(head == null) {
            return;
        }

        Set<SingleNode<T>> set = new HashSet<>();
        SingleNode<T> cur = head;
        while(cur != null) {
            if(set.contains(cur)) {
                System.out.println();
                System.out.println("链表已成环，入环节点是：" + cur.value);
                break;
            } else {
                set.add(cur);
            }


            System.out.print(cur.value + " -> ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static <T> void printDoubleList(DoubleNode<T> head) {
        if(head == null) {
            return;
        }

        DoubleNode<T> cur = head;
        DoubleNode<T> pre = null;
        while(cur != null) {
            System.out.print(cur.value + " -> ");
            pre = cur;
            cur = cur.next;
        }
        System.out.println();

        while(pre != null) {
            System.out.print(pre.value + " => ");
            pre = pre.last;
        }
        System.out.println();
    }

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
