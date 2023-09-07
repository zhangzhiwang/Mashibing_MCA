package com.mashibing.preInEclipse.junior.tree;

/**
 * 树的高度
 * 节点的高度：（从所在位置往下看才叫高度，从0开始）该节点到叶子结点的最长路径（边数）。树的高度等于根节点的高度。
 * 节点的深度：（从所在位置往上看才叫深度，从0开始）从根节点到该节点的边数。从上往下看，根节点到任意节点的通路只有一条，因为在树中不允许多个节点指向同一节点，所以不存在最长路径的说法。
 * 树的深度就是最深叶子节点的深度。所以从整个树来看，树的高度等于树的深度。
 * （由于有的地方规定树的高度和深度从0开始，有的地方规定从1开始，面试的时候跟面试官沟通好）
 * 
 * @author zhangzhiwang
 * @date 2022年1月27日 下午4:05:46
 */
public class TreeDepth {
	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		node1.left = node2;
		node1.right = node3;
		node3.left = node4;
		node3.right = node5;
		node4.right = node6;
		
		System.out.println(getTreeDepth(node1));
	}
	
	/**
	 * 求树的高度
	 * 注意：这里树的高度从1开始算
	 * 例如：
	 *   1
  	2	      3
           4     5  
             6 
             
     * t1:h1 = 1
     * t1:h2=3
     * t3:3
     * t3:h1=2
     * t3:h2=1
     * t5:1
     * t4:h1=0,t4:h2=1,t4:2
     * t6:1
	 * 
	 * @param head
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月27日 下午4:42:44
	 */
	private static int getTreeDepth(TreeNode head) {
		if(head == null) {
			return 0;
		}
		
		int h1 = getTreeDepth(head.left);
		int h2 = getTreeDepth(head.right);
		return Math.max(h1, h2) + 1;
	}
}
