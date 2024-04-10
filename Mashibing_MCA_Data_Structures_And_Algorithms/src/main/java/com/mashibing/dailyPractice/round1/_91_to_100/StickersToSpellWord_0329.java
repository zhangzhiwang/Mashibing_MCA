package com.mashibing.dailyPractice.round1._91_to_100;

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
public class StickersToSpellWord_0329 {
    public int minStickers1(String[] stickers, String target) {
        if(stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return -1;
        }

        int count = recurse1(stickers, target);
        return count == Integer.MAX_VALUE ? -1 : count;
    }

    private int recurse1(String[] stickers, String rest) {
        if(rest.length() == 0) {
            return 0;
        }

        int restMinCount = Integer.MAX_VALUE;
        for (String first : stickers) {
            String remaining = minus(first, rest);
            if(remaining.equals(rest)) {
                continue;
            }

            int count = recurse1(stickers, remaining);
            restMinCount = Math.min(restMinCount, count);
        }

        return restMinCount == Integer.MAX_VALUE ? restMinCount : restMinCount + 1;
    }

    private String minus(String sticker, String target) {
        char[] stickerCharArr = sticker.toCharArray();
        char[] targetCharArr = target.toCharArray();
        int[] countArr = new int[26];

        for (int i = 0; i < targetCharArr.length; i++) {
            countArr[targetCharArr[i] - 'a']++;
        }

        for (int i = 0; i < stickerCharArr.length; i++) {
            countArr[stickerCharArr[i] - 'a']--;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < countArr.length; i++) {
            if(countArr[i] <= 0) {
                continue;
            }

            for (int j = 0; j < countArr[i]; j++) {
                stringBuilder.append((char)(i + 'a'));
            }
        }

        return stringBuilder.toString();
    }

    public int minStickers2(String[] stickers, String target) {
        if(stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return -1;
        }

        int count = recurse2(stickers, target, new HashMap<String, Integer>());
        return count == Integer.MAX_VALUE ? -1 : count;
    }

    private int recurse2(String[] stickers, String rest, Map<String, Integer> map) {
        if(map.get(rest) != null) {
            return map.get(rest);
        }

        if(rest.length() == 0) {
            return 0;
        }

        int restMinCount = Integer.MAX_VALUE;
        for (String first : stickers) {
            String remaining = minus(first, rest);
            if(remaining.equals(rest)) {
                continue;
            }

            int count = recurse2(stickers, remaining, map);
            restMinCount = Math.min(restMinCount, count);
        }

        int result = restMinCount == Integer.MAX_VALUE ? restMinCount : restMinCount + 1;
        map.put(rest, result);
        return result;
    }

    public static void main(String[] args) {
        // 本题测试链接：https://leetcode.com/problems/stickers-to-spell-word

        System.out.println(new StickersToSpellWord_0329().minStickers1(new String[]{"with","example","science"}, "thehat"));
    }
}
