package com.mashibing.dailyPractice.round6;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态规划题目5：
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回至少需要多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * 所以返回2，如果所有贴纸都消不掉返回-1。
 */
public class StickersToSpellWord_0903 {
    public int minStickers(String[] stickers, String target) {
        if(stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return 0;
        }

        int i = recurse(stickers, target, new HashMap<>());
        return i == Integer.MAX_VALUE ? -1 : i;
    }

    private int recurse(String[] stickers, String rest, Map<String, Integer> map) {
        if(map.containsKey(rest)) {
            return map.get(rest);
        }

        if("".equals(rest)) {
            return 0;
        }

        int minCount = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String remaining = cut(sticker, rest);
            if(remaining.length() == rest.length()) {
                continue;
            }

            minCount = Math.min(minCount, recurse(stickers, remaining, map));
        }

        int r = minCount == Integer.MAX_VALUE ? Integer.MAX_VALUE : minCount + 1;
        map.put(rest, r);
        return r;
    }

    private String cut(String sticker, String rest) {
        char[] restArr = rest.toCharArray();
        int[] help = new int[26];
        for (char c : restArr) {
            help[c - 'a']++;
        }

        char[] stickerArr = sticker.toCharArray();
        for (char c : stickerArr) {
            help[c - 'a']--;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < help.length; i++) {
            while (help[i]-- > 0) {
                stringBuilder.append((char)(i + 'a'));
            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        // 本题测试链接：https://leetcode.com/problems/stickers-to-spell-word
    }
}
