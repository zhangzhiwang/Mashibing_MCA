package com.mashibing.greed;

import java.util.HashSet;

/**
 * 道路放灯问题：
 * 给定一个字符串str，改字符串只有‘X’和‘.’两种字符构成，其中：
 * ‘X’表示墙，墙上不能放灯，墙也不透光
 * ‘.’表示道路，可以放灯，也可以不放灯，但必须被照亮
 * 如果在某一个道路的位置上放置了灯，那么它邻近的道路可以被点亮，比如：如果灯放在i位置，可以让i-1，i和i+1三个位置的道路被点亮，
 * 但是如果i位置是墙，由于墙不透光，做在i-1位置放灯并不能照亮i+1位置上的道路。
 * 求照亮str中所有的道路至少需要几盏灯。
 *
 * 思路：
 * 1、设置一个指针i从左到右去遍历str中的每个字符
 * 2、看i位置上的字符是什么：
 * （1）如果i位置上的字符是"X"，根据题目要求不能放灯，所以要到i+1的位置上继续考察
 * （2）如果i位置上的字符是"."，那么还要看i+1位置上的字符是什么，如果i+1上是"X"，那么在i位置必须放灯，i跳到i+2上去考察
 * （3）如果i位置上的字符是"."，i+1上也是"."，那么还要看i+2位置上的字符是什么，如果i+2上也是"."，就需要在i+1上放灯，i跳到i+3上去继续考察
 * （4）如果i位置上的字符是"."，i+1上也是"."，i+2上是"X"，那么在i上或者i+1上放灯都可以，i跳到i+3上去考察
 * 以上过程统计放灯的数量。
 * 3、返回灯的数量
 *
 * 课程：体系班课时120-121
 */
public class Light {
    public static int lightCount(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }

        char[] charArray = str.toCharArray();
        int i = 0;// 设置考察的指针
        int lightCount = 0;
        while(i < charArray.length) {
            if(charArray[i] == 'X') {// i位置是墙，不放灯，对应注释的情况1
                i++;
            } else {// 说明i位置上是"."
                // 看类上面注释写的的思路，对于情况2、3、4无论是哪种情况都需要放一个灯，区别就是灯放在哪个位置而已，所以灯的数量都需要加1
                lightCount++;
                /*
                 由于在i位置上是"."的情况要考察i+1位置上是什么，但是while循环条件只能保证i不越界，不能保证i+1不越界，
                 如果i正好是str的最后一个字符，那么i+1就越界了
                 */
                if(i == charArray.length - 1) {
                    return lightCount;
                }

                if(charArray[i + 1] == 'X') {// 情况2
                    i += 2;
                } else {// 情况3和情况4 i都需要跳到i+3的位置
                    i += 3;
                }
            }
        }

        return lightCount;
    }

    // 以下是对数器
    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static int minLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    public static int process(char[] str, int index, HashSet<Integer> lights) {
        if (index == str.length) { // 结束的时候
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') { // 当前位置是点的话
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else { // str还没结束
            // i X .
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                lights.add(index);
                yes = process(str, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = lightCount(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }
}
