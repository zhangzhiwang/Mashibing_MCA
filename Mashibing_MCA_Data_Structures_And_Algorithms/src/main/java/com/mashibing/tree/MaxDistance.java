package com.mashibing.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 题目：给定一颗二叉树head，任意两个节点之间都会有距离，每经过一个节点距离加1，最短的距离是节点自己和自己的距离，距离是1，每个节点和其相邻节点的距离是2，求该树的最大距离
 * 思路：
 * 在二叉树上任意找一个节点x，节点x就代表以x为头的子树，出现最大距离的可能性有2种情况：
 * 1、情况一：以x为头的子树的最大距离不经过x，最大距离出现在x左子树或者右子树里面。也就是说最大距离的起点和终点不会越过x，不会从x的左边到x的右边或者从x的右边到x的左边。
 * 2、情况二：以x为头的子树的最大距离经过x，也就是起点和终点会分布在x的两侧。
 * 对于第一种情况，就求出x左子树的最大距离或者右子树的最大距离，对于第二种情况，就求出左子树的高度+右子树的高度然后再加1，加的这个1就是x节点本身。
 * 使用递归思想，x管它的左子树要两个信息：一个是左子树的最大距离，一个是左子树的高度，右子树同理。
 * 递归通用思想：
 * 1、在二叉树上随意找一个节点x，x可以是整棵树的头结点，也可以是树中的任意一个节点
 * 2、要想达到最终的目的，x需要向它的左子树收集什么信息，向它的右子树收集什么信息，都收集上来之后自己要做什么处理才能达到最终的目的。
 * 3、递归的思想是对树中所有的节点都一视同仁，即递归的算法设计出来要是用于树中的任意一个节点，无论该节点是整棵树的头结点还是叶子节点。
 * x向左子树收集上来的信息组成一个集合A，向右子树收集上来的信息组成一个集合B，收集完信息后x自己还要经过一些的加工，需要的信息组成集合C，
 * 那么递归函数的返回值要是A并B并C的全集，这样这个递归就适合所有节点了，不能对某些节点做定制返回。以上就是递归的通用思想。
 * 代码实现模板：
 * 1、先写一个算法的主函数，返回题目要求的东西
 * 2、定义一个类作为递归的返回值类型，类里面的东西就是x向左右子树要的东西的并集
 * 3、定义一个递归函数，入参是x节点，出参是上面定义的类
 * （1）定义好base case，如果base case返回什么不好定义的话就直接返回null，让上游去判空
 * （2）让x的左孩子去调用递归函数收集左子树的信息，然后让x的右孩子去调用递归函数收集右子树的信息
 * （3）x收集完自己的左右子树的信息之后，自己作为头结点的子树也要返回那些信息，所以要加工处理。
 * 至于怎么加工处理得到那些信息，就要看分哪些情况，这些情况一般分为两类：一类是有x节点参与的情况，一类是没有x节点参与的情况。
 * （4）最终递归函数返回那些信息，让更上一级去接收。
 * 4、算法主函数效用递归函数
 * 求二叉树深度的问题都可以使用递归的通用思想。
 *
 * 课程：体系班课时102
 */
public class MaxDistance {
    static class MaxDistanceInfo {
        public int maxDistance;// 子树的最远距离，适用于情况一
        public int height;// 子树的高度，适用于情况二

        public MaxDistanceInfo(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }


    public static int getMaxDistance(TreeNode<Integer> head) {
        if(head == null) {
            return 0;
        }
        return recurse(head).maxDistance;
    }

    /**
     *
     * @param x 指定任意一个节点x
     * @return
     */
    public static MaxDistanceInfo recurse(TreeNode<Integer> x) {
        /*
         base case
         在base case中，如果base case返回null，那么上游必须要判空；如果base case不返回null，那么上游就不用判空
         */
        if(x == null) {
            return new MaxDistanceInfo(0, 0);
        }

        // 从左子树收集信息
        MaxDistanceInfo leftInfo = recurse(x.left);
        // 从右子树收集信息
        MaxDistanceInfo rightInfo = recurse(x.right);

        // 收集完左右子树的信息后x自己加工需要的信息
        // 以x为头的子树的高度：x左子树的高度和右子树的高度的最大值再加1，加的这个1就是x自己本身。
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;// 左右子树收集上来的信息不用判空，因为base case没有返回空

        /*
         以x为头的子树的最大距离有三种情况：
         1、在最大距离不经过x的情况下，最大距离出现在x的左子树中，即x左子树的最大距离
         2、在最大距离不经过x的情况下，最大距离出现在x的右子树中，即x右子树的最大距离
         3、在最大距离经过x的情况下，最大距离是：左子树的高度+右子树的高度+1
         最终以x为头的子树的最大距离是以上三种情况的最大值
         */
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance), leftInfo.height + rightInfo.height + 1);
        return new MaxDistanceInfo(maxDistance, height);
    }

    // 对数器
    // for test
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static int maxDistance1(TreeNode head) {
        if (head == null) {
            return 0;
        }
        ArrayList<TreeNode> arr = getPrelist(head);
        HashMap<TreeNode, TreeNode> parentMap = getParentMap(head);
        int max = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i; j < arr.size(); j++) {
                max = Math.max(max, distance(parentMap, arr.get(i), arr.get(j)));
            }
        }
        return max;
    }

    public static HashMap<TreeNode, TreeNode> getParentMap(TreeNode head) {
        HashMap<TreeNode, TreeNode> map = new HashMap<>();
        map.put(head, null);
        fillParentMap(head, map);
        return map;
    }

    public static void fillParentMap(TreeNode head, HashMap<TreeNode, TreeNode> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }

    public static ArrayList<TreeNode> getPrelist(TreeNode head) {
        ArrayList<TreeNode> arr = new ArrayList<>();
        fillPrelist(head, arr);
        return arr;
    }

    public static void fillPrelist(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static int distance(HashMap<TreeNode, TreeNode> parentMap, TreeNode o1, TreeNode o2) {
        HashSet<TreeNode> o1Set = new HashSet<>();
        TreeNode cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        TreeNode lowestAncestor = cur;
        cur = o1;
        int distance1 = 1;
        while (cur != lowestAncestor) {
            cur = parentMap.get(cur);
            distance1++;
        }
        cur = o2;
        int distance2 = 1;
        while (cur != lowestAncestor) {
            cur = parentMap.get(cur);
            distance2++;
        }
        return distance1 + distance2 - 1;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode<Integer> head = generateRandomBST(maxLevel, maxValue);
            if (maxDistance1(head) != getMaxDistance(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}