package com.mashibing.dailyPractice.round2._71_to_100._0708;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态规划题目4：
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class ConvertToLetterString_0708 {
    public static int convertToLetterString1(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }

        return recurse1(str.toCharArray(), 0);
    }

    private static int recurse1(char[] charArr, int index) {
        if(index == charArr.length) {
            return 1;
        }
        if(charArr[index] == '0') {
            return 0;
        }

        int p1 = recurse1(charArr, index + 1);
        int p2 = 0;
        if(index + 1 < charArr.length && (charArr[index] - '0') * 10 + (charArr[index + 1] - '0') <= 26) {
            p2 = recurse1(charArr, index + 2);
        }

        return p1 + p2;
    }

    public static int convertToLetterString2(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }

        return recurse2(str.toCharArray(), 0, new HashMap<>());
    }

    private static int recurse2(char[] charArr, int index, Map<Integer, Integer> map) {
        if(map.containsKey(index)) {
            return map.get(index);
        }

        if(index == charArr.length) {
            return 1;
        }
        if(charArr[index] == '0') {
            return 0;
        }

        int p1 = recurse2(charArr, index + 1, map);
        int p2 = 0;
        if(index + 1 < charArr.length && (charArr[index] - '0') * 10 + (charArr[index + 1] - '0') <= 26) {
            p2 = recurse2(charArr, index + 2, map);
        }

        map.put(index, p1 + p2);
        return p1 + p2;
    }

    // 对数器
    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    // str[0..i-1]转化无需过问
    // str[i.....]去转化，返回有多少种转化方法
    public static int process(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }
        // i没到最后，说明有字符
        if (str[i] == '0') { // 之前的决定有问题
            return 0;
        }
        // str[i] != '0'
        // 可能性一，i单转
        int ways = process(str, i + 1);
        if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
            ways += process(str, i + 2);
        }
        return ways;
    }

    // 为了测试
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int N = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = randomString(len);
            int ans0 = number(s);
            int ans1 = convertToLetterString1(s);
            int ans2 = convertToLetterString2(s);
            if (ans0 != ans1 || ans0 != ans2) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
