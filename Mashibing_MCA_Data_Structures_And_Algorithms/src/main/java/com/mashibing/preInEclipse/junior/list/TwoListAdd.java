package com.mashibing.preInEclipse.junior.list;

/**
 * 两个链表对应位置上的数相加
 * 题目：给定两个链表的头节点head1和head2，每一个链表都认为从左到右是某个数字的低位到高位，返回两个数字相加之后的结果所代表的的链表。
 * 解释：有两个链表，l1：7 -> 3 -> 9，l2：9 -> 8 -> 7 -> 6，相加之后输出：6 -> 2 -> 7 -> 7。l1可以看做数字937，l2可以看做数字6789，相加得7726，然后根据题目要求：
 * 如果要求输出一个新链表，那么新建一个6 -> 2 -> 7 -> 7的链表了如果要求将结果合并到长链表，那么就覆盖长链表元素的值并返回，这里采用后者。
 * 
 * 思路：分三个阶段：
 * 首先要知道两个链表谁长谁短，并标识为长链表L和短链表。
 * 1、长链表有，短链表有，按照加法法则进行相加，如果有进位则使用单独的变量保存进位
 * 2、长链表有，短链表没有了，长链表剩下的数字和进位相加
 * 3、长链表没有了，短链表肯定也没有了，那么就看进位还有没有，如果没有就返回，如果有那么要在长链表的的后面加上一个节点，其值为1
 * 
 * @author zhangzhiwang
 * @date 2022年1月22日 下午4:25:37
 */
public class TwoListAdd {
	public static void main(String[] args) {
		// 构造短链表：7 -> 3 -> 9
		Node n_7 = new Node(9);
		Node n_3 = new Node(9);
		Node n_9 = new Node(9);
		n_7.next = n_3;
		n_3.next = n_9;
		System.out.println("短链表：");
		System.out.println(n_7.value + " -> " + n_7.next.value + " -> " + n_7.next.next.value);
		
		// 构造长链表：8 -> 6 -> 5 -> 1
		Node n_8 = new Node(1);
//		Node n_6 = new Node(6);
//		Node n_5 = new Node(5);
//		Node n_1 = new Node(1);
//		n_8.next = n_6;
//		n_6.next = n_5;
//		n_5.next = n_1;
		System.out.println("短链表：");
//		System.out.println(n_8.value + " -> " + n_8.next.value + " -> " + n_8.next.next.value + " -> " + n_8.next.next.next.value);
		System.out.println(n_8.value );
		
		System.out.println("长度：" + getSize(n_8));
		
		n_8 = addList(n_7, n_8);
		System.out.println("相加后：");
		System.out.println(n_8.value + " -> " + n_8.next.value + " -> " + n_8.next.next.value + " -> " + n_8.next.next.next.value);
	}
	
	static class Node {
		private int value;
		private Node next;

		public Node(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 两个链表相应位置上的元素相加
	 * 
	 * @param head1 列表1的头结点
	 * @param head2 列表2的头结点
	 * @author zhangzhiwang
	 * @date 2022年1月22日 下午4:48:04
	 */
	private static Node addList(Node head1, Node head2) {
		// 首先要找到谁是长链表谁是短链表
		int size1 = getSize(head1);
		int size2 = getSize(head2);
		Node lHead = size1 >= size2 ? head1 : head2;// 长链表头结点
		Node sHead= lHead == head1 ? head2 : head1;// 短链表头结点
		
		// 由于本题目采取最终结果覆盖长链表的方式，所以最终要返回长链表的头部，这里必须事先弄一份拷贝出来，后面的移动及所有操作都用拷贝引用来做
		Node copyLHead = lHead;// 后续所有操作都用老被应用来做
		
		// 阶段1：长链表有，短链表有，循环短链表即可
		int tmp = 0;// 保存进位
		Node lEnd = null;// 长链表的最后一个节点，用于在第三阶段将新节点挂在长链表的最后一个节点之后，因为最后长链表的head肯定会移出去变成null，再往回就找不到谁是最后一个节点了，所以要跟踪保存长链表的最后一个节点是谁。
		while(sHead != null) {
			int sum = copyLHead.value + sHead.value + tmp;
			copyLHead.value = sum % 10;
			tmp = sum / 10;
			lEnd = copyLHead;// 注意：由于记录的是长链表的最后一个节点，所以记录的时候是在copyLHead后移之前，如果在后移之后那么lEnd可能是null
			copyLHead = copyLHead.next;
			sHead = sHead.next;
		}
		// 上面的while循环结束说明整个短链表循环加完了
		// 阶段2：长链表有，短链表没有了
		while(copyLHead != null) {// 注意这里的copyLHead已经不是最开始的copyLHead了，因为在上一个while循环里面copyLHead后移了多次，这里的copyLHead是接着上一步操作继续的
			int sum = copyLHead.value + tmp;
			copyLHead.value = sum % 10;
			tmp = sum / 10;
			lEnd = copyLHead;
			copyLHead = copyLHead.next;
		}
		// 上面的while循环结束说明整个长链表循环加完了
		// 阶段3：长链表没有了，短链表肯定也没有了，只看进位
		if(tmp != 0) {
			Node n = new Node(tmp);
			lEnd.next = n;
		}
		
		return lHead;
	}
	
	/**
	 * 返回链表长度
	 * 
	 * @param head 链表头结点
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月22日 下午4:37:34
	 */
	private static int getSize(Node head) {
		int size = 0;
		while(head != null) {
			head = head.next;
			size++;
		}
		
		return size;
	}
}
