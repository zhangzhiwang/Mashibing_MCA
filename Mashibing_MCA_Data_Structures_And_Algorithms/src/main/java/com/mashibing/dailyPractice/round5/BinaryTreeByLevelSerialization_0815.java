package com.mashibing.dailyPractice.round5;

import com.mashibing.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 将二叉树按层遍历的结果进行序列化和反序列化
 */
public class BinaryTreeByLevelSerialization_0815 {
    public static Queue<String> binaryTreeByLevelSerialization(TreeNode<Integer> root) {
        Queue<String> queue = new LinkedList<>();
        if(root == null) {
            return queue;
        }

        Queue<TreeNode<Integer>> help = new LinkedList<>();
        help.add(root);
        queue.add("" + root.value);
        while (!help.isEmpty()) {
            TreeNode<Integer> poll = help.poll();
            if(poll.left != null) {
                help.add(poll.left);
                queue.add("" + poll.left.value);
            } else {
                queue.add("#");
            }

            if(poll.right != null) {
                help.add(poll.right);
                queue.add("" + poll.right.value);
            } else {
                queue.add("#");
            }
        }

        return queue;
    }

    public static TreeNode<Integer> binaryTreeByLevelDeserialization(Queue<String> queue) {
        if(queue == null || queue.isEmpty()) {
            return null;
        }

        String value = queue.poll();
        TreeNode<Integer> root = new TreeNode<>(Integer.valueOf(value));
        Queue<TreeNode<Integer>> help = new LinkedList<>();
        help.add(root);
        while (!help.isEmpty()) {
            TreeNode<Integer> poll = help.poll();
            String val = queue.poll();
            if(!"#".equals(val)) {
                TreeNode<Integer> node = new TreeNode<>(Integer.valueOf(val));
                poll.left = node;
                help.add(node);
            }

            val = queue.poll();
            if(!"#".equals(val)) {
                TreeNode<Integer> node = new TreeNode<>(Integer.valueOf(val));
                poll.right = node;
                help.add(node);
            }
        }
        
        return root;
    }

    //对数器
    // for test
    public static TreeNode<Integer> generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode<Integer> generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode<Integer> head = new TreeNode<Integer>((int)Math.random() * maxValue);
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // for test
    public static boolean isSameValueStructure(TreeNode<Integer> head1, TreeNode<Integer> head2) {
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
    public static void printTree(TreeNode<Integer> head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(TreeNode<Integer> head, int height, String to, int len) {
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

    public static Queue<String> levelSerial(TreeNode<Integer> head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            Queue<TreeNode<Integer>> queue = new LinkedList<TreeNode<Integer>>();
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

    public static TreeNode<Integer> buildByLevelQueue(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        TreeNode<Integer> head = generateNode(levelList.poll());
        Queue<TreeNode<Integer>> queue = new LinkedList<TreeNode<Integer>>();
        if (head != null) {
            queue.add(head);
        }
        TreeNode<Integer> node = null;
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

    public static TreeNode<Integer> generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new TreeNode<Integer>(Integer.valueOf(val));
    }

    public static void main(String[] args) {
//        Queue<String> res = new LinkedList<>();
//        System.out.println(res.poll());

        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            TreeNode<Integer> head = generateRandomBST(maxLevel, maxValue);
            Queue<String> level = levelSerial(head);
            TreeNode<Integer> levelBuild = buildByLevelQueue(level);
            Queue<String> queue = binaryTreeByLevelSerialization(head);
            TreeNode<Integer> h = binaryTreeByLevelDeserialization(queue);
            if (!isSameValueStructure(h, levelBuild)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");
    }
}
