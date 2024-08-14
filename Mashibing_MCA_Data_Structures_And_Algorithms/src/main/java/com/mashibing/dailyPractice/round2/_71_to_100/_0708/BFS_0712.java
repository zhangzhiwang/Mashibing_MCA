package com.mashibing.dailyPractice.round2._71_to_100._0708;

import com.mashibing.graph.GraphNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 图的宽度优先遍历
 */
public class BFS_0712 {
    public static void BFS(GraphNode<Integer> node) {
        if(node == null) {
            return;
        }

        Queue<GraphNode<Integer>> help = new LinkedList<>();
        help.add(node);
        Set<GraphNode<Integer>> set = new HashSet<>();
        while(!help.isEmpty()) {
            GraphNode<Integer> poll = help.poll();
            if(set.contains(poll)) {
                continue;
            }

            set.add(poll);
            System.out.print(poll.data + "\t");
            help.addAll(poll.nexts);
        }
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

        BFS(a);
    }
}
