package com.mashibing.preInEclipse.junior.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据前序遍历和中序遍历的结果还原原来的二叉树是什么（找头游戏，找到所有头整棵树就还原了）
 * 本质：整个这道题就是一个找头游戏，根据前序遍历和后续遍历的数组找到所有子树的头，游戏结束，原来的那棵树就还原出来了。
 * 
 * 思路：
 * 1、前序遍历的第一个位置就是整个树的头部root
 * 2、找到root在中序遍历的位置，进而可以确定左子树和右子树的范围；然后将这个范围应用到前序遍历数组，同样找出pre中的左子树和右子树的范围
 * 3、递归确定左子数和右子树的头部，然后构造树的结构，最终返回头部root
 * 
 * @author zhangzhiwang
 * @date 2022年1月27日 下午5:54:55
 */
public class GetBinaryTreeByPreAndMid {
	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
//		TreeNode[] pre = {node1,node2,node4,node5,node3,node6,node7};
//		TreeNode[] mid = {node4,node2,node5,node1,node6,node3,node7};
		TreeNode[] pre = {node1,node3,node7};
		TreeNode[] mid = {node1,node3,node7};
		
		TreeNode head = getBinaryTreeByPreAndMid(pre, 0, pre.length - 1, mid, 0, mid.length - 1);
		System.out.println(head.value);
		System.out.println(head.left);
//		System.out.println(head.left.left.value);
//		System.out.println(head.left.right.value);
		System.out.println(head.right.value);
		System.out.println(head.right.left);
		System.out.println(head.right.right.value);
	}

	/**
	 * 根据前序遍历和中序遍历的结果还原原来的二叉树是什么
	 * 
	 * @param pre 前序遍历的数组
	 * @param l1  pre数组的起始索引
	 * @param l2  pre数组的结束索引
	 * @param mid 中序遍历的数组
	 * @param r1  mid数组的起始索引
	 * @param r2  mid数组的结束索引
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月27日 下午6:40:32
	 */
	private static TreeNode getBinaryTreeByPreAndMid(TreeNode[] pre, int l1, int l2, TreeNode[] mid, int r1, int r2) {
		// 首先保证pre和mid数组都不能为空
		if ((pre == null || pre.length == 0) || (mid == null || mid.length == 0)) {// pre和mid有一个为空都不能还原
			return null;
		}

		if (pre.length != mid.length) {// 无论什么序遍历的结果长度应该相等
			return null;
		}

		Map<TreeNode, Integer> map = new HashMap<>();// 避免在f方法中循环遍历mid数组来找到head的位置
		for (int i = 0; i < mid.length; i++) {
			map.put(mid[i], i);
		}

		return f(pre, l1, l2, mid, r1, r2, map);
	}

	/**
	 * 递归调用本方法来找到当前这棵树的头
	 * 递归掉使用本方法的关键点就在于找好pre和mid数组中子树的起止位置
	 * 
	 * @param pre 前序遍历的数组
	 * @param l1  pre数组的起始索引
	 * @param l2  pre数组的结束索引
	 * @param mid 中序遍历的数组
	 * @param r1  mid数组的起始索引
	 * @param r2  mid数组的结束索引
	 * @param map
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年2月4日 上午11:04:29
	 */
	private static TreeNode f(TreeNode[] pre, int l1, int l2, TreeNode[] mid, int r1, int r2, Map<TreeNode, Integer> map) {
		/*
		 当前序遍历数组pre的起始索引大于结束索引时说明当前这棵树不存在，进而说明前面的那颗父树没有当前这颗子树，所以返回null。
		 base case只判断先序遍历数组的边界即可，无需判断中序遍历结果的边界，因为先序数组中当左边界大于右边界时说明在先序遍历中不存在左/右子树，
		 由于中序遍历结果和先序结果是对应的，那么在中序遍历结果中肯定也不存在这棵树。当先出遍历结果中左右边界重合，说明左/右子树只有一个节点，
		 那么在中序遍历结果中可定也只有一个节点，所以判断先序遍历结果的边界条件即可。
		 */
		if (l1 > l2) {
			return null;
		} else if (l1 == l2) {
			return pre[l1];
		}

		/**
		 * 前序遍历的的第一个元素无疑是整个树的root，用于最终返回。
		 * 单凭前序遍历无从知道哪部分是左子树哪部分是右子树，必须通过中序遍历找到root的位置，那么root左边就是左子树，右边就是右子树
		 */
		TreeNode head = pre[l1];// 注意pre数组的第一个元素的索引是L1，不是0，所有带范围的数组都要注意

		/**
		 * 如何在mid中找到root的位置？
		 * 1、一种思路是使用for循环，从第一个位置开始找直到找到root为止，这样的话时间复杂度是O(n)，由于f()是被递归调用的，总的时间复杂度是n * O(n)
		 * 2、另一种思路是在f函数外将mid数组转成Map，key是TreeNode，value是索引，这样在f函数外遍历一次数组就可以了(时间复杂度是O(n))，在f内部获取root索引的而时间复杂度是O(1)。
		 */
		// 找到本棵树的头
		int rootIndexInMid = map.get(head);// root在mid数组中的位置

		/**
		 * rootIndexInMid找到了，那所有的子树范围就都找到了：
		 * 1、左子树的范围在mid数组中是：[r1,rootIndexInMid - 1]，右子树的范围在mid数组中是：[rootIndexInMid + 1,r2]
		 * 2、左子树的范围在pre数组中是：[l1 + 1,l1 + 1 + (rootIndexInMid - 1 - r1)]，右子树的范围在pre数组中是：[l1 + 1 + (rootIndexInMid - 1 - r1) + 1,l2]
		 * 3、上面说的左右子树是整棵树的左右子树，左右子树内部又可以分为若干个左右子树，每一个子树又得找头节点和每个子树的左右子树，方法就是f()的递归
		 */
		// leftHead是以head为头的左子树的头，所以传入的pre和mid数组的范围都是左子树的范围
		// 整个这道题就是一个找头游戏，根据前序遍历和后续遍历的数组找到所有子树的头，游戏结束，原来那棵树就还原出来了
		TreeNode leftHead = f(pre, l1 + 1, l1 + 1 + (rootIndexInMid - 1 - r1), mid, r1, rootIndexInMid - 1, map);
		// rightHead是以head为头的右子树的头，所以传入的pre和mid数组的范围都是右子树的范围
		TreeNode rightHead = f(pre, l1 + 1 + (rootIndexInMid - 1 - r1) + 1, l2, mid, rootIndexInMid + 1, r2, map);
		// 找到左右子树的头之后跟head挂上
		head.left = leftHead;
		head.right = rightHead;

		return head;
	}
}
