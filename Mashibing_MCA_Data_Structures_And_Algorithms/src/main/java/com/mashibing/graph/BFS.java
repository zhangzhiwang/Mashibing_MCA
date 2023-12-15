package com.mashibing.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 图的广度优先遍历（Breadth First Search），又叫宽度优先遍历
 * 思路：
 * 类似于树的按层遍历，准备一个队列，准备一个Set，由于图的节点是可以成环的，准备Set就是为了去重，否则有可能使遍历出现死循环。
 * 另外，对于图来说，无论是什么遍历，结果都有可能不是唯一的，哪个都对。
 *
 * 课程：体系班课时141
 */
public class BFS {
    public static void bfs(GraphNode<Integer> node) {
        if(node == null) {
            return;
        }

        Queue<GraphNode<Integer>> queue = new LinkedList<>();
        Set<GraphNode<Integer>> set = new HashSet<>();
        queue.add(node);// 先把start节点放入队列和Set中
        set.add(node);

        while(!queue.isEmpty()) {
            GraphNode<Integer> n = queue.poll();
            System.out.print(n.data + "\t");// 打印就代表对节点的处理
            for(GraphNode<Integer> next : n.nexts) {
                if(set.contains(next)) {// 由于图是存在环的，所以set是为了避免将同一个节点重复放入队列而导致死循环
                    continue;
                }
                set.add(next);
                queue.add(next);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        GraphNode<Integer> a = new GraphNode<>(1);
        GraphNode<Integer> b = new GraphNode<>(2);
        GraphNode<Integer> c = new GraphNode<>(3);
        GraphNode<Integer> d = new GraphNode<>(4);
        GraphNode<Integer> e = new GraphNode<>(5);
        GraphNode<Integer> f = new GraphNode<>(6);
        GraphNode<Integer> g = new GraphNode<>(7);
        a.nexts.add(b);
        b.nexts.add(c);
        b.nexts.add(d);
        d.nexts.add(e);
        e.nexts.add(c);
        c.nexts.add(f);
        f.nexts.add(e);

        bfs(a);
    }
}
