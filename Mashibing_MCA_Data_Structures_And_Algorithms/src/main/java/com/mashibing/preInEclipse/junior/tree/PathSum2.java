package com.mashibing.preInEclipse.junior.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 收集达标路径和，是PathSum.java的升级版
 * 题目：给定一棵树和一个sum值，收集这棵树能达到轮径和sum的所有路径并返回一个列表。
 * 解释：这道题比PathSum.java升级的地方在于PathSum.java只需要判断有没有这样的一条完整路径即可，而本题要求将所有符合条件的路径收集起来返回
 * 
 * 
 * 思路：在上一道题的基础上设置一个列表就可以了
 * 
 * 
 * @author zhangzhiwang
 * @date 2022年2月9日 上午10:23:08
 */
public class PathSum2 {
	public static void main(String[] args) {
		/**
		 * 构造一棵树：
		 * 		        6
				4		     9
			 2      5            
		   1   3
		 */
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(8);
		TreeNode node9 = new TreeNode(9);
		node6.left = node4;
		node6.right = node9;
		node4.left = node2;
		node4.right = node5;
		node2.left = node1;
		node2.right = node3;

		List<List<TreeNode>> result = getAllPathSum(node6, 15);
		System.out.println(result);
	}

	private static List<List<TreeNode>> getAllPathSum(TreeNode head, int sum) {
		List<List<TreeNode>> resultList = new ArrayList<List<TreeNode>>();
		if (head == null) {
			return resultList;
		}
		
		f(head, 0, sum, new ArrayList<TreeNode>(), resultList);
		return resultList;
	}
	
	/**
	 * 
	 * 
	 * @param head
	 * @param preSum
	 * @param sum
	 * @param path 访问到当前节点时走过的路径
	 * @param resultList 题目最终返回的大列表
	 * @author zhangzhiwang
	 * @date 2022年2月9日 下午12:00:33
	 */
	private static void f(TreeNode head, int preSum, int sum, List<TreeNode> path, List<List<TreeNode>> resultList) {
		// 叶子节点
		if (head.left == null && head.right == null) {
			if (preSum + head.value == sum) {
				path.add(head);
				resultList.add(copy(path));// 注意这里可不能将path直接放到resultList，因为还要往上一层返，path可能还要加其它节点，path和resultList必须指向不同的list对象，否则改一个全改了
				/**
				 * 注意再返回上一层节点前得把本层的节点移除掉，因为path是访问到当前节点时走过的路径。
				 * 前面path.add(head)是为了将path放入到resultList里面（当然是拷贝之后），再返回上一层之前还得把当前节点移出去
				 */
				path.remove(head);
				return;
			}
		}
		
		// 非叶子节点
		preSum += head.value;
		path.add(head);
		if(head.left != null) {
			f(head.left, preSum, sum, path, resultList);
		}
		if(head.right != null) {
			f(head.right, preSum, sum, path, resultList);
		}
		
		// 最后在返回上一层节点前把自己从路径中移出去。注意：当本方法运行到最后一行返回不代表整个过程结束而只是本节点结束，方法运行结束会返回到上一个节点时代卡主的那行代码，所以必须要将本节点移除。
		path.remove(head);
	}

	private static List<TreeNode> copy(List<TreeNode> src) {
		List<TreeNode> dest = new ArrayList<TreeNode>();
		for (TreeNode node : src) {
			dest.add(node);
		}
		return dest;
	}
}
