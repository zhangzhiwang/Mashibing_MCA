package com.mashibing.preInEclipse.junior.tree;

/**
 * 判断达标路径和
 * 题目：给定一棵树和一个sum值，判断这棵树是否至少存在一个完整路径（从头到节点到叶子节点），该路径和等于sum。
 * 解释：
 * 1、完整路径：从头结点出发到叶节点结束这是一个完整的路径，一棵树有多少个叶节点就有多少个完整路径
 * 2、路径和：一条完整路径上所有节点的和就是这条路径的路径和
 * 
 * 思路：设置一个全局静态变量flag，当到达叶节点之后判断路径和是否和sum相等，如果相等置flag为true。如果不是叶子节就将路径和往下级不是null的节点传递。
 *
 * @author zhangzhiwang
 * @date 2022年2月9日 上午10:23:08
 */
public class PathSum {
	private static boolean flag = false;
	
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
		TreeNode node9 = new  TreeNode(9);
		node6.left = node4;
		node6.right = node7;
		node4.left = node2;
		node4.right = node9;
		node7.right = node8;
		node2.left = node1;
		node2.right = node1;
		
		boolean result = hasPathSum(node6, 15);
		System.out.println(result);
	}
	
	private static boolean hasPathSum(TreeNode head, int sum) {
		if(head == null) {// 题目给出的已知条件：如果head为空则返回false
			return false;
		}
		
		flag = false;// 还原flag
		f(head, 0, sum);
		return flag;
	}
	
	/**
	 * 
	 * 
	 * @param head
	 * @param preSum 之前所有节点的路径和
	 * @param sum 题目要求的路径和
	 * @author zhangzhiwang
	 * @date 2022年2月9日 上午10:55:27
	 */
	private static void f(TreeNode head, int preSum, int sum) {
		if(flag) {
			return;
		}
		// 判断叶节点
		if (head.left == null && head.right == null) {
			flag =  (preSum  + head.value) == sum;
			return;
		}
		
		// 非叶节点
		preSum += head.value;
		
		if(head.left != null) {// 说明当前节点有左节点
			f(head.left, preSum, sum);
		}
		
		if(head.right != null) {// 说明当前节点有右节点
			f(head.right, preSum, sum);
		}
	}
}
