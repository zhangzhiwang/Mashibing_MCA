package com.mashibing.tree;

/**
 * 前缀树
 * 课程：体系班课时65-67
 * 思路：见com.mashibing.preInEclipse.senior.tree.trie.Trie注释
 */
public class Trie {
   public static TrieNode root = new TrieNode();

    static class TrieNode {
        public int pass;
        public int end;
        public TrieNode[] nexts = new TrieNode[26];// 假定存放的字符只有26个小写字母
    }

    public static void add(String str) {
        if(str == null || "".equals(str)) {
            return;
        }

        char[] charArray = str.toCharArray();
        TrieNode cur = root;
        cur.pass++;
        for(int i = 0; i < charArray.length; i++) {
            if(cur.nexts[charArray[i]-'a'] == null) {
                cur.nexts[charArray[i]-'a'] = new TrieNode();
            }

            cur.nexts[charArray[i]-'a'].pass++;
            if(i == charArray.length - 1) {
                cur.nexts[charArray[i]-'a'].end++;
            }
            cur = cur.nexts[charArray[i]-'a'];
        }
    }

    /**
     * 查找某一个字符串是否存在
     * @param str
     * @return
     */
    public static boolean search(String str) {
        if(str == null || str.length() == 0) {
            return false;
        }

        char[] charArray = str.toCharArray();
        TrieNode cur = root;
        for(int i = 0; i < charArray.length; i++) {
            if(cur.nexts[charArray[i] - 'a'] == null) {
                return false;
            }

            if(i == charArray.length - 1) {
                return cur.nexts[charArray[i] - 'a'].end != 0;
            }
            cur = cur.nexts[charArray[i] - 'a'];
        }

        return false;
    }

    /**
     * 有多少字符串是以str开头的
     * @param str 前缀字符串
     * @return
     */
    public static int getPrefixNum(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }

        char[] charArray = str.toCharArray();
        TrieNode cur = root;
        for(int i = 0; i < charArray.length; i++) {
            if(cur.nexts[charArray[i] - 'a'] == null) {
                return 0;
            }

            if(i == charArray.length - 1) {
                return cur.nexts[charArray[i] - 'a'].pass;
            }

            cur = cur.nexts[charArray[i] - 'a'];
        }

        return 0;
    }

    public static void deleteStr(String str) {
        if(str == null || str.length() == 0) {
            return;
        }

        if (!search(str)) {
            return;
        }

        char[] charArray = str.toCharArray();
        TrieNode cur = root;
        cur.pass--;
        for(int i = 0; i < charArray.length; i++) {
            // 不用判断是否为空，因为上面已经search过了
            if(--cur.nexts[charArray[i] - 'a'].pass == 0) {
                cur.nexts[charArray[i] - 'a'] = null;
                return;
            }

            if(i == charArray.length - 1) {
                cur.nexts[charArray[i] - 'a'].end--;
            }

            cur = cur.nexts[charArray[i] - 'a'];
        }
    }

    public static void main(String[] args) {
        Trie.add("abc");
        System.out.println(Trie.search("abc"));
        System.out.println(Trie.getPrefixNum("abc"));
        System.out.println("----------");

        Trie.add("abc");
        System.out.println(Trie.search("abc"));
        System.out.println(Trie.getPrefixNum("abc"));
        System.out.println("----------");

        Trie.add("absk");
        System.out.println(Trie.search("absk"));
        System.out.println(Trie.getPrefixNum("abs"));
        System.out.println("----------");

        Trie.deleteStr("abc");
        System.out.println(Trie.search("abc"));
        System.out.println(Trie.getPrefixNum("abc"));
        System.out.println("----------");

        Trie.deleteStr("abc");
        System.out.println(Trie.search("abc"));
        System.out.println(Trie.getPrefixNum("abc"));
        System.out.println("----------");

        Trie.deleteStr("absk");
        System.out.println(Trie.search("absk"));
        System.out.println(Trie.getPrefixNum("abs"));
        System.out.println("----------");
    }
}
