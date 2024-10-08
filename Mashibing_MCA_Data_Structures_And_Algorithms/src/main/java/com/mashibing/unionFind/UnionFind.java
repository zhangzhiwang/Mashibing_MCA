package com.mashibing.unionFind;

import com.mashibing.dailyPractice.round1._81_to_90.UnionFind_0322;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 并查集
 * 思路：什么是并查集、并查集的相关概念以及代码实现思路请见课程视频
 * 并查集的优势就在于对于以下两个操作：
 * 1、查询两个元素是否在同一集合
 * 2、合并两个集合
 * 如果操作的次数非常频繁，达到了甚至超过了样本总数据量N，那么并查集就可以使以上两个操作的时间复杂度进行均摊，使每一次的操作时间复杂度为O(1)。
 *
 * 注意：本类是并查集的经典实现方法，但是这种实现有一个弊端或者说限制，就是要求样本数据如果是基本数据类型或者字符串类型，不能有重复的值。
 * 因为在经典实现里有一个nodeMap，key是值本身，value是包装的Node对象，样本数据如果是基本数据类型或者String类型的话，即使是使用基本数据类型的包装类，
 * 如果值一样的话，HashMap仍然会认为是同一个key，会将value进行覆盖。这个是由于HashMap的机制导致的，在计算key的hash值时会调用key的hashcode方法，
 * 拿Integer来说，Integer的hashcode方法就是返回其背后所代表的int值，其它的类似。
 * 如果样本数据是基本数据类型或者字符串类型且有重复的值，如果还想使用并查集经典实现的话，那么就得想办法让HashMap认为他们是不同的key，比如将值包装进一个类里面，
 * 比如A类，然后再将A类包装进UnionFindNode里面，这样在放进nodeMap中，HashMap会计算A对象的内存地址，就会认为它们是不同的值。
 *
 * 课程：体系班课时122-125
 */
public class UnionFind<T> {
    // 值和包装节点的对应关系
    private Map<T, UnionFindNode<T>> nodeMap = new HashMap<>();
    // 节点和其直接父节点的对应关系
    private Map<UnionFindNode<T>, UnionFindNode<T>> parentMap = new HashMap<>();
    // 代表结点和其代表集合的节点数量的对应关系，注意：nodeSizeMap只记录代表节点所对应集合的节点数量
    private Map<UnionFindNode<T>, Integer> nodeSizeMap = new HashMap<>();

    /**
     * @param values 一些值组成的集合
     */
    public UnionFind(List<T> values) {
        if (values == null) {
            throw new RuntimeException("值集不能为空！");
        }

        for (T t : values) {
            UnionFindNode<T> node = new UnionFindNode<>(t);
            nodeMap.put(t, node);
            parentMap.put(node, node);
            nodeSizeMap.put(node, 1);
        }
    }

    /**
     * 给定一个节点找出它的代表节点
     *
     * @param node
     * @return
     */
    public UnionFindNode<T> findAncestor(UnionFindNode<T> node) {
        if(node == null) {
            return null;
        }

        Stack<UnionFindNode<T>> stack = new Stack<>();
        while(parentMap.get(node) != node) {
            stack.push(node);
            node = parentMap.get(node);
        }
        // 上面的while循环结束之后node已经向上走到了代表结点但并没有加入到站里面去

        /*
         可以看到整个并查集的操作无论是isSameSet还是union，时间消耗最多的就是findAncestor方法，其它的都是O(1)的操作，
         而findAncestor方法是从任意节点出发一直向上找到它的代表节点，链的长度会影响到查询性能，所以优化思路就是尽量减小链的长度。
         优化：将入参node及以上所有节点都挂在代表节点下，相当于拉平了，但是入参在node以下的节点还挂在原来的node下不变，
         等后续调用此方法传入原node以下的节点时，那个时候会拉平，所以当调用本方法非常频繁的时候，平均时间复杂度是O(1)
         */
        while(!stack.isEmpty()) {
            parentMap.put(stack.pop(), node);// 此时node已经不是入参的node了，已经是代表节点了
        }

        return node;
    }

    public boolean isSameSet(T value1, T value2) {
        if(value1 == null && value2 == null) {
            return true;
        } else if (value1 == null ^ value2 == null) {
            return false;
        }

        UnionFindNode<T> node1 = nodeMap.get(value1);
        UnionFindNode<T> node2 = nodeMap.get(value2);
        if(node1 == null || node2 == null) {
            return false;
        }

        return findAncestor(nodeMap.get(value1)) == findAncestor(nodeMap.get(value2));
    }

    /**
     * 合并两个节点背后的并查集
     *
     * @param value1
     * @param value2
     */
    public void union(T value1, T value2) {
        if(isSameSet(value1, value2)) {// 如果两个值都在同一个并查集里无需合并
            return;
        }

        // 注意：parentMap的key和value存放的都是并查集节点，而不是赝本数据的原始节点，用parentMap去get原始数据节点是不行的，所以必须先通过nodeMap把并查集节点先查出来
        UnionFindNode<T> ancestor1 = findAncestor(nodeMap.get(value1));
        UnionFindNode<T> ancestor2 = findAncestor(nodeMap.get(value2));
        Integer size1 = nodeSizeMap.get(ancestor1);
        Integer size2 = nodeSizeMap.get(ancestor2);
        UnionFindNode<T> bigAncestor = size1 >= size2 ? ancestor1 : ancestor2;
        UnionFindNode<T> smallAncestor = bigAncestor == ancestor1 ? ancestor2 : ancestor1;
        /*
         TODO 不理解为什么要小集合并入大集合里面，为什么这样就比大集合并入小集合要更好
         老师讲的是小挂大的话可以使链增长得慢，为什么？集合的合并是往代表结点上挂，又不是往任意一个节点上挂。
         */
        parentMap.put(smallAncestor, bigAncestor);
        nodeSizeMap.put(bigAncestor, size1 + size2);
        nodeSizeMap.remove(smallAncestor);
    }

    /**
     * 返回并查集的数量
     * 注意不是返回并查集节点的数量，是返回有多少并查集
     *
     * @return
     */
    public int size() {
        return nodeSizeMap.size();
    }

    /**
     * 把值包装成节点
     * 这个包装节点并不是所有情况都需要的，当样本数据有重复值但代表不同样本数据时，由于Map的key会重复，所以需要外面包装一层，这个时候才需要这个UnionFindNode。
     * 当不涉及这个场景时，可以直接使用样本数据本身作为并查集节点，这样还可以省掉nodeMap属性。当然无论什么情况都使用UnionFindNode包装一层也没有问题，只要使用
     * UnionFindNode包装，就要有nodeMap属性来对样本数据和并查集节点做映射。
     *
     * @param <T>
     */
    static class UnionFindNode<T> {
        public T data;

        public UnionFindNode(T data) {
            this.data = data;
        }
    }
}
