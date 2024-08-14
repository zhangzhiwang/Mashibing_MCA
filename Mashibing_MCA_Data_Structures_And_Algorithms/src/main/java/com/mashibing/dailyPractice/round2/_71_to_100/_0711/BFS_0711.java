package com.mashibing.dailyPractice.round2._71_to_100._0711;

import com.mashibing.graph.GraphNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 图的宽度优先遍历
 * 思路：和二叉树的按层遍历类似，需要一个help队列和Set（因为结果是打印出来所以需要一个队列即可）
 */
public class BFS_0711 {
    public static void bfs(GraphNode<Integer> root) {
        if(root == null) {
            return;
        }

        Queue<GraphNode<Integer>> help = new LinkedList<>();
        help.add(root);
        Set<GraphNode<Integer>> set = new HashSet<>();
        while (!help.isEmpty()) {
            GraphNode<Integer> poll = help.poll();
            if(set.contains(poll)) {
                continue;
            }

            System.out.print(poll.data + "\t");
            set.add(poll);
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
        a.nexts.add(c);
        a.nexts.add(d);
        b.nexts.add(e);
        b.nexts.add(f);
        e.nexts.add(b);
        c.nexts.add(g);
        g.nexts.add(b);

        bfs(a);
    }
}
