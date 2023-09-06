package com.mashibing.preInEclipse.junior.tree;

import com.mashibing.preInEclipse.junior.tree.BinaryTree.Node;

/**
 * 判断两棵树完全相等
 * 题目：给定两颗完全独立的树，树的每个节点的值必须一样，树的结构也必须一样
 * 
 * @author zhangzhiwang
 * @date 2022年1月27日 上午11:04:06
 */
public class TreeEquals {
	public static void main(String[] args) {
		Node head1 = BinaryTree.makeATree();
		Node head2 = BinaryTree.makeATree();
		System.out.println(isTwoTreeEquals(head1, head2));
		
		
		Node node_8 = new Node(8);
		head2.left.left.right = node_8;
		System.out.println(isTwoTreeEquals(head1, head2));
	}
	
	private static boolean isTwoTreeEquals(Node head1, Node head2) {
		// true=1，false=0，如果结果为1，说明"^"两边的操作数不一样
		if (head1 == null ^ head2 == null) {// head1和head2一个为空一个不为空，肯定不一样
			return false;
		}

		if (head1 == null && head2 == null) {// 都是空树，人为规定：都为空则认为一样
			return true;
		}

		// 都不为空
		// part1:
		if (head1.value != head2.value) {// 当前节点的值不一样
			return false;
		}

		// 判断当前节点的所有左子节点和所有右子节点是否相等
		// part2:
		if (!isTwoTreeEquals(head1.left, head2.left)) {
			return false;
		}
		
		// part3:
		if (!isTwoTreeEquals(head1.right, head2.right)) {
			return false;
		}

		// part4:
		return true;
		
		// 以上part1-4可以合并为1句：
		// 分开写便于阅读，好理解，思路清晰；合起来写不好理解，可以起到装B的效果
		// return head1.value == head2.value && isTwoTreeEquals(head1.left, head2.left) && isTwoTreeEquals(head1.right, head2.right)
	}
}
