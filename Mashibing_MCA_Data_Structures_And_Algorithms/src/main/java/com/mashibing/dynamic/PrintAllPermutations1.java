package com.mashibing.dynamic;

/**
 * 打印一个字符串的全部排列
 * 解释：字符串的全排列就是字符串中各个字符位置的排列组合，各种排列只是顺序不一样，原字符串的字符个数不能减少，不是求子序列。
 * 思路：使用递归思想，关键点在于"恢复现场"。
 *
 * 课程：体系班课时162-163
 */
public class PrintAllPermutations1 {
    public static void printAllPermutations(String str) {
        if(str == null || str.length() == 0) {
            return;
        }

        recurse(str.toCharArray(), 0);
    }

    /**
     *
     * @param charArr 原字符序列
     * @param index 当前考查的位置
     */
    public static void recurse(char[] charArr, int index) {
        // base case：由于index只往后走不往前走，所以当index等于charArr.length时说明所有字符已经考查完了，打印即可
        if(index == charArr.length) {
            System.out.println(new String(charArr));
            return;
        }

        /**
         * 最外层的循环是用来控制全排列的第一个字符的，原序列中的任何一个字符都有机会当第一个字符，每次选择谁来当第一个字符时是在原序列的原始顺序上选的，
         * 所以在每一次循环结束后、下一次循环开始前要"恢复现场"，将字符顺序调整到最初的顺序。
         * 之所以要恢复现场，是因为本算法是在原始的字符顺序charArr中直接进行调整，没有借助额外的数据结构做保存，所以在下一次选择"谁开头"时必须将charArr恢复到初始顺序。
         * 一次循环就代表着以某一个字符开头的所有排列组合全部找出来了，然后再换一个字符开头继续循环。
         */
        for(int i = index; i < charArr.length; i++) {// 子字符串的第一个字符是从i位置开始，向后依次尝试每一个字符去当该子字符串的开头
            swap(charArr, i, index);
            recurse(charArr, index + 1);
            swap(charArr, i, index);// 恢复现场，当时哪两个字符调换了顺序再给换回去
        }
    }

    private static void swap(char[] charArr, int i, int j) {
        char tmp = charArr[i];
        charArr[i] = charArr[j];
        charArr[j] = tmp;
    }

    public static void main(String[] args) {
        printAllPermutations("abc");
    }
}
