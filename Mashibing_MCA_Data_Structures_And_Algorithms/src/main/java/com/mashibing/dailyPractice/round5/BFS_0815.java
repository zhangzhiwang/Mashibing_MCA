package com.mashibing.dailyPractice.round5;

import com.mashibing.graph.GraphNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 图的宽度优先遍历
 */
public class BFS_0815 {
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

    // 老师的代码
    public static void bfs_teacher(GraphNode<Integer> start) {
        if (start == null) {
            return;
        }
        Queue<GraphNode<Integer>> queue = new LinkedList<>();
        HashSet<GraphNode<Integer>> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            GraphNode<Integer> cur = queue.poll();
            System.out.print(cur.data + "\t");
            for (GraphNode<Integer> next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
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

        System.out.println("correct ans:");
        bfs_teacher(a);
        System.out.println();
        System.out.println("my ans:");
        bfs(a);
        System.out.println();
    }
}
