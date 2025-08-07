package com.mashibing.dailyPractice.round5;

import com.mashibing.dailyPractice.round4.Trie_0730;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树：实现add、search、searchPre、getCount、delete方法
 */
public class Trie_0828 {
    static class TrieNode_0828 {
        private int p;
        private int e;
        private Map<Character, TrieNode_0828> map = new HashMap<>();

        public TrieNode_0828(int p, int e) {
            this.p = p;
            this.e = e;
        }
    }

    private TrieNode_0828 root = new TrieNode_0828(0, 0);

    public void add(String s) {
        if (s == null || s.length() == 0) {
            return;
        }

        root.p++;
        TrieNode_0828 c = root;
        char[] chars = s.toCharArray();
        int charsI = 0;
        while (charsI < chars.length) {
            TrieNode_0828 node = null;
            if (c.map.containsKey(chars[charsI])) {
                node = c.map.get(chars[charsI]);
                node.p++;
            } else {
                node = new TrieNode_0828(1, 0);
                c.map.put(chars[charsI], node);
            }

            c = node;
            charsI++;
        }
        c.e++;
    }

    public boolean search(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        char[] chars = s.toCharArray();
        TrieNode_0828 c = root;
        int charsI = 0;
        while (charsI < chars.length) {
            if (!c.map.containsKey(chars[charsI])) {
                return false;
            }

            c = c.map.get(chars[charsI]);
            charsI++;
        }

        return c.e != 0;
    }

    public boolean searchPre(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        char[] chars = s.toCharArray();
        TrieNode_0828 c = root;
        int charsI = 0;
        while (charsI < chars.length) {
            if (!c.map.containsKey(chars[charsI])) {
                return false;
            }

            c = c.map.get(chars[charsI]);
            charsI++;
        }

        return true;
    }

    public int getCount(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();
        TrieNode_0828 c = root;
        int charsI = 0;
        while (charsI < chars.length) {
            if (!c.map.containsKey(chars[charsI])) {
                return 0;
            }

            c = c.map.get(chars[charsI]);
            charsI++;
        }

        return c.e;
    }

    public void delete(String s) {
        if (s == null || s.length() == 0 || !search(s)) {
            return;
        }

        root.p++;
        TrieNode_0828 c = root;
        char[] chars = s.toCharArray();
        int charsI = 0;
        while (charsI < chars.length) {
            TrieNode_0828 node = c.map.get(chars[charsI]);
            if(--node.p == 0) {
                c.map.remove(chars[charsI]);
                return;
            }

            c = node;
            charsI++;
        }

        c.e--;
    }

    public static void main(String[] args) {
        Trie_0828 trie = new Trie_0828();
        trie.add("abc");
        System.out.println("search = " + trie.search("abc"));
        System.out.println("searchPre = " + trie.searchPre("abd"));
        System.out.println("getCount = " + trie.getCount("abc"));
        System.out.println("------------");

        trie.add("ab");
        System.out.println("search = " + trie.search("ab"));
        System.out.println("searchPre = " + trie.searchPre("abd"));
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
        System.out.println("searchPre = " + trie.searchPre("abc"));
        System.out.println("getCount = " + trie.getCount("abc"));
        System.out.println("------------");

        trie.delete("abc");
        System.out.println("search = " + trie.search("abc"));
        System.out.println("searchPre = " + trie.searchPre("abc"));
        System.out.println("getCount = " + trie.getCount("abc"));
        System.out.println("------------");

        trie.delete("abc");
        System.out.println("search = " + trie.search("abc"));
        System.out.println("searchPre = " + trie.searchPre("ab"));
        System.out.println("getCount = " + trie.getCount("abc"));
        System.out.println("------------");
    }
}
