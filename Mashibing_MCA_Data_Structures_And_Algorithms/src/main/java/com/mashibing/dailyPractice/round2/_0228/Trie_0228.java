package com.mashibing.dailyPractice.round2._0228;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树：实现add、search、searchPre、getCount、delete方法
 */
public class Trie_0228 {
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

        TrieNode cur = root;
        cur.pass++;
        char[] charArray = str.toCharArray();
        for(char c : charArray) {
            if(!cur.map.containsKey(c)) {
                TrieNode node = new TrieNode();
                cur.map.put(c, node);
            }
            cur = cur.map.get(c);
            cur.pass++;
        }
        cur.end++;
    }

    public boolean search(String str) {
        if(str == null || str.length() == 0) {
            return true;
        }

        TrieNode cur = root;
        char[] charArray = str.toCharArray();
        for(char c : charArray) {
            if(!cur.map.containsKey(c)) {
                return false;
            }

            cur = cur.map.get(c);
        }

        return cur.end > 0;
    }

    public boolean searchPre(String str) {
        if(str == null || str.length() == 0) {
            return true;
        }

        TrieNode cur = root;
        char[] charArray = str.toCharArray();
        for(char c : charArray) {
            if(!cur.map.containsKey(c)) {
                return false;
            }

            cur = cur.map.get(c);
        }

        return cur.pass > 0;
    }

    public int getCount(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }

        TrieNode cur = root;
        char[] charArray = str.toCharArray();
        for(char c : charArray) {
            if(!cur.map.containsKey(c)) {
                return 0;
            }

            cur = cur.map.get(c);
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

        TrieNode cur = root;
        TrieNode pre = cur;
        cur.pass--;
        char[] charArray = str.toCharArray();
        for(char c : charArray) {
            cur = cur.map.get(c);
            cur.pass--;
            if(cur.pass == 0) {
                pre.map.remove(c);
                return;
            }
            pre = cur;
        }
        cur.end--;
    }

    public static void main(String[] args) {
        String[] strings = {"ab","a","abc"};
        Trie_0228 trie = new Trie_0228();
        for(String s : strings) {
            trie.add(s);
        }

        for(String s : strings) {
            System.out.print(trie.search(s) + "\t");
        }
        System.out.println();

        for(String s : strings) {
            System.out.print(trie.getCount(s) + "\t");
        }
        System.out.println();

        trie.delete("absdf");
        for(String s : strings) {
            System.out.print(trie.search(s) + "\t");
        }
        System.out.println();

        System.out.println(trie.searchPre("b"));
    }
}
