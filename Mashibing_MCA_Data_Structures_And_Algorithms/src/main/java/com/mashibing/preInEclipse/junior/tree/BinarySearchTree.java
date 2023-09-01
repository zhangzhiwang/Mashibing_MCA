package com.mashibing.preInEclipse.junior.tree;

/**
 * 搜索二叉树（BinarySearchTree，BST）
 * 解释：如果一棵树的某一节点，如果它有左子树那么它左子树的任意节点都要小于当前节点，如果它有右子树那么它右子树的任意节点都要大于当前节点，当这棵树的任意节点都满足这个条件时这棵树就是BST。
 * 注意：BST里面不允许有相同的值。
 * 下面这棵树就不是BST：
 *  				  6
	   			 3		   7
  			  2     5        9
            8
 * 虽然2 < 3 < 5，但是2的子树比3大了，不满足定义中“子树的任意节点”。
 * 
 * 题目：判断一棵树是否为BST？
 * 思路：
 * 方法1：如果一棵树的中序遍历结果是从小到大有序的，那么这棵树就是BST。因为中序遍历结果就是把一棵立体的树给拉平，把所有节点都落在同一水平面上：
 *  				   6
	   			 3	   |	7
  			  2  |   5 |    |    9
            1 |  |   | |    |    |
            | |  |   | |    |    |
            ——————————————————————————
    拉平：   1 2  3   5 6    7    9
    中序遍历结果：[1,2,3,5,6,7,9]
    
 * 方法2：判断一棵树整体是不是BST，就判断任意一个节点：
 * 1、它的左子树和右子树都是BST
 * 2、左子树的最大值小于当前节点的值
 * 3、右子树的最小值大于当前节点的值
 * 当任意节点都满足以上三个条件时整棵树就是BST。
 * 注意：当某一个节点满足以上三个条件时只能说明以这个节点为头的树是BST，如果该节点上面还有节点，为了给上层节点提供判断依据，必须给上层节点反馈本棵树中最小值和最大值，
 * 因为BST的定义中有“任意”两个字。
 * 
 * @author zhangzhiwang
 * @date 2022年2月8日 下午7:03:39
 */
public class BinarySearchTree {
	public static void main(String[] args) {
		/**
		 * 构造一棵树：
		 * 		        6
				4		     7
			 2      5            8
		   1   3
		 */
		TreeNode node1 = new  TreeNode(1);
		TreeNode node2 = new  TreeNode(2);
		TreeNode node3 = new  TreeNode(3);
		TreeNode node4 = new  TreeNode(4);
		TreeNode node5 = new  TreeNode(5);
		TreeNode node6 = new  TreeNode(6);
		TreeNode node7 = new  TreeNode(7);
		TreeNode node8 = new  TreeNode(8);
		node6.left = node4;
		node6.right = node7;
		node4.left = node2;
		node4.right = node5;
		node7.right = node8;
		node2.left = node1;
		node2.right = node3;
		
		boolean result = isBST(node6);
		System.out.println(result);
	}

	/**
	 * 对于一棵树来说，递归算法要对树里面的所有节点都是用，就是说递归函数的返回值要对所有节点都适用，即递归函数的返回值对所有节点一视同仁。
	 * 就像本题这种情况，对于左子树返回的信息和右子树返回的信息要求不一样，那么就把所有要收集的信息全部放在一个类里面，这样才能做到统一返回值。
	 * 
	 * @author zhangzhiwang
	 * @date 2022年2月8日 下午7:28:53
	 */
	static class Info {
		private boolean isBST;
		private int maxValue;// 表示当前节点及其左右子树中最大的值
		private int minValue;// 同理

		public Info(boolean isBST, int maxValue, int minValue) {
			super();
			this.isBST = isBST;
			this.maxValue = maxValue;
			this.minValue = minValue;
		}
	}

	private static boolean isBST(TreeNode node) {
		return f(node).isBST;
	}

	private static Info f(TreeNode head) {
		/**
		 * 如果一棵树为空就认为它是BST，但是不能返回一个默认的Info对象，因为isBST可以认为定义为true，但是maxValue和minValue不好定义，
		 * 因为value可正可负，可以是任意值，索性直接返回null，由上游调用的地方去判空。
		 */
		if (head == null) {
			return null;
		}

		// 获取左右子树的Info信息
		Info leftInfo = f(head.left);// t 2 4
		Info rightInfo = f(head.right);// t 5 5 

		// 判断左右子树是不是BST，约定空树就是BST
		boolean leftIsBST = leftInfo == null ? true : leftInfo.isBST;
		boolean rightIsBST = rightInfo == null ? true : rightInfo.isBST;

		// 判断左右子树和当前节点的大小
		boolean isLeftMaxLessThanHead = leftInfo == null ? true : (leftInfo.maxValue < head.value);
		boolean isRightMinLargerThanHead = rightInfo == null ? true : (rightInfo.minValue > head.value);

		boolean isBST = leftIsBST && rightIsBST && isLeftMaxLessThanHead && isRightMinLargerThanHead;
		if (!isBST) {
			return new Info(false, 0, 0);
		}

		// 找到当前节点及其左右子树中的最小值和最大值
		int min = head.value;// 这里默认最小值和最大值要是head自己的值，不能设初始值为0，因为leftInfo和rightInfo可能为空
		int max = head.value;
		if (leftInfo != null) {
			if (leftInfo.maxValue >= head.value) {
				return new Info(false, 0, 0);
			} else {
				min = Math.min(leftInfo.minValue, min);
			}
		}

		if (rightInfo != null) {
			if (rightInfo.minValue <= head.value) {
				return new Info(false, 0, 0);
			} else {
				max = Math.max(rightInfo.maxValue, max);
			}
		}

		return new Info(isBST, min, max);
	}
}
