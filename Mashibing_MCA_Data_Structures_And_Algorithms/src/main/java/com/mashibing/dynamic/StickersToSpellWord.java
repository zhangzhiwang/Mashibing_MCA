package com.mashibing.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态规划题目5：
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串都代表一种字面值的贴纸，每一种字面值的贴纸都有无数张，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * 所以返回2
 *
 * 思路：用每一张贴纸都作为第一张贴纸，然后看看哪个贴纸做第一张的时候整体所用的张数最少，去所有方案的最小值
 *
 * 课程：体系班课时188-191
 */
public class StickersToSpellWord {
    // ---------------版本一：暴力递归版---------------
    /**
     *
     * @param stickers 原始贴纸数组
     * @param target 要拼的目标str
     * @return
     */
    public static int stickersToSpellWord1(String[] stickers, String target) {
        if(stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return -1;
        }

        Map<String, Integer> map = recurse1(stickers, target);
        return map.get("restStrLength") != 0 ? 0 : map.get("count");
    }

    private static Map<String, Integer> recurse1(String[] stickers, String rest) {
        int minCount = Integer.MAX_VALUE;
        String remaining = "";
        for (int i = 0; i < stickers.length; i++) {
            remaining = restStr(rest, stickers[i]);
            if(remaining.length() == 0) {
                Map<String, Integer> retMap = new HashMap<>();
                retMap.put("restStrLength", 0);
                retMap.put("count", 1);
                return retMap;
            }

            if(remaining.length() == rest.length()) {
                continue;
            }

            Map<String, Integer> map = recurse1(stickers, remaining);
            if(map.get("restStrLength") == 0) {
                // 能进入这个if判断说明后续过程把剩余的字符串给消完了，所以要重置remaining为空串
                remaining = "";
                minCount = Math.min(minCount, map.get("count") + 1);
            }
        }

        Map<String, Integer> retMap = new HashMap<>();
        retMap.put("restStrLength", remaining.length());
        retMap.put("count", minCount);

        return retMap;
    }

    /**
     * 用sticker去消target的字符，返回target被削掉后的剩余字符组成的字符串
     * @param target
     * @param sticker
     * @return
     */
    private static String restStr(String target, String sticker) {
        // 由于是内部调用所以不用做参数校验了
        char[] targetArr = target.toCharArray();
        char[] stickerArr = sticker.toCharArray();

        /*
         设置做词频统计的数组，题目说明了每个字符都是小写字母，所以准备长度是26的数组，数组的索引代表到a字符的距离，对应的值代表该字母在字符串中出现的次数。
         比如下标是0代表到a字符的距离是0的字符，也就是该字符就是a字符本身，如果下标是2代表到a字符的距离是2的字符，也就是c字符。
         */
        int[] countArr = new int[26];
        for(int i = 0; i < targetArr.length; i++) {
            countArr[targetArr[i] - 'a']++;
        }
        // 上面的循环走完target字符串里面所有字母的词频已被统计到countArr数组里面了
        /*
         然后同样用countArr数组对sticker做反向词频：对sticker里面的每一个字母在countArr数组里做词频的减法操作，
         减完可能是负数，没关系，因为最后统计的是countArr数组中词频大于0的字母
         */
        for(int i = 0; i < stickerArr.length; i++) {
            countArr[stickerArr[i] - 'a']--;
        }
        // 经过上面的for循环，countArr数组里面次词频大于0的就是target剩余的字符，将剩余的字符拼成字符串，顺序无所谓
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < countArr.length; i++) {
            if(countArr[i] <= 0) {
                continue;
            }

            for(int j = 0; j < countArr[i]; j++) {
                stringBuilder.append((char)(i + 'a'));
            }
        }

        return stringBuilder.toString();
    }
    // -------------------------------------------
    // ---------------版本二：方案一的优化版（仍为暴力递归）---------------
    /*
    该优化版本和方案一的整体思路是一样的，就是把原始贴纸和targe字符串都搞成词频统计的形式，思路比较绕，相对复杂一些，真正面试时如果遇到此题写出方案一即可，
    由于时间关系此版本的实现忽略，待以后补充。
    该版本的课程：体系班课时190-191
    老师的代码：class19.Code03_StickersToSpellWord#minStickers2
     */
    // -------------------------------------------
    // ---------------版本三：最终版---------------
    /*
    通过对上面的递归过程可以分析出影响整个递归过程的因素只有一个，那就是剩余字符串rest，而这个变量是一个字符串，不方便搞出数组的形式，
    即使是转成字符数组那么也不方便填充每一个格子，巨费事，所以舍弃原本的最终版，将傻缓存版就定位最终版。
     */
    public static int stickersToSpellWord2(String[] stickers, String target) {
        if(stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return -1;
        }

        Map<String, Integer> map = recurse2(stickers, target, new HashMap<String, Map<String, Integer>>());
        return map.get("restStrLength") != 0 ? 0 : map.get("count");
    }

    private static Map<String, Integer> recurse2(String[] stickers, String rest, Map<String, Map<String, Integer>> buf) {
        if(buf.get(rest) != null) {
            return buf.get(rest);
        }

        int minCount = Integer.MAX_VALUE;
        String remaining = "";
        for (int i = 0; i < stickers.length; i++) {
            remaining = restStr(rest, stickers[i]);
            if(remaining.length() == 0) {
                Map<String, Integer> retMap = new HashMap<>();
                retMap.put("restStrLength", 0);
                retMap.put("count", 1);

                buf.put(rest, retMap);
                return retMap;
            }

            if(remaining.length() == rest.length()) {
                continue;
            }

            Map<String, Integer> map = recurse1(stickers, remaining);
            if(map.get("restStrLength") == 0) {
                // 能进入这个if判断说明后续过程把剩余的字符串给消完了，所以要重置remaining为空串
                remaining = "";
                minCount = Math.min(minCount, map.get("count") + 1);
            }
        }

        Map<String, Integer> retMap = new HashMap<>();
        retMap.put("restStrLength", remaining.length());
        retMap.put("count", minCount);

        buf.put(rest, retMap);
        return retMap;
    }
    // -------------------------------------------

    public static void main(String[] args) {
//        System.out.println((char)(25 + 'a'));
//        System.out.println('a' - 'a');
//        System.out.println((char)(1 + 'a'));

        String[] stickers = {"ba","c","abcd"};
        String target= "babac";
        int count = stickersToSpellWord1(stickers, target);
        System.out.println(count);

        count = stickersToSpellWord2(stickers, target);
        System.out.println(count);
    }
}