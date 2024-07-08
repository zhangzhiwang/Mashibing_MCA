package com.mashibing.dailyPractice.round2._71_to_100;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态规划题目5：
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * 所以返回2，如果所有贴纸都消不掉返回-1。
 */
public class StickersToSpellWord_0513 {
    public int minStickers1(String[] stickers, String target) {
        if(stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return 0;
        }

        int count = recurse1(target, stickers);
        return count == Integer.MAX_VALUE ? -1 : count;
    }

    private int recurse1(String rest, String[] stickers) {
        if("".equals(rest)) {
            return 0;
        }

        int restMinCount = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String last = minus(sticker, rest);
            if(last.length() == rest.length()) {
                continue;
            }

            int c = recurse1(last, stickers);
            restMinCount = Math.min(c, restMinCount);
        }

        return restMinCount == Integer.MAX_VALUE ? restMinCount : restMinCount + 1;
    }

    public int minStickers2(String[] stickers, String target) {
        if(stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return 0;
        }

        int count = recurse2(target, stickers, new HashMap<String, Integer>());
        return count == Integer.MAX_VALUE ? -1 : count;
    }

    private int recurse2(String rest, String[] stickers, Map<String, Integer> map) {
        if(map.containsKey(rest)) {
            return map.get(rest);
        }

        if("".equals(rest)) {
            return 0;
        }

        int restMinCount = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String last = minus(sticker, rest);
            if(last.length() == rest.length()) {
                continue;
            }

            int c = recurse2(last, stickers, map);
            restMinCount = Math.min(c, restMinCount);
        }

        int tolCount = restMinCount == Integer.MAX_VALUE ? restMinCount : restMinCount + 1;
        map.put(rest,tolCount);
        return tolCount;
    }

    private String minus(String sticker, String rest) {
        char[] stickerArr = sticker.toCharArray();
        char[] restArr = rest.toCharArray();
        int[] help = new int[26];

        for (char c : restArr) {
            help[c - 'a']++;
        }

        for (char c : stickerArr) {
            help[c - 'a']--;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < help.length; i++) {
            if(help[i] <= 0) {
                continue;
            }

            for (int j = help[i]; j > 0 ; j--) {
                stringBuilder.append((char)(i + 'a'));
            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        // 本题测试链接：https://leetcode.com/problems/stickers-to-spell-word

//        int[] help = new int[26];
//        help[0] = 1;
//        help[1] = 2;
//
//        char c = 'a';
//        System.out.println(help[c - 'a']);

        String[] stickers = {"with","example","science"};
        new StickersToSpellWord_0513().minStickers1(stickers, "thehat");
    }
}
