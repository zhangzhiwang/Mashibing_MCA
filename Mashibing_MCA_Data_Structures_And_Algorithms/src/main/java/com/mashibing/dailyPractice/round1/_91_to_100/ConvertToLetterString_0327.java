package com.mashibing.dailyPractice.round1._91_to_100;

/**
 * 动态规划题目4：
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果。
 */
public class ConvertToLetterString_0327 {
    public static int convertToLetterString(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }

        return recurse(str.toCharArray(), 0);
    }

    private static int recurse(char[] charArr, int index) {
        if(index == charArr.length) {
            return 1;
        }
        if(charArr[index] == '0') {
            return 0;
        }

        int count = recurse(charArr, index + 1);
        if(index + 1 < charArr.length && (charArr[index] - '0') * 10 + (charArr[index + 1] - '0') < 27) {
            count += recurse(charArr, index + 2);
        }
        return count;
    }

    // 从右往左的动态规划
    // 就是上面方法的动态规划版本
    // dp[i]表示：str[i...]有多少种转化方式
    public static int dp1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (str[i] != '0') {
                int ways = dp[i + 1];
                if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
                    ways += dp[i + 2];
                }
                dp[i] = ways;
            }
        }
        return dp[0];
    }

    // 从左往右的动态规划
    // dp[i]表示：str[0...i]有多少种转化方式
    public static int dp2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        if (str[0] == '0') {
            return 0;
        }
        int[] dp = new int[N];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            if (str[i] == '0') {
                // 如果此时str[i]=='0'，那么他是一定要拉前一个字符(i-1的字符)一起拼的，
                // 那么就要求前一个字符，不能也是‘0’，否则拼不了。
                // 前一个字符不是‘0’就够了嘛？不够，还得要求拼完了要么是10，要么是20，如果更大的话，拼不了。
                // 这就够了嘛？还不够，你们拼完了，还得要求str[0...i-2]真的可以被分解！
                // 如果str[0...i-2]都不存在分解方案，那i和i-1拼成了也不行，因为之前的搞定不了。
                if (str[i - 1] == '0' || str[i - 1] > '2' || (i - 2 >= 0 && dp[i - 2] == 0)) {
                    return 0;
                } else {
                    dp[i] = i - 2 >= 0 ? dp[i - 2] : 1;
                }
            } else {
                dp[i] = dp[i - 1];
                if (str[i - 1] != '0' && (str[i - 1] - '0') * 10 + str[i] - '0' <= 26) {
                    dp[i] += i - 2 >= 0 ? dp[i - 2] : 1;
                }
            }
        }
        return dp[N - 1];
    }

    // 为了测试
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    // 为了测试
    public static void main(String[] args) {
		int N = 30;
		int testTime = 1000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * N);
			String s = randomString(len);
			int ans0 = convertToLetterString(s);
			int ans1 = dp1(s);
			int ans2 = dp2(s);
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
