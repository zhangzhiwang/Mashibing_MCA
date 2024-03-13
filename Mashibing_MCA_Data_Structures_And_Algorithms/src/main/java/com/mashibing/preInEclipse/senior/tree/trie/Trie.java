package com.mashibing.preInEclipse.senior.tree.trie;

/**
 * 前缀树（多叉树）
 * 前缀树的节点不保存数据，数据可以想象成保存在两节点之间的连线上
 *
 * 注意：前缀树不要和图混淆，图是可以有环的，不同路径可以共用节点，但是前缀树不能有环，不同路径不能共用节点
 *
 * @author zhangzhiwang
 * @date 2022年2月18日 上午10:33:40
 */
public class Trie {
	// 根节点，始终不要给root赋新值，root始终是root
	private TrieNode root = new TrieNode();

	/**
	 * 向前缀树添加元素
	 * 思路：
	 * 1、经过的节点（没有就创建）pass全部++，尾结点end++。
	 * 2、设置一个节点从头结点开始，一级一级往下移动直至字符串的最后一个字符，切记不要用root直接移动
	 * 
	 * @param value
	 * @author zhangzhiwang
	 * @date 2022年2月18日 上午10:59:53
	 */
	public void add(String value) {
		if (value == null) {
			return;
		}

		char[] charArray = value.toCharArray();
		// 注意：这为什么用临时变量替代root而不是用root直接操作？是因为下面curNode要随时移动，如果用root直接操作的话，那么Trie类的同一个对象再次调用add方法时root就已经不是根对象了
		TrieNode curNode = root;
		curNode.pass++;// 根节点肯定要经过，所以根节点的pass肯定要++
		for (int i = 0; i < charArray.length; i++) {
			TrieNode curChar = curNode.tnMap.get((int) charArray[i]);
			if (curChar == null) {
				curChar = new TrieNode();
				curNode.tnMap.put((int) charArray[i], curChar);
			}
			curChar.pass++;
			curNode = curChar;// 注意：curNode要往下移，这一步不能少
		}
		curNode.end++;
	}

	public boolean contains(String value) {// 本方法名也可以命名为search
		if (value == null) {
			return false;
		}

		char[] charArray = value.toCharArray();
		TrieNode curNode = root;
		for (int i = 0; i < charArray.length; i++) {
			TrieNode curChar = curNode.tnMap.get((int) charArray[i]);
			if (curChar == null) {
				return false;
			}
			curNode = curChar;
		}

		return curNode.end > 0;// 注意：前缀树是无环的，图是有环的，不要混淆
	}

	public boolean containsPre(String value) {// 本方法名也可以命名为search
		if (value == null) {
			return false;
		}

		char[] charArray = value.toCharArray();
		TrieNode curNode = root;
		for (int i = 0; i < charArray.length; i++) {
			TrieNode curChar = curNode.tnMap.get((int) charArray[i]);
			if (curChar == null) {
				return false;
			}
			curNode = curChar;
		}

		return true;
	}

	/**
	 * 获取某一个字符串出现了几次
	 * 
	 * @param value
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年2月18日 上午11:22:54
	 */
	public int getCount(String value) {
		if (value == null) {
			return 0;
		}

		char[] charArray = value.toCharArray();
		TrieNode curNode = root;
		for (int i = 0; i < charArray.length; i++) {
			TrieNode curChar = curNode.tnMap.get((int) charArray[i]);
			if (curChar == null) {
				return 0;
			}
			curNode = curChar;
		}
		return curNode.end;
	}

	public void delete(String value) {
		if (value == null) {
			return;
		}

		if (!contains(value)) {// 如果不包含该字符串，直接退出，这一步很重要，不能省略，因为后面涉及pass的--以及节点的删除，如果遍历一半发现不包含该字符串就麻烦了，之前的节点pass已经--了。
			return;
		}

		// 到这里说明trie肯定包含value这个字符串
		char[] charArray = value.toCharArray();
		TrieNode curNode = root;
		curNode.pass--;
		for (int i = 0; i < charArray.length; i++) {
			TrieNode curChar = curNode.tnMap.get((int) charArray[i]);// 既然前面已经用contains方法检查过了所以curChar肯定不为null
			if (--curChar.pass == 0) {
				curNode.tnMap.remove((int) charArray[i]);// 让当前节点和trie断开联系，那么当前节点后面的所有子节点就都不用管了
				curChar = null;// 让JVM回收，其实这句不写也行，因为curChar是局部变量，等delete方法运行结束后自然会被释放掉，只要没有全局引用引着就可以
				return;
			}
			curNode = curChar;
		}
		// for循环结束还没有return掉说明字符串出现不止一次
		curNode.end--;
	}
}