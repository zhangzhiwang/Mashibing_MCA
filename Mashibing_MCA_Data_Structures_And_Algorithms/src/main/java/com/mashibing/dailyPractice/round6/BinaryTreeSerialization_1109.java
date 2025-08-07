package com.mashibing.dailyPractice.round6;

import com.mashibing.others.DuiShuQiUtil;
import com.mashibing.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 将二叉树的先序遍历结果进行序列化和反序列化（输出到队列中）
 */
public class BinaryTreeSerialization_1109 {
    public static Queue<String> binaryTreeSerialization(TreeNode<Integer> root) {
        Queue<String> res = new LinkedList<>();
        if(root == null) {
            return res;
        }

        serialize(root, res);
        return res;
    }

    public static TreeNode<Integer> binaryTreeDeserialization(Queue<String> queue) {
        if(queue == null || queue.isEmpty()) {
            return null;
        }

        return deserialize(queue);
    }

    private static void serialize(TreeNode<Integer> root, Queue<String> queue) {
        if(root == null) {
            queue.add("#");
            return;
        }

        queue.add(String.valueOf(root.value));
        serialize(root.left, queue);
        serialize(root.right, queue);
    }
    
    private static TreeNode<Integer> deserialize(Queue<String> queue) {
        if(queue.isEmpty()) {
            return null;
        }

        String poll = queue.poll();
        if("#".equals(poll)) {
            return null;
        }

        TreeNode<Integer> node = new TreeNode<>(Integer.valueOf(poll));
        node.left = deserialize(queue);
        node.right = deserialize(queue);

        return node;
    }

    // 对数器
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

    public static Queue<String> preSerial(TreeNode<Integer> head) {
        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }

    public static void pres(TreeNode<Integer> head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            pres(head.left, ans);
            pres(head.right, ans);
        }
    }

    public static TreeNode<Integer> buildByPreQueue(Queue<String> prelist) {
        if (prelist == null || prelist.size() == 0) {
            return null;
        }
        return preb(prelist);
    }

    public static TreeNode<Integer> preb(Queue<String> prelist) {
        String value = prelist.poll();
        if (value == null) {
            return null;
        }
        TreeNode<Integer> head = new TreeNode<Integer>(Integer.valueOf(value));
        head.left = preb(prelist);
        head.right = preb(prelist);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            TreeNode<Integer> head = generateRandomBST(maxLevel, maxValue);
            Queue<String> queue = binaryTreeSerialization(head);
            TreeNode<Integer> h = binaryTreeDeserialization(queue);

            Queue<String> pre = preSerial(head);
            TreeNode<Integer> preBuild = buildByPreQueue(pre);
            if (!isSameValueStructure(preBuild, h)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");
    }
}
