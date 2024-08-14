package com.mashibing.dailyPractice.round4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 打印一个字符串的全部子序列（子序列的字符顺序不能颠倒），要求打印的子序列不能重复
 */
public class PrintAllSubsequences2_0731 {
    public static void printAllSubsequences2(String s) {
        if(s == null || s.length() == 0) {
            return;
        }

        recurse(s.toCharArray(), 0, "", new HashSet<String>());
    }

    private static void recurse(char[] chars, int i, String str, Set<String> set) {
        if(i == chars.length) {
            if(!set.contains(str)) {
                System.out.println(str);
                set.add(str);
            }
            return;
        }

        recurse(chars, i + 1, str + chars[i], set);
        recurse(chars, i + 1, str, set);
    }

    // 老师的代码
    public static List<String> subsNoRepeat(String s) {
        char[] str = s.toCharArray();
        String path = "";
        HashSet<String> set = new HashSet<>();
        process2(str, 0, set, path);
        List<String> ans = new ArrayList<>();
        for (String cur : set) {
            ans.add(cur);
        }
        return ans;
    }

    public static void process2(char[] str, int index, HashSet<String> set, String path) {
        if (index == str.length) {
            set.add(path);
            return;
        }
        String no = path;
        process2(str, index + 1, set, no);
        String yes = path + String.valueOf(str[index]);
        process2(str, index + 1, set, yes);
    }

    public static void main(String[] args) {
        String s = "acc";
        System.out.println("correct ans:");
        System.out.println(subsNoRepeat(s));
        System.out.println("my ans:");
        printAllSubsequences2(s);
    }
}