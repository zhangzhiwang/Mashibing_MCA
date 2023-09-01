package com.mashibing.preInEclipse.junior.bitmap;

import java.util.HashSet;

import com.mashibing.preInEclipse.junior.array.TestAlgorithmTool;

/**
 * 位图
 * 说白了就是一个位数组，每个元素占一位，每一位有两种数0和1
 * 
 * @author zhangzhiwang
 * @date 2022年1月23日 下午4:41:24
 */
public class MyBitmap {
	private long[] arr;

	public long[] getArr() {
		return arr;
	}

	/**
	 * @param maxValue 元素的最大值，一般认为maxValue>=0，如果maxValue<0那么
	 */
	public MyBitmap(int maxValue) {
		/**
		 * 根据元素的最大值可以计算出需要使用多少个64位，一个long占64位。
		 * 假如arr数组的长度是1，那么只能放一个long型数据，但是这一个long型数据就有64位，这64位可以表示64个数，范围是[0,63]，对应位的值是1代表有这个值，0代表没有这个值，
		 * 所以一个64位就可以表示64个数有没有，拿最后五位举例：
		 * 位：0 1 1 0 0
		 * 值：4 3 2 1 0
		 * 上面的5位分别代表0-4五个数，值0、1、4对应的位上是0代表没有这四个数，值2、3对应的位上是1，代表有2和3这两个数。
		 * 如果arr长度是n，那么可以代表有n个元素，即n个64位，可以表示64 * n个数的有无
		 */
		arr = new long[maxValue / 64 + 1];// 由于除法转成整形是向下取整，如果除不尽的话余数部分还得有一个完成的64位来保存，所以要加1
	}

	/**
	 * 添加一个值
	 * 注意此值必须不能比初始化时maxValue要大
	 * 
	 * @param value
	 * @author zhangzhiwang
	 * @date 2022年1月23日 下午5:23:40
	 */
	private void add(int value) {
		/**
		 * 前置知识：
		 * 1、num % a，如果num和都是非负整数且a是2的n次方，那么num % a的结果的二进制表示就是num二进制表示的后n位。
		 * 假如num %
		 * 64，64是2的6次方，二进制表示是第7位为1后六位都是0，换个角度说，第7位是1代表是64的1倍，如果第8位也是1代表是64的2倍，如果第9位也是1代表是64的3倍……
		 * 也就是说第7位及其更高位的数都是64的整数倍，也就是num / 64的整数部分。我们知道余数最小是0最大是被除数-1.那么num /
		 * 64余数最大是63，63的二进制表示是后六位都是1，
		 * 即0011 1111，正好是0100 0000 - 1，那么余数的范围在0000 0000 ~ 0011
		 * 1111之间，也就是说num二进制表示低于7位的数就是num % 64的余数，就是除以64除不尽的部分。
		 * 2、任何加减乘除、取模等运算都是在十进制范围进行的，底层要将十进制转换为二进制，然后再利用位操作来进行线管运算，最后再转换成十进制输出，所以直接使用位运算要比十进制的加减乘除、取模等运算效率要高。
		 * 根据上面第一点的结论，计算num %
		 * a的结果就取出num二进制表示的后六位即可，由于%运算效率低于位运算，那怎么用位运算来取代%呢？什么样的方式可以还原一个数的后六位呢？
		 * 我们知道一个二进制数与1进行&操作会还原该数本身，那么就让num的二进制数与0011
		 * 1111与就可以了，第7位及其更高位与0与都是0，正好取得是余数，64的整数倍也不要。
		 * 所以结论：num % a = num & (a - 1)，num和都是非负整数且a是2的n次方.
		 * 3、由于位图的位数组的每一位代表一个数的有无，所以num % 64的余数是谁num就落在当前64位段的第几位上。
		 * 4、一个数左移n位，相当于乘以2^n；一个数右移n位，相当于除以2^n。
		 */
		/**
		 * 这里说本add方法，思路：
		 * 1、找到value应该存放在数组的第几个元素
		 * 寻找数组将一个数value放入到位图中，首先数组是long型数组，不是int型数组，所以一个元素有64位，那么value应该存放在第几个64位呢？num
		 * / 64然后转成int，我们知道double转成int是向下取整，
		 * 当num < 64时，结果是0，也就是放在第0个64位，整好对应数组的下标0，也就是数组的第一个位置，正好数组的下标是从0开始的，所以num /
		 * 64后不用再加1。
		 * 2、确定value应按该放在64位的哪一位上
		 * num % 64 = num & (a - 1)
		 * 3、将该位置上的数字标1，就代表value存放在这了。
		 * “将该位置上的数字标1”意味着只将该位置标1，其他位置不动，那么怎么做呢？利用或运算的特性：一个二进制数与0进行|操作会还原该数本身。让其他位置与0或，这样其他位置可以不变，
		 * 标1的位置与1或，结果一定是1，原来位置是0会变成1，原来位置是1还是1，这样操作后的结果就是其他位置不变目标位标1，让后把结果再赋值给数组的当前元素，也就是或等于(|=)。
		 * 目标位上的数字怎么与1与？1左移num & (a - 1)位
		 */
		arr[value >> 6] |= 1L << (value & 63);// 注意由于本例使用的是long数组，所以1必须是long类型的，int型的1左移63位会报错。
	}

	/**
	 * 删除一个值
	 * 
	 * @param value
	 * @author zhangzhiwang
	 * @date 2022年1月24日 上午10:22:07
	 */
	private void delete(int value) {
		/**
		 * 思路：
		 * 删除一个数就是将目标位置为0，不管原来是1还是0统一置为0。
		 * 比如要将0011 1010 1101的第6位置为0其它位不变，变成0011 1000 1101。怎么做呢？让原数0011 1010
		 * 1101与上一个只有目标位第6位是0的数1111 1101 1111，结果即可将第6位变成0其它位保持不变。
		 * 那么只有目标位是0其它位都是1的数怎么出来呢？换个思路，如果只有目标位是1其它位都是0的数怎么做出来？很简单，1向左移动n位就可以了，然后取个反就出现了只有目标位是0其它位都是1的数。
		 */
		arr[value >> 6] &= ~(1L << (value & 63));
	}

	/**
	 * 查找一个值是否存在
	 * 思路：
	 * 1、首先要找到该值在数组的第几个元素上（第几个64位上）：value / 64
	 * 2、确定在64位上的具体哪一位：value % 64 = n
	 * 3、看相应位上是否为1：& (1 << n)
	 * 
	 * @param value
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月24日 上午10:33:05
	 */
	private boolean contains(int value) {
		/**
		 * 最后的!=0不能替换为==1。因为目的是只比较目标位是否为1，但是程序是比较整个64位数字的，即使目标位上是1，但是后面的位都是0，这个数组在整体上可不是1。
		 */
		return (arr[value >> 6] & (1L << (value & 63))) != 0;
	}

	public static void main(String[] args) {
		// 人工验证
//		MyBitmap myBitmap = new MyBitmap(100);
//		myBitmap.add(99);
//		System.out.println(myBitmap.contains(99));
//		myBitmap.delete(99);
//		System.out.println(myBitmap.contains(99));

		// 对数器验证
		int maxValue = 1000;
		MyBitmap myBitmap = new MyBitmap(maxValue);
		HashSet<Integer> hashSet = new HashSet<Integer>();
		double d = Math.random();
		for (int i = 0; i < 100_0000; i++) {
			int num = (int) (Math.random() * (maxValue + 1));
			if (d < 0.3) {
				myBitmap.add(num);
				hashSet.add(num);
			} else if (d < 0.6) {
				myBitmap.delete(num);
				hashSet.remove(num);
			} else {
				if(myBitmap.contains(num) != hashSet.contains(num)) {
					System.out.println("程序出错");
				}
			}
		}
		
		for(int i = 0; i <= maxValue; i++) {
			if(myBitmap.contains(i) != hashSet.contains(i)) {
				System.out.println("程序出错!!!");
			}
		}
		
		System.out.println("ok");
	}
}
