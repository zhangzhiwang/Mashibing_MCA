package com.mashibing.dailyPractice.round5;

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
public class StickersToSpellWord_0808 {
    public int minStickers1(String[] stickers, String target) {
        if(stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return 0;
        }

        int i = recurse1(stickers, target);
        return i == Integer.MAX_VALUE ? -1 : i;
    }

    private static int recurse1(String[] stickers, String rest) {
        if("".equals(rest)) {
            return 0;
        }

        int minCount = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String remaining = cut(sticker, rest);
            if(remaining.length() == rest.length()) {
                continue;
            }

            int count = recurse1(stickers, remaining);
            minCount = Math.min(minCount, count);
        }

        return minCount == Integer.MAX_VALUE ? Integer.MAX_VALUE : minCount + 1;
    }

    private static String cut(String sticker, String rest) {
        char[] restChars = rest.toCharArray();
        int[] help = new int[26];
        for (int i = 0; i < restChars.length; i++) {
            help[restChars[i] - 'a']++;
        }

        char[] stickerChars = sticker.toCharArray();
        for (char c : stickerChars) {
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

    public int minStickers2(String[] stickers, String target) {
        if(stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return 0;
        }

        int i = recurse2(stickers, target, new HashMap<String, Integer>());
        return i == Integer.MAX_VALUE ? -1 : i;
    }

    private static int recurse2(String[] stickers, String rest, Map<String, Integer> map) {
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

            int count = recurse2(stickers, remaining, map);
            minCount = Math.min(minCount, count);
        }

        int result = minCount == Integer.MAX_VALUE ? Integer.MAX_VALUE : minCount + 1;
        map.put(rest, result);
        return result;
    }

    public static void main(String[] args) {
        // 本题测试链接：https://leetcode.com/problems/stickers-to-spell-word
    }
}
