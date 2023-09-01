package com.mashibing.preInEclipse.junior;

import com.mashibing.preInEclipse.junior.sort.SortCommon;

/**
 * 前缀和数组——用于计算数组某一个区间范围的和，sum[arr,L,R]，计算索引范围在L和R之间的和
 * 
 * 思路：
 * 1、原数组是arr=[1,5,7,-2,9,10,3]
 * 2、构造前缀和数组：sum=[1,6,13,11,20,30,27]，前缀和数组和原数组的元素个数一样。sum[0]=arr[0]，sum[1]=arr[0] + arr[1]，sum[2]=arr[0] + arr[1] + arr[2]，……
 * 3、当求sum[arr,L,R]时，如果L=0，那么sum[arr,L,R]=sum[R]；如果L!=0，那么sum[arr,L,R]=sum[R] - sum[L - 1]
 * 
 * @author zhangzhiwang
 * @date 2022年1月18日 下午4:12:17
 */
public class QianZhuiHeArray {
	public static void main(String[] args) {
		int[] arr = { 1, 3, 5, 2, 7, 4, 8, 100, 9, 10, -888, 8 };// 准备一个原数组
		SortCommon.printArr(arr);
		
		// 构造前缀和数组
		int[] sum = new int[arr.length];
		sum[0] = arr[0];
		for(int i = 1; i < arr.length; i++) {
			sum[i] = sum[i - 1] + arr[i];
		}
		SortCommon.printArr(sum);
	}
}
