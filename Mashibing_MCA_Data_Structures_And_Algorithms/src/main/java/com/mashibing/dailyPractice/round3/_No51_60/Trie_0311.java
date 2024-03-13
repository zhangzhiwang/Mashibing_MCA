package com.mashibing.dailyPractice.round3._No51_60;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树：实现add、search、searchPre、getCount、delete方法
 */
public class Trie_0311 {
    static class TrieNode {
        private int pass;
        private int end;
        private Map<Character, TrieNode> map = new HashMap<>();
    }

    private TrieNode root = new TrieNode();

    public void add(String str) {
        if(str == null || str.length() == 0) {
            return;
        }

        root.pass++;
        TrieNode cur = root;
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            TrieNode trieNode = cur.map.get(c);
            if(trieNode == null) {
                trieNode = new TrieNode();
                cur.map.put(c, trieNode);
            }

            trieNode.pass++;
            cur = trieNode;
        }
        cur.end++;
    }

    public boolean search(String str) {
        if(str == null || str.length() == 0) {
            return false;
        }

        TrieNode cur = root;
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            TrieNode trieNode = cur.map.get(c);
            if(trieNode == null) {
                return false;
            }

            cur = trieNode;
        }

        return cur.end > 0;
    }

    public boolean searchPre(String str) {
        if(str == null || str.length() == 0) {
            return false;
        }

        TrieNode cur = root;
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            TrieNode trieNode = cur.map.get(c);
            if(trieNode == null) {
                return false;
            }

            cur = trieNode;
        }

        return true;
    }

    public int getCount(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }

        TrieNode cur = root;
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            TrieNode trieNode = cur.map.get(c);
            if(trieNode == null) {
                return 0;
            }

            cur = trieNode;
        }

        return cur.end;
    }

    public void delete(String str) {
        if(str == null || str.length() == 0) {
            return;
        }

        if(!search(str)) {
            return;
        }

        root.pass--;
        TrieNode cur = root;
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            TrieNode trieNode = cur.map.get(c);
            trieNode.pass--;
            if(trieNode.pass == 0) {
                cur.map.remove(c);
                trieNode = null;
                break;
            }
            cur = trieNode;
        }
        cur.end--;
    }

    public static void main(String[] args) {
        Trie_0311 t = new Trie_0311();
        t.add("ab");
        t.add("a");
        t.add("abc");

        System.out.println(t.searchPre("a"));
        System.out.println(t.getCount("abc"));
        System.out.println("------------");

        t.delete("ab");
        System.out.println(t.searchPre("ab"));
    }
}
