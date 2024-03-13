package com.mashibing.dailyPractice.round1._2_7;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树：实现add、search、getCount、delete方法
 */
public class Trie_0207 {
    private TrieNode root = new TrieNode();

    static class TrieNode {
        public int pass;
        public int end;
        public Map<Integer, TrieNode> map = new HashMap<>();
    }

    public void add(String str) {
        if (str == null || str.length() == 0) {
            return;
        }

        char[] charArray = str.toCharArray();
        TrieNode cur = root;
        cur.pass++;
        for(int i = 0; i < charArray.length; i++) {
            TrieNode node = cur.map.get((int) charArray[i]);
            if(node == null) {
                node = new TrieNode();
                cur.map.put((int) charArray[i], node);
            }
            node.pass++;
            cur = node;
        }
        cur.end++;
    }

    /**
     * 查找前缀树是否包含某个字符串
     * @param str
     * @return
     */
    public boolean search(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        char[] charArray = str.toCharArray();
        TrieNode cur = root;
        for(int i = 0; i < charArray.length; i++) {
            TrieNode node = cur.map.get((int) charArray[i]);
            if(node == null) {
                return false;
            }

            cur = node;
        }

        return cur.end > 0;
    }

    /**
     * 查找前缀树是否包含以某个字符串开头的字符串
     * @param str
     * @return
     */
    public boolean searchPre(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        char[] charArray = str.toCharArray();
        TrieNode cur = root;
        for(int i = 0; i < charArray.length; i++) {
            TrieNode node = cur.map.get((int) charArray[i]);
            if(node == null) {
                return false;
            }

            cur = node;
        }

        return true;
    }

    /**
     * 查找指定字符串出现了几次
     * @param str
     */
    public int getCount(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] charArray = str.toCharArray();
        TrieNode cur = root;
        for(int i = 0; i < charArray.length; i++) {
            TrieNode node = cur.map.get((int) charArray[i]);
            if(node == null) {
                return 0;
            }

            cur = node;
        }

        return cur.end;
    }

    public void delete(String str) {
        if (str == null || str.length() == 0) {
            return;
        }

        if(!search(str)) {
            return;
        }

        char[] charArray = str.toCharArray();
        TrieNode cur = root;
        cur.pass--;
        for(int i = 0; i < charArray.length; i++) {
            TrieNode node = cur.map.get((int) charArray[i]);
            if(--node.pass == 0) {
                cur.map.remove((int) charArray[i]);
                node = null;
                return;
            }

            cur = node;
        }

        cur.end--;
    }

    public static void main(String[] args) {
        Trie_0207 trie = new Trie_0207();
        trie.add("ab");
        trie.add("abcd");
        trie.add("bde");

        System.out.println(trie.getCount("ab"));
        System.out.println(trie.search("ab"));
//        trie.delete("bde");
        System.out.println(trie.searchPre("b"));
    }
}
