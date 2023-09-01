package com.mashibing.preInEclipse.senior.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 加强堆
 *
 * @author zhangzhiwang
 * @date 2022年2月16日 下午5:00:52
 */
public class HeapEnhanced<T> {
	private ArrayList<T> heap;// 由于整个ArrayList里面的所有元素都用来组成堆，那么理论上就不需要heapSize变量来标识对的范围了，可以认为heapSize等于ArrayList.size()
	/**
	 *  反向索引表，用于表示堆中每一个元素在数组中的位置
	 *  其实也可以省略该变量，用ArrayList.indexOf(Object o)来代替，只不过该方法的时间复杂度是O(N)，而Map.get()的时间复杂度是O(1)。
	 *  如果不考虑时间复杂度而只考虑功能的话去掉indexMap也可以
	 */
	private HashMap<T, Integer> indexMap;
	/**
	 * 理论上用ArrayList.size()可以替代heapSize，但是从实际上来讲，当ArrayList添加或者删除元素后，size()会自动变化，如果想获取最后一个元素的索引用size() - 1来标识可能会出问题
	 * 所以，不妨用heapSize以避免不可预知的错误
	 */
	private int heapSize;
	private Comparator<T> comparator;

	public HeapEnhanced(Comparator<T> comparator) {
		super();
		this.heap = new ArrayList<>();
		this.indexMap = new HashMap<>();
		this.comparator = comparator;
	}

	public int getSize() {
		return heap.size();
	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}

	public boolean contains(T t) {
		return heap.contains(t);
	}

	public T peek() {
		return heap.get(0);
	}

	public void add(T t) {// 详细注释参考HeapByArray.java
		heap.add(t);
		indexMap.put(t, heapSize);
		heapInsert(heap, heapSize++);
	}

	private void heapInsert(ArrayList<T> heap, int index) {
		/**
		 * 使用比较器的compare方法技能实现大根堆也能实现小根堆，循环条件中compare的返回值和0进行比较，写成大于0或者小于0都可以（只要不写等于就行），只是一个形式问题，
		 * 关键在于compare方法是怎么定义的。
		 */
		while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
			swap(heap, index, (index - 1) / 2);
			index = (index - 1) / 2;
		}
	}

	public T poll() {
		T t = heap.get(0);
		/**
		 *  这里原本的操作应该是将数组的第一个元素和最后一个元素交换，然后heapSize--，原来头号元素并没有在数组中删除只是移出了堆的范围，就认为在对中删除了，因为在heapSize之外的元素访问不到
		 *  但是这里是真正的删除，因为整个ArrayList的所有元素都在堆的范围内，况且也没有heapSize变量，所以就不需要真正地交换头尾元素了，将尾元素覆盖到头元素然后移除尾元素就可以了，效果等同于互换。
		 */
		heap.set(0, heap.get(heapSize - 1));
		heap.remove(--heapSize);
		indexMap.remove(t);// 从反向索引中删除头元素
		heapify(heap, 0);

		return t;
	}
	
	private void heapify(ArrayList<T> heap, int index) {
		int left = 2 * index + 1;
		int heapSize = heap.size();
		while(left < heap.size()) {// 对于完全二叉树来说，没有左孩子就肯定没有右孩子，有左孩子不一定有右孩子，所以当左孩子越界（没有左孩子）的时候跳出循环
			int bestIndex = left + 1 < heapSize && comparator.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;
			bestIndex = comparator.compare(heap.get(bestIndex), heap.get(index)) < 0 ? bestIndex : index;// bestIndex是当前节点以及它左孩子和右孩子中最小或者最大值的索引
			if(bestIndex == index) {
				break;
			}
			
			swap(heap, index, bestIndex);
			index = bestIndex;
			left = 2 * index + 1;
		}
	}
	
	/**
	 * 当改变任意节点的值的时候，重新调整堆
	 * 
	 * 思路：
	 * 当其中的某个元素改变了，但是不知道是变大了还是变小了。
	 * 1、首先找到该元素的位置
	 * 2、假如是个大根堆，并且假如t变大了，那么调用heapInsert那么一定将该元素往上推
	 * 3、假如t变小了，那么调用heapify那么一定将该元素往下推
	 * 注意：由于不知道t是变大还是变小，所以必须要先后调用heapInsert和heapify方法（二者调用顺序无所谓）。由于t变大和变小只能发生一个，所以heapInsert和heapify只有一个起作用。
	 * 
	 * @param t 被改变的元素
	 * @author zhangzhiwang
	 * @date 2022年2月16日 下午6:49:54
	 */
	public void resign(T t) {
		heapInsert(heap, indexMap.get(t));
		heapify(heap, indexMap.get(t));
	}
	
	/**
	 * 删除堆中某一个元素
	 * 
	 * 思路和poll一样
	 * 
	 * @param t
	 * @author zhangzhiwang
	 * @date 2022年2月16日 下午7:43:42
	 */
	public void remove(T t) {
		if (!heap.contains(t)) {
			return;
		}
		
		int index = indexMap.get(t);
		T last = heap.get(heapSize - 1);
		heap.set(index, last);
		if(index != heapSize - 1) {// 如果移除的元素不是最后一个元素，必须要有这个判断，否则heapify会报下标越界
			heapify(heap, index);// 无论大根堆还是小根堆都是heapify，都不会heapInsert
		}
		heap.remove(--heapSize);
		indexMap.remove(t);
	}

	/**
	 * 注意：由于加入了反向索引表，交换的时候indexMap也要同步更新
	 * 
	 * @param heap
	 * @param i
	 * @param j
	 * @author zhangzhiwang
	 * @date 2022年2月16日 下午6:06:54
	 */
	private void swap(ArrayList<T> heap, int i, int j) {
		T o1 = heap.get(i);
		T o2 = heap.get(j);
		heap.set(i, o2);
		heap.set(j, o1);
		// 要同步更新反向索引表
		indexMap.put(o1, j);
		indexMap.put(o2, i);
	}

	public static void main(String[] args) {
//		PriorityQueue<User> pq = new PriorityQueue<>(new UserIdAscComparator());
		User u1 = new User(1, "zs", 18);
		User u2 = new User(2, "ls", 19);
		User u3 = new User(3, "ww", 20);
//		pq.add(u3);
//		pq.add(u2);
//		pq.add(u1);
//
//		u3.setId(0);// PriorityQueue在构建好堆之后，改变其中一个元素参与排序的值，PriorityQueue不会动态调整堆
//		
//		System.out.println(pq.poll());
//		System.out.println(pq.poll());
//		System.out.println(pq.poll());
		
		HeapEnhanced<User> heap = new HeapEnhanced<User>(new UserIdAscComparator());
		heap.add(u3);
		heap.add(u2);
		heap.add(u1);
		
//		System.out.println(heap.poll());
//		System.out.println(heap.poll());
//		System.out.println(heap.poll());
		
		u3.setId(0);
		heap.resign(u3);
		
		System.out.println(heap.poll());
		
		
//		heap.remove(u1);
//		System.out.println(heap.poll());
//		System.out.println(heap.poll());
		
//		User u = new User(1, "zs", 18);
//		HashMap<User, String> map = new HashMap<User, String>();
//		map.put(u, "123");
//		System.out.println(map.get(u));
//		
//		u.setId(0);
//		System.out.println(map.get(u));
	}
}
