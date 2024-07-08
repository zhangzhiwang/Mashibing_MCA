package com.mashibing.greed;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * 贪心算法
 * 第一道贪心算法题：给定一个字符串数组，你可以按照任意顺序将数组的所有字符串都拼接起来组成一个大字符串，返回大字符串中字典序最小的那个。
 * 解释：
 * 1、字典序：两个字符串的大小关系取决于它们从左到右第一个不同字符的ASCII值大小关系，比如："abcd"的字典序就比"abfg"的要小，因为第三个字符"c"的字典序要小于"f"。
 * 当字符串长度不一样时，较短的字符串后面要进行补位，补上比最小的ascii码还要小的值，将长度补齐后再进行比较。
 * 为什么从左到右第一个字符不一样的时候就决定整个字符串的字典序呢？可以把字符串每一个字符看做一个数字，这个数字就是该字符的ascii码，就相当于比较两个阿拉伯数字的大小，
 * 当位数一样的时候，当高位决出大小的时候就不用再比较低位了，整个数字的大小就比较出来了，唯一和数字比较不一样的是：当长度不一样时，字符串比较是往较短的字符串后面补位，
 * 而数字比较是往长度较小的数字前面补位。
 * 2、数组每一个元素的字符串都是原子的，一个元素的字符串不能拆开插入别的字符串也不能内部调换顺序，只能作为整体排序。
 *
 * 贪心算法：你提出一个能够解决局部问题的方案，这个方案推广到全局也能适用，即可以解决全局问题，这个方案就是贪心算法。
 * 举例：你以某个视角提出了一个局部问题的解决方案，比如你是一名部门经理，你们部门一共有100个人，你管理你们部门管理得非常好，你总结出一个管理方法论，
 * 按照你的方法轮管理一个部门非常有效，但是这个方法轮推广到全公司适不适用还有待验证，你的这个方法轮就是贪心算法。如果你的方法轮对全公司也适用，
 * 能够成功解决全公司的管理问题，那么你的贪心算法就"贪"对了，否则就"贪"错了，说明你的贪心算法只适用于局部问题。
 * 再或者说你在一个局部问题上提出了一个最优解，这个最优解就是你的贪心算法，你认为推广到范围更大的全局问题上它也是最优解。
 * 贪心算法一共分为三步：
 * 1、算法的提出，也就是上面说的解决方案
 * 2、证明提的方案能够解决全局问题
 * 3、在证明通过的前提下开始实施，也就是写代码
 * 上面是正规流程。但是，在实际面试或笔试中，由于时间、精力等各方面的限制，你在面试现场把上面的三个步骤走下来时间肯定不够用，证明你方案的正确性就很耗时，
 * 所以在面试时先提出方案，然后直接写代码，写完代码写对数器，如果对数器验证不通过说明方案肯定不对，去想另一个方案，然后重复上面的过程，直到对数器验证通过为止。
 *
 * 所有贪心算法的题最终的代码都巨简单，难点在于你提出的局部问题的解正好也是全局问题的解，你的贪心算法在全局问题上能"贪对"这个事巨难。
 *
 * 思路：写一个比较器，让数组相邻两个字符串做ascii比较，做正序排序，在比较时不是拿相邻的两个字符串直接比较，而是先正反拼接之后再比较；比较完之后形成一个全新顺序的数组，
 * 然后按照排序后的顺序拼在一起即可，最终拼接成的大字符串就是字典序最小的。
 * 比如：[a,b,c]，先让a和b做拼接，拼接成ab，然后反过来拼接，拼接成ba，然后ab和ba做ascii比较，正序排序。
 * 具体思路的证明请看视频。
 *
 * 课程：体系班课时110-112
 */
public class LowestLexicography {
    /**
     *
     * @param arr 原字符串数组
     * @return 最后拼接成的大字符串
     */
    public static String lowestLexicography(String[] arr) {
        if(arr == null || arr.length == 0) {
            return "";
        }

        Arrays.sort(arr, new StrComparator());

        String result = "";
        for(int i = 0; i < arr.length; i++) {
            result += arr[i];
        }

        return result;
    }

    static class StrComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
//            return o1.compareTo(o2);// 直接比较两个字符串的ascii就错了

            // 整个题目算法最难得地方就在这里，你要能想到是数组相邻元素正反拼接后去比较，然后证明这种算法的正确性，这个事儿巨难
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    // 以下是对数器
    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static String lowestString1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        TreeSet<String> ans = process(strs);
        return ans.size() == 0 ? "" : ans.first();
    }

    // strs中所有字符串全排列，返回所有可能的结果
    public static TreeSet<String> process(String[] strs) {
        TreeSet<String> ans = new TreeSet<>();
        if (strs.length == 0) {
            ans.add("");
            return ans;
        }
        for (int i = 0; i < strs.length; i++) {
            String first = strs[i];
            String[] nexts = removeIndexString(strs, i);
            TreeSet<String> next = process(nexts);
            for (String cur : next) {
                ans.add(first + cur);
            }
        }
        return ans;
    }

    // {"abc", "cks", "bct"}
    // 0 1 2
    // removeIndexString(arr , 1) -> {"abc", "bct"}
    public static String[] removeIndexString(String[] arr, int index) {
        int N = arr.length;
        String[] ans = new String[N - 1];
        int ansIndex = 0;
        for (int i = 0; i < N; i++) {
            if (i != index) {
                ans[ansIndex++] = arr[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestString1(arr1).equals(lowestLexicography(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
