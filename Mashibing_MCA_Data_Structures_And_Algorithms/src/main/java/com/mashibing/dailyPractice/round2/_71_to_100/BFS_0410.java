package com.mashibing.dailyPractice.round2._71_to_100;

import com.mashibing.graph.GraphNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 图的宽度优先遍历
 *
 * @Auth zhangzhiwang
 * @Date 2024/4/10 21:08
 */
public class BFS_0410 {
    public static void bfs(GraphNode<Integer> head) {
        if(head ==null) {
            return;
        }

        Queue<GraphNode<Integer>> queue = new LinkedList<>();
        Set<GraphNode<Integer>> set = new HashSet<>();
        queue.add(head);
        set.add(head);
        while(!queue.isEmpty()) {
            GraphNode<Integer> poll = queue.poll();
            System.out.print(poll.data + "\t");
            for (GraphNode<Integer> child : poll.nexts) {
                if (set.contains(child)) {
                    continue;
                }

                queue.add(child);
                set.add(child);
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
        a.nexts.add(c);
        a.nexts.add(d);
        b.nexts.add(e);
        b.nexts.add(f);
        c.nexts.add(g);
        c.nexts.add(b);
        bfs(a);
    }
}
