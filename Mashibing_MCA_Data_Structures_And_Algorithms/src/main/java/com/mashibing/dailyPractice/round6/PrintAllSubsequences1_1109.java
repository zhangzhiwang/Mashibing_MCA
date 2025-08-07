package com.mashibing.dailyPractice.round6;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部子序列（子序列的字符顺序不能颠倒）
 */
public class PrintAllSubsequences1_1109 {
    public static void printAllSubsequences1(String str) {
        if(str == null || str.length() == 0) {
            return;
        }

        recurse(str.toCharArray(), 0, "");
    }

    private static void recurse(char[] chars, int i, String rest) {
        if(i == chars.length) {
            System.out.println(rest);
            return;
        }

        recurse(chars, i + 1, rest + chars[i]);
        recurse(chars, i + 1, rest);
    }

    // 老师的代码
    public static List<String> subs(String s) {
        char[] str = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        process1(str, 0, ans, path);
        return ans;
    }

    // str 固定参数
    // 来到了str[index]字符，index是位置
    // str[0..index-1]已经走过了！之前的决定，都在path上
    // 之前的决定已经不能改变了，就是path
    // str[index....]还能决定，之前已经确定，而后面还能自由选择的话，
    // 把所有生成的子序列，放入到ans里去
    public static void process1(char[] str, int index, List<String> ans, String path) {
        if (index == str.length) {
            ans.add(path);
            return;
        }
        // 没有要index位置的字符
        process1(str, index + 1, ans, path);
        // 要了index位置的字符
        process1(str, index + 1, ans, path + String.valueOf(str[index]));
    }

    public static void main(String[] args) {
        String s = "abc";
        System.out.println("correct ans:");
        System.out.println(subs(s));
        System.out.println("my ans:");
        printAllSubsequences1(s);
    }
}
