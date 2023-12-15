package com.mashibing.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 图的深度优先遍历（Depth First Search）
 * 思路：由于是深度优先遍历，当遍历到某个节点时，如果它有多个出度，那么会选择其中一个出度继续走下去，直到这个出度走完，然后按照遍历过的节点原路返回，
 * 目的是找到经历过的节点还有没有其它出度，如果有的话就走另一条路，也就是"怎么来的就怎么回去"，直到探测完所有节点的所有出度。
 * 所以，辅助的容器结构就不能用FIFO的队列了，得用栈，因为DFS还要原路返回，而BST就不需要。
 * 整个过程要注意几点：
 * 1、打印的时机要在入栈的时候，而不是出栈的时候，因为一个节点要入栈出栈多次，而出栈是无条件的，入栈是有条件的，条件就是该节点的其中一个邻居节点不在Set中，
 * 所以如果出栈打印，会打印重复数据。
 * 2、在把一个节点的邻居节点压入栈之前，先把该节点入栈，这一步不能省且顺序不能错，因为栈里从栈头到栈尾保存的是一条完整的路径，后续还要倒序退回来，靠的就是这个栈。
 * 假如A的邻居节点是B，遍历的时候是先遍历A在遍历B，等到最后回退的时候是先遍历B后遍历A，所以在正序遍历的时候必须先把A入栈，再把邻居B入栈，回退的时候顺序正好相反。
 *
 * 课程：体系班课时142
 */
public class DFS {
    public static void dfs(GraphNode<Integer> node) {
        if(node == null) {
            return;
        }

        Stack<GraphNode<Integer>> stack = new Stack<>();
        Set<GraphNode<Integer>> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.print(node.data + "\t");
        while(!stack.isEmpty()) {
            GraphNode<Integer> n = stack.pop();
            for(GraphNode<Integer> next : n.nexts) {
                if(set.contains(next)) {
                    continue;
                }

                set.add(next);
                stack.add(n);// 先把自己入栈，后将邻居入栈
                stack.add(next);
                System.out.print(next.data + "\t");
                break;// 栈里任意加入一个n的邻居即可，因为走的是其中一条路，其它的邻居等退回来之后会遍历到
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
        b.nexts.add(g);
        d.nexts.add(e);
        e.nexts.add(c);
        c.nexts.add(f);
        f.nexts.add(e);

        dfs(a);
    }
}
