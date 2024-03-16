package com.mashibing.dailyPractice.round1._3_15;

import com.mashibing.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 按层遍历二叉树的序列化和反序列化
 */
public class BinaryTreeByLevelSerialization_0315 {
    public static Queue<String> binaryTreeByLevelSerialization(TreeNode<String> head) {
        if(head == null) {
            return null;
        }

        Queue<String> res = new LinkedList<>();
        Queue<TreeNode<String>> help = new LinkedList<>();
        res.add(head.value);
        help.add(head);
        while(!help.isEmpty()) {
            TreeNode<String> poll = help.poll();
            if(poll.left == null) {
                res.add("#");
            } else {
                res.add(poll.left.value);
                help.add(poll.left);
            }

            if(poll.right == null) {
                res.add("#");
            } else {
                res.add(poll.right.value);
                help.add(poll.right);
            }
        }

        return res;
    }

    public static TreeNode<String> binaryTreeByLevelDeserialization(Queue<String> res) {
        if(res == null || res.isEmpty()) {
            return null;
        }

        String headValue = res.poll();
        if("#".equals(headValue)) {
            return null;
        }

        TreeNode<String> head = new TreeNode<>(headValue);
        Queue<TreeNode<String>> help = new LinkedList<>();
        help.add(head);
        while(!help.isEmpty()) {
            TreeNode<String> poll = help.poll();
            String leftValue = res.poll();
            if(leftValue == null || "#".equals(leftValue)) {
                poll.left = null;
            } else {
                TreeNode<String> node = new TreeNode<>(leftValue);
                poll.left = node;
                help.add(node);
            }

            String rightValue = res.poll();
            if(rightValue == null || "#".equals(rightValue)) {
                poll.right = null;
            } else {
                TreeNode<String> node = new TreeNode<>(rightValue);
                poll.right = node;
                help.add(node);
            }
        }

        return head;
    }

    //对数器
    // for test
    public static TreeNode<String> generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode<String> generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode<String> head = new TreeNode<String>(String.valueOf (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // for test
    public static boolean isSameValueStructure(TreeNode<String> head1, TreeNode<String> head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    // for test
    public static void printTree(TreeNode<String> head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(TreeNode<String> head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static Queue<String> levelSerial(TreeNode<String> head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            Queue<TreeNode<String>> queue = new LinkedList<TreeNode<String>>();
            queue.add(head);
            while (!queue.isEmpty()) {
                head = queue.poll(); // head 父   子
                if (head.left != null) {
                    ans.add(String.valueOf(head.left.value));
                    queue.add(head.left);
                } else {
                    ans.add(null);
                }
                if (head.right != null) {
                    ans.add(String.valueOf(head.right.value));
                    queue.add(head.right);
                } else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    public static TreeNode<String> buildByLevelQueue(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        TreeNode<String> head = generateNode(levelList.poll());
        Queue<TreeNode<String>> queue = new LinkedList<TreeNode<String>>();
        if (head != null) {
            queue.add(head);
        }
        TreeNode<String> node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return head;
    }

    public static TreeNode<String> generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new TreeNode<String>(String.valueOf(val));
    }

    public static void main(String[] args) {
//        Queue<String> res = new LinkedList<>();
//        System.out.println(res.poll());

        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            TreeNode<String> head = generateRandomBST(maxLevel, maxValue);
            Queue<String> level = levelSerial(head);
            TreeNode<String> levelBuild = buildByLevelQueue(level);
            Queue<String> queue = binaryTreeByLevelSerialization(head);
            TreeNode<String> h = binaryTreeByLevelDeserialization(queue);
            if (!isSameValueStructure(h, levelBuild)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");
    }
}
