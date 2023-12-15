package com.mashibing.preInEclipse.junior.tree;

/**
 * 树节点
 * 
 * @author zhangzhiwang
 * @date 2022年1月27日 下午3:30:29
 */
public class TreeNode {
	public int value;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int value) {
		super();
		this.value = value;
	}

	@Override
	public String toString() {
		return "ThreeNode [value=" + value + "]";
	}

	/**
	 * 建立一个二叉树，结构为：1的左子树是2，右子树是3，2的左子树是4，右子树是5；3的左子树是6，右子树是7
	 * 
	 *    1
	   2 	  3
	 4   5  6   7
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月27日 下午3:43:25
	 */
	public static TreeNode buildDemoBinaryTree() {
		TreeNode treeNode_1 = new TreeNode(1);
		TreeNode treeNode_2 = new TreeNode(2);
		TreeNode treeNode_3 = new TreeNode(3);
		TreeNode treeNode_4 = new TreeNode(4);
		TreeNode treeNode_5 = new TreeNode(5);
		TreeNode treeNode_6 = new TreeNode(6);
		TreeNode treeNode_7 = new TreeNode(7);
		treeNode_1.left = treeNode_2;
		treeNode_1.right = treeNode_3;

		treeNode_2.left = treeNode_4;
		treeNode_2.right = treeNode_5;

		treeNode_3.left = treeNode_6;
		treeNode_3.right = treeNode_7;
		return treeNode_1;
	}
}