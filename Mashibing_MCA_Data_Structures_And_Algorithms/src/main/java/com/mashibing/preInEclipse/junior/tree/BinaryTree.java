package com.mashibing.preInEclipse.junior.tree;

/**
 * 二叉树
 * 注意：二叉树节点之间不能有循环链
 * 
 * 遍历顺序：
 * 1、前序遍历：任意一棵子树遍历的顺序是：头-左-右
 * 2、中序遍历：任意一棵子树遍历的顺序是：左-头-右
 * 3、后序遍历：任意一棵子树遍历的顺序是：左-右-头
 * 递归序：按照节点被访问的顺序进行排序，遍历完整个树之后，每一个节点都会被访问3次，最终的完整顺序就是递归序。
 * 前序、中序、后序就是有递归序衍生而来的：前序就是每个节点第一次被访问的时候打印，中序就是每个节点第二次被访问的时候打印，后序就是每个节点第三次被访问的时候打印
 * 把一棵二叉树拉平到同一直线上就是中序遍历的结果。
 * 
 * @author zhangzhiwang
 * @date 2022年1月27日 上午10:17:56
 */
public class BinaryTree {
	public static void main(String[] args) {
		Node node_1 = makeATree();
//		pre(node_1);
//		mid(node_1);
		post(node_1);
	}

	public static Node makeATree() {
		// 构造一棵树：1的左子树是2，右子树是3，2的左子树是4，右子树是5；3的左子树是6，右子树是7
		Node node_1 = new Node(1);
		Node node_2 = new Node(2);
		Node node_3 = new Node(3);
		Node node_4 = new Node(4);
		Node node_5 = new Node(5);
		Node node_6 = new Node(6);
		Node node_7 = new Node(7);
		node_1.left = node_2;
		node_1.right = node_3;

		node_2.left = node_4;
		node_2.right = node_5;

		node_3.left = node_6;
		node_3.right = node_7;
		return node_1;
	}

	static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int value) {
			super();
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + "]";
		}
	}

	/**
	 * 递归序
	 * 
	 * 假如有一棵树：1的左子树是2，右子树是3，下面就没有了。
	 * 分析：
	 * 首先recurse(1)（后面recurse(N)就简写为r）会进入节点1时代的pos1位置（第一次进入1时代），然后递归调用本方法传入的是1的左节点2，然后进入节点2时代的recurse方法，节点1时代停留在pos2位置；
	 * 进入节点2时代的recurse方法后（第一次进入2时代），会首先进入2时代的pos1位置，然后继续调用本方法传入2的左节点null，2时代停留在了pos2位置；进入2左子树时代之后发现当前节点是空就返回了，返回到2时代的pos2位置，
	 * 这是第二次进入2时代，2时代继续从pos2位置运行，运行到pos4位置时又调用本方法传入2的右节点，进入2右子树时代之后发现当前节点是空就返回了，返回到2时代的pos4位置（第三次进入2时代）。
	 * 2时代继续运行，发现运行完了，返回1时代的pos2位置（第二次进入1时代）。1时代继续从pos2位置运行，到pos4时调用本方法传入1的右节点3，1时代停留在pos4位置。进入3时代（第一次进入3时代）后运行到pos2时传入左子树null，
	 * 然后返回到3时代的pos2位置（第二次进入3时代），运行到pos4时又转了一圈返回3时代（第三次进入3时代）。3时代运行完了返回1时代的pos4位置（第三次进入1时代），1时代也运行完了，整体结束。
	 * 可以发现每个节点都会访问三次。
	 * 
	 * 在这个树的基础上再继续往下加入子树：2的左子树是4，右子树是5；3的左子树是6，右子树是7。
	 * 那么完整的递归序是：1-2-4-4-4-2-5-5-5-2-1-3-6-6-6-3-7-7-7-3-1
	 * 当每个节点第一次被访问到时打印就是前序遍历：1-2-4-5-3-6-7
	 * 当每个节点第二次被访问到时打印就是中序遍历：4-2-5-1-6-3-7
	 * 当每个节点第三次被访问到时打印就是后序遍历：4-5-2-6-7-3-1
	 * 
	 * @param head
	 * @author zhangzhiwang
	 * @date 2022年1月27日 上午10:27:57
	 */
	private static void recurse(Node head) {
		if (head == null) {
			return;
		}

		// pos1：某个节点时代第一次被访问到
		recurse(head.left);// pos2

		// pos3：某个节点时代第二次被访问到
		recurse(head.right);// pos4

		// pos5：某个节点时代第三次被访问到
	}

	/**
	 * 前序遍历
	 * 
	 * @param head
	 * @author zhangzhiwang
	 * @date 2022年1月27日 上午10:56:57
	 */
	private static void pre(Node head) {
		if (head == null) {
			return;
		}

		// pos1：某个节点时代第一次被访问到
		System.out.println(head.value);
		pre(head.left);// pos2

		// pos3：某个节点时代第二次被访问到
		pre(head.right);// pos4

		// pos5：某个节点时代第三次被访问到
	}

	/**
	 * 中序遍历
	 * 
	 * @param head
	 * @author zhangzhiwang
	 * @date 2022年1月27日 上午10:56:57
	 */
	private static void mid(Node head) {
		if (head == null) {
			return;
		}

		// pos1：某个节点时代第一次被访问到
		mid(head.left);// pos2

		// pos3：某个节点时代第二次被访问到
		System.out.println(head.value);
		mid(head.right);// pos4

		// pos5：某个节点时代第三次被访问到
	}

	/**
	 * 后序遍历
	 * 
	 * @param head
	 * @author zhangzhiwang
	 * @date 2022年1月27日 上午10:56:57
	 */
	private static void post(Node head) {
		if (head == null) {
			return;
		}

		// pos1：某个节点时代第一次被访问到
		post(head.left);// pos2

		// pos3：某个节点时代第二次被访问到
		post(head.right);// pos4

		// pos5：某个节点时代第三次被访问到
		System.out.println(head.value);
	}
}
