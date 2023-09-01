package com.mashibing.preInEclipse.junior.bitmap;

/**
 * 用位运算实现加减乘除
 * 要求：不能在算法中出现“+”、“-”、“*”、“/”、“%”符号，要完全使用位运算
 * 
 * @author zhangzhiwang
 * @date 2022年1月24日 下午8:04:47
 */
public class CalcByBitOper {
	public static void main(String[] args) {
		// 人工验证
		System.out.println(add(10, 3));
		System.out.println(minus(-10, 1));
		System.out.println(multi(5, -2));
		System.out.println(divide(Integer.MIN_VALUE, -1));
	}

	/**
	 * 加
	 * 思路：
	 * 前置知识：
	 * 1、异或运算相当于无进位相加
	 * 比如：0011 0101 1011 无进位加上 0111 0001 0101 = 0100 0100 1110
	 * 0011 0101 1011 ^ 0111 0001 0101 = 0100 0100 1110
	 * 2、如何表示进位信息？在二进制中只有1和相遇才会进位，1和0、0和1、0和0相遇都不会产生进位。用与运算来找出哪个位需要进位，如果与的结果是1，那么说明该位上的两个数都为1，
	 * 那么进位是往前进一位，所以在与运算的结果上向左移一位即可。
	 * 3、两个数相加的结果可以看做是两个数的无进位相加结果加上进位信息。那十进制举例：188 +
	 * 99，先进行无进位相加得177，然后个位有一个进位，进到了十位，十位也有一个进位，尽到了百位，
	 * 所以进位信息是110，177 + 110 = 287。二进制同理，将无进位相加的值a ^
	 * b加上进位信息的值等于最终结果，但是这可能又产生进位，递归算下去知道进位信息为0为止，那么当进位信息为0时，无进位相加的结果就是最终的结果。
	 * 
	 * @param a
	 * @param b
	 * @author zhangzhiwang
	 * @date 2022年1月24日 下午8:05:30
	 */
	private static int add(int a, int b) {
		int sum = a;// 为什么上来就让sum =
					// a而不是等于0？这是为了防止传进来的b是0，这样的话就直接返回a，如果不这么做就在在最开始进行判断：如果a或者b任意一个为空返回另外一个。
		while (b != 0) {
			sum = a ^ b;// 无进位相加的结果
			b = (a & b) << 1;// b充当了进位信息
			a = sum;// a充当了无进位相加的结果
		}

		return sum;
	}

	/**
	 * 减
	 * 思路：
	 * 两数相减相当于一个数加上另一个数的相反数，所以可以调用add(a, -b)，但是不能出现减号，所以add(a, ~b +
	 * 1)，但是不能出现加号，所以add(a, add(~b, 1))
	 * 这里要知道一个数的相反数的二进制表示是该数的二进制表示取反加1。
	 * 
	 * @param a
	 * @param b
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月24日 下午8:31:24
	 */
	private static int minus(int a, int b) {
		return add(a, add(~b, 1));
	}

	/**
	 * 乘
	 * 思路：
	 * a：1011
	 * b：1100
	 * result：结果
	 * 1、b的第一位是0，那么result += 0000（加0也可以忽略不做）
	 * 2、b的第二位是0，那么result += 0000
	 * 3、b的第三位是1，那么a左移2位放进result里：result += a << 2（至于为什么是这样？可以参考小学乘法列竖式的方式，比如789 *
	 * 987列竖式怎么算？）
	 * 4、b的第四位是1，那么a左移3位放进result里：result += a << 3
	 * 5、result为最终结果
	 * 
	 * @param a
	 * @param b
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月24日 下午8:49:41
	 */
	private static int multi(int a, int b) {
		int result = 0;
		while (b != 0) {
			if ((b & 1) == 1) {
				result = add(result, a);
			}
			b >>>= 1;
			a <<= 1;
		}

		return result;
	}

	/**
	 * 除（除不尽的向下取整）
	 * 前置知识：
	 * 1、a / b = c => a = b * c
	 * 2、a * (b + c) => a * b + b * c
	 * 
	 * 思路：a / b = c
	 * 假如结果c的二进制表示为：0000 0110，那么c就可以表示为2^2 + 2^1，即c = 2^2 + 2^1。因为a = b * c，所以a = b
	 * * (2^2 + 2^1)，所以a = 2^2 * b + 2^1 * b。
	 * 那么这时在已知c的情况下，现在是求c，c肯定是未知，但是假如知道a = 2^2 * b + 2^1 * b，变个形式：a = b * (2^2 +
	 * 2^1)，也就意味着未知数c的二进制表示在第3位和第二位上的值为1，其它全为0，即0110。
	 * 所以要想求c，那么就得知道a = 2^? * b + 2^? * b + …… + 2^? *
	 * b，知道了所有“?”的值之后，c的二进制形式就出来了。另外2^? * b可以看做b左移?位。
	 * 假如a和b全是正数，那么等号右边的任意一个2^? * b都会比a小，因为若干个2^? * b的和是a。
	 * 假如：
	 * a:0011 1110 => 62（十进制）
	 * b:0000 0110 => 6（十进制）
	 * b往左移多少位最接近a（“最接近a”的意思是小于等于a的最大的b是什么）？答案是3位，即b左移3位：0011 0000，也就是说2^? *
	 * b，这个问号是3，而且3是所有问号里面最大的，所以结果c的第3位上肯定是1.
	 * 怎么算出其它的问号呢？用a将2^3 * b减掉，这样就消除掉了一个2^? * b，即a = 2^3 * b + 2^? * b + …… + 2^? *
	 * b => a - 2^3 * b = 2^? * b + …… + 2^? * b，
	 * 然后用同样的方法算出其它的问号。0011 1110 - 0011 0000 = 0000 1110，所以a变成0000 1110，b还是原来的0000
	 * 0110不变。
	 * 第二轮继续，b往左移多少位最接近a？答案是1位，即b左移1位：0000 1100，所以结果c的第1位上肯定是1.那么a - b之后a为：0000
	 * 0010，b还是0000 0110，这个时候a比b小了，小的部分就是余数，
	 * 由于本题目要求求整数部分，所以当a < b时停止。所以c的最总结果的二进制表示为第3位和第1位上是1，其它都是0，换算成十进制就是10.
	 * 为什么从最近接a的最大的b（也就是问号最大值）开始找呢，为什么不从最小的开始呢？因为小于a的最大的b只有一个，但是小于a的b有很多。如果从最小的问号开始找，那么b到底左移多少位呢？b左移1位、2位、3位都比a小，
	 * 那问号到底等于多少？确定不了！只能找到问号的最大值，也就是b左移最多的位数之后恰好不大于a，再多移一位就大于a了，由于a和b都是正数，所以等式a =
	 * 2^? * b + 2^? * b + …… + 2^? * b不成立了，所以要从问号的最大值开始找。
	 * 
	 * 但是！有一个关键问题：b往左移多少位之后才知道b是小于a的最大值呢？推导一下：b往左移1位，发现b比a小，但是我不知道b是不是比a小的最大的数，只能b再往前移一位，这样b就左移两位了，如果此时b=a，那么b就是<=a的最大值，
	 * 如果b还是比a小，那么继续左移三位，这时发现b比a大了，这时才知道上一步的b是<=a的最大值，意思就是说当b左移n位时发现比a大了，才知道左移n-1位的时候才是<=a的最大值。
	 * 这样有一个风险就是如果a的符号位是0，下一位是1，那么b可能做一道符号位才发现上一步是结果，这个时候b可能已经把原来自己的符号位上的0改成了1，变成了负数，b就永远比a小了，就永远也发现不了比a小的最大的b是谁了。
	 * 于是乎，换一种思路：
	 * 假如：a = 2^? * b，那么：a / 2^? = b，“a / 2^?”不就是a右移?位吗，因为a右移永远不会修改符号位。
	 * 根据：a = 2^? * b + 2^? * b + …… + 2^? * b >= 0 可以得知：a减去任何一个2^? * b肯定是>=0的，即：a -
	 * 2^? * b = 2^? * b + …… + 2^? * b >= 0 ===> a - 2^? * b >= 0 ===> a >= 2^? * b
	 * ===> a / 2^? >= b ===> a >> ？ >= b
	 * 所以：a >> ？>=
	 * b，也就是从原来的“去找b左移之后<=a的最大的b是谁”变成了“去找a右移之后>=b的最小的a是谁”。由于a和b都是int类型的，所以有32位，除第一位外有效位是31位。
	 * 要想找到>=b的最小的a必须右移30位、29位、28位……这样递减地右移，如果从0开始递增地右移那么会出现比b大的a有很多，不是唯一的，道理上面已经讲过了。右移？位第一次>=b，
	 * 那么？的值就确定了，而？就代表c第几位上的数字是1.
	 * 无论是a右移去找>=b的最小还是b左移去找<=a的最大值，目的都是消去a的高位
	 * 
	 * 注意：这里没有考虑除数为0的情况，如果给b传入0，那么结果会得到Integer的最大值
	 * 
	 * @param a
	 * @param b
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月25日 上午10:30:36
	 */
	private static int div(int a, int b) {
		// 首先先将a和b都转化为整数，用正数去做除法然后再在结果上填符号
		int x = isNegtive(a) ? getOppositeumber(a) : a;
		int y = isNegtive(b) ? getOppositeumber(b) : b;
		int sum = 0;

		for (int i = 30; i >= 0; i = minus(i, 1)) {// 之所以使用minus(i, 1)是因为程序中不能出现减号。为啥是倒叙遍历见上面的思路。
			if (x >> i >= y) {
				sum |= 1 << i;
				x = minus(x, y << i);// 无论是a右移去找>=b的最小还是b左移去找<=a的最大值，目的都是消去a的高位，所以相减的时候仍然是x减去y左移i位
			}
		}

		/**
		 * true ^ true = false ==> 1 ^ 1 = 0
		 * false ^ false = false ==> 0 ^ 0 = 0
		 * true ^ false = false ==> 1 ^ 0 = 1
		 */
		return isNegtive(a) ^ isNegtive(b) ? getOppositeumber(sum) : sum;
	}

	/**
	 * 除法的完整过程
	 * 这里主要是解决Integer.MIN_VALUE不能转绝对值的问题，因为Integer.MIN_VALUE的相反数还是它自己，原因见junior.array.ByteOper的注释
	 * 
	 * @param a 被除数
	 * @param b 除数
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月25日 下午5:42:52
	 */
	private static int divide(int a, int b) {
		if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
			return 1;
		} else if (b == Integer.MIN_VALUE) {
			return 0;// 因为在int的范围内任何数的绝对值都比Integer.MIN_VALUE的绝对值小（假如有绝对值的话），所以向下取整就是0
		} else if (a == Integer.MIN_VALUE) {
			/**
			 * 为了举例方便，假设int的范围是[-10,9]，最小值-10没有绝对值，否则超出了int的最大值。如果被除数a是-10，那么就将a的绝对值减小1，即-10+1=-9。假如b是2，那么-10/2就转化为-9/2。
			 * -9/2=-4，逆向算回去-4*2=-8，比-10还差-2.就用这个差值-2再除以2，即-2/2=-1，然后-4+(-1)=-5，-5就是最终结果。
			 * 另外，LeetCode上规定：如果被除数是系统最小值，即Integer.MIN_VALUE，除数是-1，那么结果返回系统最大值即可，即Integer.MAX_VALUE。
			 */
			if(b == -1) {
				return Integer.MAX_VALUE;
			} else {
				// 1、算出比系统最小值的绝对值小一个的数
				int a_1 = Integer.MIN_VALUE + 1;
				// 2、让a_1去除以被除数b
				int c = div(a_1, b);
				// 3、让c逆运算算回去，看看被除数和Integer.MAX_VALUE有多少差距
				int d = Integer.MAX_VALUE - multi(c, b);
				// 4、用差距d再除以被除数b
				int e = div(d, b);
				// 5、c和e的和就是最终结果
				return add(c, e);
			}
		} else {// a和b都不是最小值
			return div(a, b);
		}
	}

	/**
	 * 判断一个数是否为负数
	 * 
	 * @param i
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月25日 下午4:19:29
	 */
	private static boolean isNegtive(int i) {
		return i < 0;
	}

	/**
	 * 获取一个数的相反数
	 * 
	 * @param i
	 * @author zhangzhiwang
	 * @date 2022年1月25日 下午4:21:42
	 */
	private static int getOppositeumber(int i) {
		return add(~i, 1);
	}
}
