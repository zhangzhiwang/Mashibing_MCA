package com.mashibing.preInEclipse.junior.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 按从低往高的顺序收集每一层的所有节点
 * 题目：给定一个二叉树，要求收集每一层的所有节点并组成一个List，将收集到的所有层的list组成一个总体的List，要求这个总体的List按照数的底层向高层排好序。
 * 比如：一个数：
 * 	   1
	2 	  3
  4   5  6  7
 * 要求最终输出：[[4,5,6,7],[2,3],[1]]，最终的列表里面各个小列表的顺序是按照数的低层向高层排序的
 * 
 * 思路：
 * 1、先不考虑最终List的顺序，将每一层的节点放入到一个队列里面，从尾部放入从头部弹出（可以使用Queue实现）。
 *    将该层所有节点都放入到queue的尾部之后，记录下这个queue的大小size，size就是该层元素的个数。然后每从头部弹出一个元素就将该元素的子树的头部放入到该队列中，弹出的这个元素放到一个list里面。
 *    当size减为0的时候表名该层的元素都被弹出，里面剩的是下一层的所有节点。这里要用递归思想，递归size次后，该层的所有节点已被弹出放入到了一个list里面，queue里面已将下一侧的所有节点放入了进来。
 *    注意：这里操作的顺序是从树的高层往底层进行的，暂且和题目要求的顺序相反。
 * 2、最终返回的大List要使用LinkedList，因为题目要求最终输出的顺序的从低层到高层，由于上一步的顺序和题目要求顺序相反，所以在构造大列表元素的时候每次要往头部插入元素。
 *    而ArrayList是用数组来实现的，往头部插入元素会导致所有元素整体迁移，所以要用链表实现的LinkedList来做。
 * 
 * 
 * @author zhangzhiwang
 * @date 2022年2月4日 下午2:53:42
 */
public class BinaryTreeLevelOrderTraversal {
	public static void main(String[] args) {
		TreeNode head = TreeNode.buildDemoBinaryTree();
		List<List<TreeNode>> result = f(head);
		System.out.println(result);
	}

	private static List<List<TreeNode>> f(TreeNode head) {
		List<List<TreeNode>> resultList = new LinkedList<List<TreeNode>>();
		if (head == null) {
			return resultList;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(head);
		while (!queue.isEmpty()) {
			int size = queue.size();// 记录下queue的size非常重要
			List<TreeNode> list = new ArrayList<>();
			for (int i = 0; i < size; i++) {// 注意：这里的for循环一定要使用保存好的size，不能使用queue.size()，因为循环里queue.poll()会使queue.size()递减，会使循环次数递减
				TreeNode treeNode = queue.poll();
				list.add(treeNode);
				if (treeNode.left != null) {
					queue.add(treeNode.left);
				}
				if (treeNode.right != null) {
					queue.add(treeNode.right);
				}
			}
			resultList.add(0, list);// 每次都要从头部插入
		}
		
		return resultList;
	}
}
