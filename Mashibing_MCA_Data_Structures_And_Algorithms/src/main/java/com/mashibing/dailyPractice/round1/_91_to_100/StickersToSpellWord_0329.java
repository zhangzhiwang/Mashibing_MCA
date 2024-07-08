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
        return count == Integer.MAX_VALUE ? -1 : count;// 根据leetcode题目要求，如果消不掉则返回-1，所以这里要做判断，不要忘了，不能把Integer.MAX_VALUE返回出去
    }

    private int recurse1(String[] stickers, String rest) {
        if(rest.length() == 0) {
            return 0;
        }

        int restMinCount = Integer.MAX_VALUE;
        /*
        for循环里面的思想：
        假如最终结果需要至少使用数组里面的三张贴纸才能把目标字符串消掉，假设这三张贴纸为a、b、c，并假设a使用了1张，b使用了两张，c使用了3张。
        由于题目要求的是最少使用几张，并没有规定使用的贴纸的顺序，所以对最终能用到的每一张贴纸来说先用和后用是一样的。就拿上面的c贴纸来举例子，
        既然最终用到了c贴纸，那么上来就先使用c贴纸和最后才使用c贴纸，对于最终的结果来说都是一样的，因为最终求的是数量，所以不妨让使用c贴纸的时刻先到来。
        所以for循环的思想就是，先让每一张贴纸都作为第一张，如果第一张贴纸一个字符也消不掉，那以后也不会用到该贴纸了，证明这张贴纸压根儿就用不到；
        如果第一张贴纸能够至少消掉一个字符，说明第一张贴纸可能是有用的，有可能作为最终结果的成员。在第一张贴纸消完之后字符串的剩余部分继续递归上面的过程，
        因为每一张贴纸都有无数张可供使用。
        所以，整个for循环的思路为：
        让数组里面的每一张贴纸都作为第一张去使用，如果第一张不能消掉任何一个字符，说明这张贴纸在整个过程中都不能使用，直接跳过，没必要继续考察后面的过程了
        （其实这情种况下再继续考察后面的过程实际上就是让其它贴纸作为第一张的情况，所以没必要继续考察了）；如果可以消掉至少一个字符，那么就继续考察后面的过程，
        收集上来在该贴纸作为第一张的情况下后续还需要几张贴纸能够消掉整个字符串。每张贴纸作为第一张的情况下，都收集上来自己后续还需贴纸的数量，然后这些方案取个最小值，
        即后续所需贴纸的最少数量，也就是restMinCount。这里要注意的是for循环里考查的是后续所需的最少张数，没有算第一张贴纸本身，返回给上一级前要把自己（第一张贴纸）
        的数量给加上
         */
        for (String first : stickers) {
            String remaining = minus(first, rest);
            /*
             这里使用equals来判断有一个前提，就是minus方法里面如果没有消掉任何字符最终stringBuilder生成的字符串要和原来的字符顺序一样，
             如果不能保证这一点，可以比较字符串的长度来判断是不是消掉字符了
             */
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
                stringBuilder.append((char)(i + 'a'));// 这里一定要将i + 'a'转换成字符再拼接，否则拼接的是int数值
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
