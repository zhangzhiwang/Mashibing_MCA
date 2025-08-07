package com.mashibing.dailyPractice.round6;

import com.mashibing.graph.GraphNode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 图的深度优先遍历
 */
public class DFS_0909 {
    public static void dfs(GraphNode<Integer> root) {
        if(root == null) {
            return;
        }

        Stack<GraphNode<Integer>> stack = new Stack<>();
        stack.add(root);
        System.out.print(root.data + "\t");
        Set<GraphNode<Integer>> set = new HashSet<>();
        set.add(root);
        while (!stack.isEmpty()) {
            GraphNode<Integer> pop = stack.pop();
            for (GraphNode<Integer> next : pop.nexts) {
                if(set.contains(next)) {
                    continue;
                }

                System.out.print(next.data + "\t");
                stack.add(pop);
                stack.add(next);
                set.add(next);
                break;
            }
        }
    }

    // 老师的代码
    public static void dfs_teacher(GraphNode<Integer> node) {
        if (node == null) {
            return;
        }
        Stack<GraphNode<Integer>> stack = new Stack<>();
        HashSet<GraphNode<Integer>> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.print(node.data + "\t");
        while (!stack.isEmpty()) {
            GraphNode<Integer> cur = stack.pop();
            for (GraphNode<Integer> next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.print(next.data + "\t");
                    break;
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

        System.out.println("correct ans:");
        dfs_teacher(a);
        System.out.println();
        System.out.println("my ans:");
        dfs(a);
    }
}
