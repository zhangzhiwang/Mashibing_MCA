package com.mashibing.dynamic;

/**
 * 动态规划题目4：
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 *
 * 注意：如果当前位置上的字符是数字0，根据题目可以看出能转成字母的最小的数字是1，最大是26，所以0自己是不能转成任何字母的，0和后面的数字拼起来也不能转成任何字母，
 * 比如"05"不能等同于数字"5"从而转成字母e。比如数字"101"有三种情况：
 * a）1+0+1，由于0不能转成任何字母，和后面的数字联合起来也不能转，所以该方案不存在
 * b）10+1，,10可以转换成字母J，1可以准换为A，所以可以转换为JA，算一种方案
 * c）1+01，上面说了"01"不能等同于"1"，所以该方案不存在
 * 综上"101"这个字符串只能有1种转换结果。
 *
 * 思路：字符串按字符从左往右尝试，每一个字符x都有两种可能：当前字符自己可以单独转成一个字母，当前字符和下一个字符联合起来转成一个字母，将所有情况加起来返回。
 * 整个递归的设计思想：当前考查到index位置上的字符，index-1位置及之前的字符不用管，因为已经考察完了，现在就看从index位置开始往后的字符有多少种转换结果并返回。
 * 递归的base case有两个：一个是当走到字符串最后时说明找到了一种结果返回1，一个是当前字符是0时说明当前路走不通返回0。
 *
 * 课程：体系班课时185-187
 */
public class ConvertToLetterString {
    // ---------------版本一：暴力递归版---------------
    public static int convertToLetterString1(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }

        return recurse1(str.toCharArray(), 0);
    }

    /**
     * 当前考查到index位置上的字符，index-1位置及之前的字符不用管，因为已经考察完了，现在就看从index位置开始往后的字符有多少种转换结果并返回
     * @param charArr 为原始str字符串转换的字符数组
     * @param index 当前考查到字符数组的index位置
     * @return
     */
    private static int recurse1(char[] charArr, int index) {
        // base case：有两个base case：一个是当走到字符串最后时，一个是当前字符是0
        // base case1：
        // 当index越界时说明已经走完了整个字符串，说明前面的所有字符都能够转换成字母，如果中间某一个位置不能转换早已经退出了，能走到最后说明肯定存在1种转换方法
        if(index == charArr.length) {
            return 1;// 注意：这里返回的是1不是0，因为走到最后肯定是存在一种转换方法的
        }

        // base case2：
        if(charArr[index] == '0') {
            return 0;
        }

        // 分两种情况：一个是当前位置的字符自己能够单独转成一个字母，一个是当前位置和下一个位置联合起来能转成一个字母
        // 单转
        int count = recurse1(charArr, index + 1);
        // 和后面的联合转
        /*
        下一个位置不越界，且当前位置和下一个位置拼起来的数字小于等于26
        注意判断的时候是当前位置的字符数字减去0的ascii码的差值然后去拼成一个两位数，不能用字符数字的ascii码直接乘以10
         */
        if(index + 1 < charArr.length && (charArr[index] - '0') * 10 + (charArr[index + 1] - '0') < 27) {
            count += recurse1(charArr, index + 2);
        }

        return count;
    }
    // -------------------------------------------
    // ---------------版本三：最终版---------------
    /*
    通过版本一的递归过程可以看到，影响整个递归过程只有一个变量index，也就是傻缓存是一个一维数组，将其平铺开来只有一个横坐标，
    通过递归的过程可以看到任意一个格子都依赖于它后面的格子和后两个格子，所以填入的顺序一定是从右往左，这样就可以忽略掉第二个傻缓存版本直接该写出最终版。
     */
    public static int convertToLetterString2(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }

        char[] charArr = str.toCharArray();
        int N = charArr.length;
        int[] buf = new int[N + 1];// 通过看递归的base case，index是可以到N的，所以数组的长度是N+1
        // 通过看递归的base case1可知缓存数组的N位置上是1
        buf[N] = 1;

        // 由于填入的顺序是从右往左填，所以要倒序遍历buf数组
        for(int i = N - 1; i >= 0; i--) {// 由于上面已经将N位置赋值了，所以这里从N-1位置开始
            // 这里是base case2，但是由于buf数组的元素默认就是0，所以没有必要再赋值为0了
//            if(charArr[i] == 0) {
//                buf[i] = 0;
//            }

            if(charArr[i] != 0) {
                buf[i] = buf[i + 1];
                if(i + 1 < N && (charArr[i] - '0') * 10 + (charArr[i + 1] - '0') < 27) {
                    buf[i] += buf[i + 2];
                }
            }
        }

        return buf[0];
    }

    public static void main(String[] args) {
//        int i = ('2' - '0') * 10 + ('3' - '0');
//        System.out.println(i);
//        System.out.println('2' * 10);

        String str = "111";
        int count = convertToLetterString1(str);
        System.out.println(count);
        count = convertToLetterString2(str);
        System.out.println(count);
    }
}
