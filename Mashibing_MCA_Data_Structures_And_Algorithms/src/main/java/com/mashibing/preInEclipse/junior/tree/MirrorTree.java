package com.mashibing.preInEclipse.junior.tree;

/**
 * 镜面树
 * 以整个数的头结点位中心，将整棵树分成了左右两部分，左半部分和右半部分互为镜面（值相等位置相反）。这里要注意的是以整棵树左右两边互为镜面，不是任意节点的左右子树都互为镜面。
 * 
 * 思路：
 * 把一棵树以输的头结点位中心左右劈开，左子树的左节点和右子树的右节点比较，左子树的右节点和右子树的左节点比较，左右子树的每个节点的值一样结构也一样。
 * 
 * @author zhangzhiwang
 * @date 2022年1月27日 上午11:57:01
 */
public class MirrorTree {
	public static void main(String[] args) {
		TreeNode head = TreeNode.buildDemoBinaryTree();
		System.out.println(isMirrorTree(head, head));// 将一棵树作为两棵树进行比较
		
		/**
		 * 构造一颗镜面树：
		 * 	     1
  			2	      2
 		3     4   4     3
   		  5 6       6 5
		 */
		TreeNode node1 = new TreeNode(1);
		TreeNode node2_l = new TreeNode(2);
		TreeNode node2_r = new TreeNode(2);
		TreeNode node3_l = new TreeNode(3);
		TreeNode node3_r = new TreeNode(3);
		TreeNode node4_l = new TreeNode(4);
		TreeNode node4_r = new TreeNode(4);
		TreeNode node5_l = new TreeNode(5);
		TreeNode node5_r = new TreeNode(5);
		TreeNode node6_l = new TreeNode(6);
		TreeNode node6_r = new TreeNode(6);
		
		node1.left = node2_l;
		node1.right = node2_r;
		node2_l.left = node3_l;
		node2_l.right = node4_l;
		node2_r.left = node4_r;
		node2_r.right = node3_r;
		node3_l.right = node5_l;
		node4_l.left = node6_l;
		node4_r.right = node6_r;
		node3_r.left = node5_r;
		
		System.out.println(isMirrorTree(node1, node1));
	}
	
	public static boolean isMirrorTree(TreeNode head1, TreeNode head2) {
		if(head1 == null ^ head2 == null) {
			return false;
		}
		
		if(head1 == null && head2 == null) {
			return true;
		}
		
		return head1.value == head2.value && isMirrorTree(head1.left, head2.right) && isMirrorTree(head1.right, head2.left);// 左子树的左和右子树的右进行比较，反过来同理
	}
}