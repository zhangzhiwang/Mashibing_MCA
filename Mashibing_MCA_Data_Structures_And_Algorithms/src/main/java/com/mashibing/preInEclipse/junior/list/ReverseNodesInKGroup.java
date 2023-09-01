package com.mashibing.preInEclipse.junior.list;

/**
 * k个节点的组内反转
 * 解释：给定一个单向链表，每k个节点分为一组，每组内的节点进行反转，不足k各节点的不分组也不反转，原样保持不变，最后组成一个新的完整的链表并返回新链表的头结点。
 * 比如：k=3，(a -> b -> c )-> (d -> e -> f )-> g -> h -> null，最后输出：c -> b -> a -> f
 * -> e -> d -> g -> h -> null
 * 
 * 思路：
 * 1、写一个方法能够根据给定的start节点和k找到end节点并返回end节点
 * 2、写第二个方法：反转start节点和end节点之间的链表，注意最后一步要将反转后的尾结点的next指向下一组反转之前的头结点
 * 3、写一个总体的方法完成整个链表的反转：
 * （1）判断一下整个链表的长度是否达到了k
 * （2）通过上面的判断之后开始循环反转每一组的链表，其中要有一个临时变量保存上一组反转后的end节点，注意这里要修正第二步反转后end节点的next指针的指向，之前是指向了下一组未反转的头结点，这回要修正为反转后的头结点
 * 
 * @author zhangzhiwang
 * @date 2022年1月21日 上午11:54:43
 */
public class ReverseNodesInKGroup {
	public static void main(String[] args) {
		// 构造原始单链表
		Node a = new Node("a");
		Node b = new Node("b");
		Node c = new Node("c");
		Node d = new Node("d");
		Node e = new Node("e");
		Node f = new Node("f");
		Node g = new Node("g");
		Node h = new Node("h");

		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		e.next = f;
		f.next = g;
		g.next = h;

		System.out.println("原始单链表：");
		System.out.print(a.value + " -> " + a.next.value + " -> " + a.next.next.value + " -> " + a.next.next.next.value
				+ " -> " + a.next.next.next.next.value + " -> " + a.next.next.next.next.next.value + " -> "
				+ a.next.next.next.next.next.next.value + " -> " + a.next.next.next.next.next.next.next.value);
		System.out.println();
		
		System.out.println("反转后的链表：");
		Node head = reverseWholeListByKGroup(a, 3);
		System.out.print(head.value + " -> " + head.next.value + " -> " + head.next.next.value + " -> " + head.next.next.next.value
				+ " -> " + head.next.next.next.next.value + " -> " + head.next.next.next.next.next.value + " -> "
				+ head.next.next.next.next.next.next.value + " -> " + head.next.next.next.next.next.next.next.value);
	}

	static class Node {
		private String value;
		private Node next;

		public Node(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + "]";
		}

	}

	/**
	 * 反转整个链表
	 * 
	 * @param head 整个链表的头结点
	 * @param k    每组元素个数
	 * @return 返回整个链表反转后的头结点
	 * @author zhangzhiwang
	 * @date 2022年1月22日 下午12:10:20
	 */
	public static Node reverseWholeListByKGroup(Node head, int k) {
		// (a -> b -> c )-> (d -> e -> f )-> g -> h -> null
		if (head == null || k <= 1) {
			return head;// 原链表不动
		}

		// 判断整个链表的元素个数够不够k，如果k比整个链表的长度都长那么之间返回原始head
		Node start = head;
		Node end = getKGroupEndNode(start, k);// 这里只是找到了第一组的尾结点，但还没有反转
		if (end == null) {// 说明整个链表的长度不够k
			return head;
		}

		head = end;// 通过了上面的判断说明链表长度比k大，能够走到这里说明第一组的尾结点在下面被反转后会成为头结点，第一组的头结点也是整个链表的头结点，由于最终要返回新链表的头结点
					// 所以，这里要将头结点这时为第一组的尾结点用于最终返回，后续操作都不能用head节点来进行。
		Node lastEnd = null;// 记录上一组反转后的尾结点，起始为null
		do {
			reverseInGroup(start, end);// c -> b -> a -> f -> e -> d -> g -> h -> null
			lastEnd = start;
			start = lastEnd.next;
			end = getKGroupEndNode(start, k);
			if (end == null) {
				return head;
			}
			lastEnd.next = end;
		} while (lastEnd.next != null);

		return head;
	}

	/**
	 * 返回某组的结束节点，某组一共k个元素
	 * 
	 * @param start 起始节点
	 * @param k     每组节点个数
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月22日 上午11:09:18
	 */
	public static Node getKGroupEndNode(Node start, int k) {
		// 边界判断
		if (start == null || k <= 1) {
			return start;
		}

		/**
		 * 注意：while(--k > 0) {} 和 while(k > 1) {k--;} 在效果上是等同的
		 * --k放在方法体内和放在条件判断里效果是一样的，因为--k是先自减再赋值和比较，但是要注意的是条件判断里面右边的操作数相差1，比如本例的话--k放到判断条件里那么是和0比较，
		 * --k放到大括号里，那么判断条件的k要和1比较，原因就是while(--k > 0)是在第一次循环之前先将k减1之后再比较循环条件，而 while(k >
		 * 1) {k--;}在第一次循环之前先用k的原值作比较，
		 * 然后在代码块里做自减操作。
		 */
		while (--k > 0 && start != null) {
			start = start.next;
		}

		return start;
	}

	/**
	 * 组内反转
	 * 
	 * @param start 起始节点
	 * @param end   结束节点
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月22日 上午11:39:30
	 */
	public static void reverseInGroup(Node start, Node end) {
		// (a -> b -> c )-> (d -> e -> f )-> g -> h -> null
		// null <- a <- b <- c
		if (start == null || end == null) {
			return;
		}

		Node pre = null;
		Node cur = start;// 这一步很关键，循环里面的操作用cur代替start来进行，循环退出后关键的一步是将原来的start的下一个指针指向下一组的开头，也就是本次循环结束后end的位置，而且本方法从喀什到结束根本就没有改变start的位置，外面还要用这个start。
		Node next = null;
		end = end.next;// 这一步也很重要，因为在循环里面要操作end，也就是反转涉及到end，而判断条件是start != end，如果事先没有本步操作的话，当cur ==
						// end的时候就退出循环了，end操作不到
		while (cur != end) {
			next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		start.next = end;// 这一步很关键，这就是为什么在循环开始之前要保存start的原因。
	}
}