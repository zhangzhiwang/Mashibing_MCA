package com.mashibing.preInEclipse.junior.tree;

import com.mashibing.tree.TreeNode;
/**
 * 平衡二叉树
 * 解释：每一个节点的左子树和右子树的高度差不超过1，当所有节点的|左子树高度-右子树高度|<=1时，整个这棵树就是平衡树
 * 
 * 题目：如何判断一棵二叉树是平衡二叉树？
 * 思路：递归判断某个结点的左右子树是不是平衡二叉树，如果都是平衡二叉树那么再判断该节点的左右子树的高度差的绝对值是不是小于等于1，如果也是那么整棵树就是平衡二叉树。
 * 每次递归返回的信息要有两个：一个是本节点的高度，一个是以本节点为头的子树是不是平衡的。
 * 
 * @author zhangzhiwang
 * @date 2022年2月4日 下午3:38:30
 */
public class BalancedBinaryTree {
	public static void main(String[] args) {
//		/**
//		 * 构造一棵树：
//		 * 		      1
//	   			 2		   3
//  			  4     5        6
//            7
//              8
//		 */
//		TreeNode node1 = new TreeNode(1);
//		TreeNode node2 = new TreeNode(2);
//		TreeNode node3 = new TreeNode(3);
//		TreeNode node4 = new TreeNode(4);
//		TreeNode node5 = new TreeNode(5);
//		TreeNode node6 = new TreeNode(6);
//		TreeNode node7 = new TreeNode(7);
//		TreeNode node8 = new TreeNode(8);
//		node1.left = node2;
//		node1.right = node3;
//		node3.right = node6;
//		node2.left = node4;
//		node2.right = node5;
//		node4.left = node7;
////		node7.right = node8;
//
//		boolean isBalanced = isTreeBalanced(node1);
//		System.out.println(isBalanced);

		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			TreeNode head = generateRandomBST(maxLevel, maxValue);
			if (isBalanced1(head) != isTreeBalanced(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

	/**
	 * 之所以封装一个类Info是因为判断一棵树是否为平衡二叉树不仅要判断它的所有子树各自是否平衡，还要判断左右子树的高度差的绝对值是否小于等于1，所以子树必须返回它的高度是多少
	 * 这里规定：树的高度和深度从1开始。
	 * 
	 * @author zhangzhiwang
	 * @date 2022年2月8日 下午6:01:09
	 */
	static class Info {
		private boolean isBalanced;
		private int height;

		public Info(boolean isBalanced, int height) {
			super();
			this.isBalanced = isBalanced;
			this.height = height;
		}
	}

	private static boolean isTreeBalanced(TreeNode root) {
		return f(root).isBalanced;
	}

	private static Info f(TreeNode head) {
		if (head == null) {
			return new Info(true, 0);// 人为规定：如果一棵树是null，那么它是平衡的且高度为0
		}

		// 如果一棵树不是null，那么获取它左子树和右子树的Info信息
		Info leftInfo = f(head.left);
		Info rightInfo = f(head.right);

		// 计算当前这棵树的高度
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;

		// 计算当前这棵树是否平衡
		boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height - rightInfo.height) <= 1;

		// 封装当前这棵树head的Info信息
		return new Info(isBalanced, height);
	}

	// 以下是对数器
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

	public static boolean isBalanced1(TreeNode head) {
		boolean[] ans = new boolean[1];
		ans[0] = true;
		process1(head, ans);
		return ans[0];
	}

	public static int process1(TreeNode head, boolean[] ans) {
		if (!ans[0] || head == null) {
			return -1;
		}
		int leftHeight = process1(head.left, ans);
		int rightHeight = process1(head.right, ans);
		if (Math.abs(leftHeight - rightHeight) > 1) {
			ans[0] = false;
		}
		return Math.max(leftHeight, rightHeight) + 1;
	}
}
