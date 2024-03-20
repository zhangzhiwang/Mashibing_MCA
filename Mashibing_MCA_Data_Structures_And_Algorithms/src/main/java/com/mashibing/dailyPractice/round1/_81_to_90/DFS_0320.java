package com.mashibing.dailyPractice.round1._81_to_90;

import com.mashibing.graph.GraphNode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 图的深度优先遍历
 */
public class DFS_0320 {
    public static void dfs(GraphNode<Integer> head) {
        if(head == null) {
            return;
        }

        Stack<GraphNode<Integer>> stack = new Stack<>();
        Set<GraphNode<Integer>> set = new HashSet<>();
        stack.add(head);
        set.add(head);
        System.out.print(head.data + "\t");
        while (!stack.isEmpty()) {
            GraphNode<Integer> pop = stack.pop();
            for (GraphNode<Integer> child: pop.nexts) {
                if(set.contains(child)) {
                    continue;
                }

                set.add(child);
                stack.add(pop);
                stack.add(child);
                System.out.print(child.data + "\t");
                break;
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
        GraphNode<Integer> h = new GraphNode<>(8);
        a.nexts.add(b);
        a.nexts.add(c);
        a.nexts.add(d);
        b.nexts.add(e);
        b.nexts.add(f);
        c.nexts.add(g);
        c.nexts.add(b);
        e.nexts.add(b);
        g.nexts.add(b);
        g.nexts.add(h);
        dfs(a);
    }
}
