package com.mashibing.dailyPractice.round2._71_to_100._0708;

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
public class StickersToSpellWord_0708 {
    public int minStickers(String[] stickers, String target) {
        if(stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return 0;
        }

        int result = recurse(stickers, target, new HashMap<String, Integer>());
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int recurse(String[] arr, String rest, Map<String, Integer> map) {
        if(map.containsKey(rest)) {
            return map.get(rest);
        }

        if("".equals(rest)) {
            return 0;
        }

        int count = Integer.MAX_VALUE;
        for (String sticker : arr) {
            String last = cut(sticker, rest);
            if(last.length() == rest.length()) {
                continue;
            }

            count = Math.min(count, recurse(arr, last, map));
        }

        count = count == Integer.MAX_VALUE ? Integer.MAX_VALUE : count + 1;
        map.put(rest, count);
        return count;
    }

    private String cut(String sticker, String rest) {
        int[] help = new int[26];
        for (char c : rest.toCharArray()) {
            help[c-'a']++;
        }

        for (char c : sticker.toCharArray()) {
            help[c-'a']--;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < help.length; i++) {
            while(help[i]-- > 0) {
                stringBuilder.append((char)('a' + i));
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        // 本题测试链接：https://leetcode.com/problems/stickers-to-spell-word
    }
}
