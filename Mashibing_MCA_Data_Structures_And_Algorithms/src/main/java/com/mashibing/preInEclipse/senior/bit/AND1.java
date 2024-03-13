package com.mashibing.preInEclipse.senior.bit;

import com.mashibing.preInEclipse.junior.array.PrintIntBinary;

/**
 * 与题1——给定一个数a的二进制形式，比如：0011 0010 1110 1000，找出并返回该二进制数的最后一个1，返回形式为：0000 0000 0000 1000。
 * 思路：
 * a & (-a)
 * 注意：一个数的相反数和它的取反是不一样的，比如：5的相反数是-5，5的取反（~5）是-6，所以对于一个数i：-i != ~i，而-i等于i的取反加1
 *
 * @author zhangzhiwang
 * @date 2022年2月14日 上午11:37:23
 */
public class AND1 {
	public static void main(String[] args) {
		int a = 1230000;
		PrintIntBinary.printIntBinary(a);
		int result = findLast1(a);
		PrintIntBinary.printIntBinary(result);
	}
	
	private static int findLast1(int a) {
		/**
		 * a:0011 0010 1110 1000，返回a的二进制形式的最后一个1，其它各位全是0。
		 * 1、将a取反：1100 1101 0001 0111，我们称原始a的二进制形式中最后一个1所在的位为目标位。将a取反后每一位都和原a是相反的。
		 * 可以看做是取反后将目标位原来的1暂且变为0，由于目标位是原始a二进制形式的最后一个1，所以目标位的后面全是0，现在取反了，目标位后面就都是1了，目标位前面的各位也都是相反的。
		 * 可以将取反理解为：将目标位的1平摊到后面的所有位上，这样后面的所有位全变成了1，而目标位暂且变成了0，目标位前面的数都变成相反的了。
		 * 2、a取反后再加1：1100 1101 0001 1000。我们把整个二进制数分成三部分来看：目标位前面、目标位和目标位后面。再加1后，目标位后面的所有1全变成了0，目标位由0变成了1，
		 * 目标位前面的各位不受影响，值不变，因为+1操作在目标位被截住了，所以目标位前面不受+1的影响，即目标位往前不会进位了。这样一搞，就把目标位及其后面各位还原成a原来二进制的样子，而目标位之前的各位是相反的。
		 * 3、其实前两个步骤就是求a的相反数-a，第三步就是将a和-a进行与操作。经过了第二步之后目标位之前全部变成相反的了，进行&操作之后就都是0了，目标位及其后面各位无论a还是-a都是1后面若干个0，
		 * 与操作之后还是1后面若干个0，最终结果就出来了。
		 *
		 * 一个数的相反数就是该数二进制形式取反加1。
		 *
		 * 另外还要强调一下位运算符和算术运算符的优先级顺序，不能统一地概括为位运算符的优先级整体就比算数运算符的高或者低，一部分逻辑运算符的优先级高于算数运算符，
		 * 一部分低于算数运算符，详见：https://blog.csdn.net/weixin_41384860/article/details/128493985
		 */
		
		return a & (-a);
	}
}
