package com.mashibing.dailyPractice.round4;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树：实现add、search、searchPre、getCount、delete方法
 */
public class Trie_0730 {
    static class TrieNode_0730 {
        private int pass;
        private int end;
        private TrieNode_0730[] children = new TrieNode_0730[26];// 要求字符串必须是小写字母
        //用数组实现有限制，如果没有限制的话可以使用Map实现，key是字符的ASCII码（字符转int就是字符的ASCII码）
//        private Map<Integer, TrieNode_0730> children = new HashMap<>();
    }

    private TrieNode_0730 root = new TrieNode_0730();

    public void add(String s) {
        if(s == null || s.length() == 0) {
            return;
        }

        char[] chars = s.toCharArray();
        root.pass++;
        TrieNode_0730 cur = root;
        for (char c : chars) {
            TrieNode_0730 node = cur.children[c - 'a'];
            if(node == null) {
                cur.children[c - 'a'] = node = new TrieNode_0730();
            }
            node.pass++;
            cur = node;
        }
        cur.end++;
    }

    public boolean search(String s) {
        if(s == null || s.length() == 0) {
            return false;
        }

        char[] chars = s.toCharArray();
        TrieNode_0730 cur = root;
        for (char c : chars) {
            TrieNode_0730 node = cur.children[c - 'a'];
            if(node == null) {
                return false;
            }
            cur = node;
        }

        return cur.end != 0;
    }

    public boolean searchPre(String s) {
        if(s == null || s.length() == 0) {
            return false;
        }

        char[] chars = s.toCharArray();
        TrieNode_0730 cur = root;
        for (char c : chars) {
            TrieNode_0730 node = cur.children[c - 'a'];
            if(node == null) {
                return false;
            }
            cur = node;
        }

        return true;
    }

    public int getCount(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();
        TrieNode_0730 cur = root;
        for (char c : chars) {
            TrieNode_0730 node = cur.children[c - 'a'];
            if(node == null) {
                return 0;
            }
            cur = node;
        }

        return cur.end;
    }

    public void delete(String s) {
        if (s == null || s.length() == 0) {
            return;
        }

        if(!search(s)) {
            return;
        }

        char[] chars = s.toCharArray();
        root.pass--;
        TrieNode_0730 cur = root;
        for (int i = 0; i < chars.length; i++) {
            TrieNode_0730 node = cur.children[chars[i] - 'a'];
            node.pass--;
            if(node.pass == 0) {
                cur.children[chars[i] - 'a'] = null;
                return;
            }
            cur = node;
        }
        cur.end--;
    }

    public static void main(String[] args) {
        Trie_0730 trie = new Trie_0730();
        trie.add("abc");
        System.out.println("search = " + trie.search("abc"));
        System.out.println("searchPre = " + trie.searchPre("d"));
        System.out.println("getCount = " + trie.getCount("abc"));
        System.out.println("------------");

        trie.add("ab");
        System.out.println("search = " + trie.search("ab"));
        System.out.println("searchPre = " + trie.searchPre("a"));
        System.out.println("getCount = " + trie.getCount("ab"));
        System.out.println("------------");

        trie.add("abc");
        System.out.println("search = " + trie.search("ab"));
        System.out.println("searchPre = " + trie.searchPre("a"));
        System.out.println("getCount = " + trie.getCount("abc"));
        System.out.println("------------");

        trie.add("bcd");
        System.out.println("search = " + trie.search("bcd"));
        System.out.println("searchPre = " + trie.searchPre("b"));
        System.out.println("getCount = " + trie.getCount("bcd"));
        System.out.println("------------");

        trie.delete("abc");
        System.out.println("search = " + trie.search("abc"));
        System.out.println("searchPre = " + trie.searchPre("a"));
        System.out.println("getCount = " + trie.getCount("abc"));
        System.out.println("------------");

        trie.delete("abc");
        System.out.println("search = " + trie.search("abc"));
        System.out.println("searchPre = " + trie.searchPre("ab"));
        System.out.println("getCount = " + trie.getCount("abc"));
        System.out.println("------------");

        trie.delete("abc");
        System.out.println("search = " + trie.search("abc"));
        System.out.println("searchPre = " + trie.searchPre("ab"));
        System.out.println("getCount = " + trie.getCount("abc"));
        System.out.println("------------");
    }
}