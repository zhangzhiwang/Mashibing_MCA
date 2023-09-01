package com.mashibing.preInEclipse.senior.tree.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树节点
 *
 * @author zhangzhiwang
 * @date 2022年2月18日 上午10:34:00
 */
public class TrieNode {
	// 该节点被经过的次数
	public int pass;
	// 以该节点为结尾的字符串个数
	public int end;
	/**
	 * 该节点下面有多少个子节点，前缀树是一棵多叉树
	 * key是字符的ascii码，比如a是97，A是65
	 * value是子节点
	 */
	public Map<Integer, TrieNode> tnMap = new HashMap<>();
}
