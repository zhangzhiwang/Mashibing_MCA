package com.mashibing.dynamic;

/**
 * 打印一个字符串的全部子序列（子序列的字符顺序不能颠倒）
 * 说明：子序列的字符顺序不能颠倒，就是假设a和b都是原始字符串中的两个字符，a在b的左边，子序列不能出现a在b右边的情况。
 * 思路：使用递归思想，原字符串中的每一个字符在子序列中都有两种情况：要它和不要它。
 *
 * 课程：体系班课时161
 */
public class PrintAllSubsequences1 {
    public static void findAllSubsequences(String str) {
        if(str == null || str.length() == 0) {
            return;
        }

        recurse(str.toCharArray(), 0, "");
    }

    /**
     *
     * @param charArr 原始字符序列
     * @param index index为charArr的索引，代表当前考查的是index位置上的字符
     * @param path index位置之前生成的子序列的结果，即charArr[0...index-1]位置上已经生成的子序列结果，是不能改变的。
     *             可以理解为：当考查到index位置上的字符时，index之前的所有字符生成的子序列都已经生成好了，都在path上，path是不可以改变的事实，
     *             当前能决定的是index位置及以后的字符生成的子序列
     */
    private static void recurse(char[] charArr, int index, String path) {
        // base case：当index移出数组范围时，说明所有的字符都已经考察完了，子序列都已经生成完了，就在path上，打印path就可以了
        if(index == charArr.length) {
            System.out.println(path);
            return;
        }

        /*
         走到这里说明index是charArr中间的某个位置，既然index之前生成的序列都在path上，已经成为了事实，改变不了，
         那么现在能决定的是从index开始往后的子序列怎么生成，即能决定的范围是[index,charArr.length - 1]，
         所以index只往后走，不往前走。下面的两个递归分别是："不要index位置上的字符，然后继续往后考察"和"要了index位置上的字符再继续往后考查"
         */
        recurse(charArr, index + 1, path);// 不要index位置上的字符，所以path保持不变，继续从index+1的位置往后考查
        recurse(charArr, index + 1, path + charArr[index]);// 要了index位置上的字符然后继续从index+1的位置往后考查，所以path后面要把index上的字符拼接上
    }

    public static void main(String[] args) {
        String str = "acc";
        findAllSubsequences(str);
//        f(str);
//        System.out.println("原始str:" + str);
    }

    private static void f(String str) {
        str += "123";
        System.out.println("f:" + str);
    }
}
