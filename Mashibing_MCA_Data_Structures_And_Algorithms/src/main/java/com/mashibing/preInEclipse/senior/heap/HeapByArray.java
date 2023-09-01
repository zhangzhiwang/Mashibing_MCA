package com.mashibing.preInEclipse.senior.heap;

/**
 * 堆
 * 堆的定义包含两点：
 * 1、首先堆是一棵完全二叉树
 * 2、在完全二叉树的基础上，分为大根堆和小根堆，一个堆必须是大根堆和小根堆其中的一个。
 * 
 * 解释：
 * 1、完全二叉树：一棵二叉树是满的那么它就是完全二叉树，如果不满也要按照从左到右的顺序在变满的路上，且不满的那一层必须是最后一层（也就是说除最后一层外其它各层都是满的），那么这样的二叉树也是完全二叉树。
 * 2、大根堆：任意一棵子树的最大值是该子树的头结点，当一棵完全二叉树的所有子树都满足这个条件时，这棵完全二叉树就是大根堆。
 * 3、小根堆：和大根堆相反，任意一棵子树的最小值是该子树的头结点，当一棵完全二叉树的所有子树都满足这个条件时，这棵完全二叉树就是小根堆。
 * 
 * 这里使用数组来实现堆：
 * 用一个变量heapSize来维护对解耦股在数组中的范围，heapSize为数组的索引，从0开始，每增加一个节点heapSize++，每删除一个节点heapSize--。
 * 如果数组是空的，那么heapSize=0，但刚加入一个节点时放到数组的0位置，heapSize++变成1，也就是heapSize指向数组索引为1的地方，也可以理解为heapSize指向下一个节点被添加的位置
 * 比如数组的长度是100，共有50个元素（另外50个位子是空的），heapSize=8，说明数组[0,7]范围内已经加入了节点，下一个被添加的位置是索引为8的位置，也就是heapSize指向的位置。
 * 堆构建的过程是数组从左往右，完全二叉树从上往下的顺序依次构建的，这样就建立起了某一个节点在数组中和完全二叉树中的对应关系：
 * 假设完全二叉树的某个节点在数组中的索引为i，那么它的左孩子（左子树的头结点）在数组中的索引是2 * i + 1，右孩子的索引是2 * i + 2，父节点的索引是（i - 1）/ 2向下取整。
 * 
 * 任何一个数组都可以看做是一棵完全二叉树
 * 
 * 在java语言的使用层面是没有堆的（java.util包里面有List，有Stack，但是没有Heap），java用优先级队列PriorityQueue来代替堆，所以在java语言中PriorityQueue就是堆。
 *
 * @author zhangzhiwang
 * @date 2022年2月15日 下午12:02:16
 */
public class HeapByArray {
	private int[] arr;// 这里维护大根堆
	private int heapSize;// 注意不是所有arr里面的元素都算在堆里，只有[0,heapSize-1]是堆的范围。heapSize永远指向下一个被放入元素的位置

	public HeapByArray(int len) {
		super();
		this.arr = new int[len];
	}
	
	/**
	 * 向堆中添加节点（大根堆）
	 * 
	 * @param num
	 * @author zhangzhiwang
	 * @date 2022年2月15日 下午6:26:14
	 */
	public void push(int num) {
		if(arr == null || heapSize == arr.length) {
			return;
		}
		// 先把元素放到最后一个位置
		arr[heapSize] = num;
		// 调整堆的位置
		heapInsert(arr, heapSize++);// 不要忘了最后heapSize要++
	}
	
	/**
	 * heapInsert的作用不是向堆中添加节点，而是调整堆中指定节点的位置，向堆中添加节点的工作是由调用heapInsert方法的上游方法来完成的
	 * heapInsert是将指定节点往上调
	 * 
	 * 思路：先将新节点添加到数组的末尾，然后逐层往上和父节点的大小进行比较，比父节点大的就和父节点交换，直到不大于父节点或者到达了整棵树的头结点。
	 * 
	 * heapInsert的时间复杂度是O(logN)。因为添加一个元素，该元素向上移动的最大次数是完全二叉树的高度次，而一棵满完全二叉树的高度和节点N的数量关系是2^(h - 1) = N，
	 * 即h = log2(N + 1)（log以2为底N+1de对数），也就是节点最大移动log2(N + 1)，去掉常数项就是logN，参考：
	 * https://blog.csdn.net/nb_zsy/article/details/120387834?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~aggregatepage~first_rank_ecpm_v1~rank_v31_ecpm-1-120387834.pc_agg_new_rank&utm_term=完全二叉树的高度&spm=1000.2123.3001.4430
	 * 
	 * 注意：无论是添加元素还是一弹出元素，index始终跟随着目标节点并随目标节点的移动而移动。对于添加来说目标节点就是被添加的元素，对于弹出来说，目标节点就是头结点的元素。
	 * 
	 * @param arr arr是对范围内的数组
	 * @param index 被添加的节点在数组中的索引
	 * @author zhangzhiwang
	 * @date 2022年2月15日 下午3:02:27
	 */
	public void heapInsert(int[] arr, int index) {
		while(arr[index] > arr[(index - 1) / 2]) {// 注意：-0.5向下取整是0，所以退出循环的条件是被添加的节点不必父节点大或者已经达到了索引为0的位置
			swap(arr, index, (index - 1) / 2);
			index = (index - 1) / 2;// 注意：入参的index是被添加的节点在数组中的索引，这个index要始终跟随者这个元素走，这个元素被换到哪index就跟哪
		}
	}
	
	/**
	 * 由于是大根堆，所以弹出堆中最大的元素
	 * 
	 * 思路：
	 * 1、被弹出的元素一定是数组0位置的元素
	 * 2、将堆最后一个节点元素（heapSize-1）和头结点位置的元素交换，并且让heapSize--，这样原来头结点的元素就被移出了对的范围之外，但是该元素还可以在数组中保留而无需删除
	 * 因为堆得可见范围是[0,heapSize-1]，原来的头元素在heapSize--之后就访问不到了。
	 * 3、调整堆的位置，移出头结点之后要保证剩下的部分仍然是大根堆
	 * 
	 * 注意：无论是添加元素还是一弹出元素，index始终跟随着目标节点并随目标节点的移动而移动。对于添加来说目标节点就是被添加的元素，对于弹出来说，目标节点就是头结点的元素。
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年2月15日 下午3:14:38
	 */
	public int poll() {
		if (arr == null | arr.length == 0) {
			throw new RuntimeException("堆为空");
		}
		
		// 无论是大根堆还是小根堆都要弹出0位置的元素
		int result = arr[0];
		// 将0位置和heapSize-1位置的元素交换，然后heapSize--，将原0位置的元素移出堆范围之外
		swap(arr, 0, --heapSize);
		// 交换之后原来位于堆最后位置的元素一跃成为整棵树的头结点，调整各节点的位置
		heapify(arr, 0);
		return result;
	}
	
	public int peek() {
		return arr[0];
	}
	
	/**
	 * heapify是将指定节点往下调
	 * 和heapInsert一样，heapify的时间复杂度也是O(logN)，因为一个节点往下移动的最大次数也是树的高度次。
	 * 
	 * @param arr
	 * @param index 头结点的索引
	 * @author zhangzhiwang
	 * @date 2022年2月15日 下午3:31:11
	 */
	public void heapify(int[] arr, int index) {// index始终随着头结点走
		if(heapSize <= 0) {
			return;
		}
		
		// 找到左孩子的索引
		int leftIndex = 2 * index + 1;
		while(leftIndex < heapSize) {// 当左孩子的索引超出了对的范围时说明没有左孩子,对于一棵完全二叉树来说如果连左孩子都没有那么右孩子肯定没有，进而说明index是叶子节点
			// 如果进入了while循环说明左孩子没有越界，说明一定有左孩子，但是有左孩子不一定有右孩子
			// 找出index左右孩子最大值的索引，由于是大根堆，所以要找左右孩子的最大值的索引
			/**
			 * 1、由于左孩子的索引=2 * index + 1，而右孩子的索引=2 * index + 2，所以右孩子=左孩子 + 1
			 * 2、(leftIndex + 1) < heapSize说明有右孩子
			 * 3、下面这行代码的意思是：如果有右孩子并且左孩子的值比右孩子小，返回右孩子索引；如果没有右孩子或者左孩子的值比右孩子大则返回左孩子索引
			 */
			int largestIndex = (leftIndex + 1) < heapSize && arr[leftIndex] < arr[leftIndex + 1] ? leftIndex + 1 : leftIndex;
			// 上面得到的largestIndex是左右孩子最大值的索引，但是还没有和父节点比较大小
			largestIndex = arr[largestIndex] > arr[index] ? largestIndex : index;// 这一步得出的是父节点和左右孩子中最大值的索引
			
			if(largestIndex == index) {// 经过上线一番折腾之后发现最大值的所以还是父节点，说明腹肌诶单不用交换了，因为左右儿子都比他小，那么左右孙子就更小了，所以直接break
				break;
			}
			// 如果在上面没有break掉，说明左右孩子之一比父节点大，要交换
			swap(arr, largestIndex, index);// 这个交换只是元素值的交换
			index = largestIndex;// 注意：index要始终和入参的头结点走
			leftIndex = 2 * index + 1;
		}
	}
	
	public void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}
	
	public static void main(String[] args) {
		HeapByArray heap = new HeapByArray(10);
		heap.push(10);
		heap.push(30);
		heap.push(20);
		System.out.println(heap.peek());
		heap.push(5);
		System.out.println(heap.peek());
		heap.push(50);
		System.out.println(heap.peek());
		
		System.out.println("------------");
		System.out.println(heap.poll());
		System.out.println(heap.poll());
		System.out.println(heap.poll());
		System.out.println(heap.poll());
		System.out.println(heap.poll());
	}
}
