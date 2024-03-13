package com.mashibing.graph;

import java.util.*;

/**
 * 最小生成树实现方式2——P算法（Prim）
 * 思路：点解锁边，边界锁点，点再解锁边：点-边-点-边-点-...，边在解锁点时要选已经解锁的边中权重最小的那个。
 * 求最小生成树的图一般都是无向图，或者可以理解为双向的，在无向图中，任意两个点之间必然至少存在一条通路，因为点和点之间是双向的，如果任意两点之间不存在通路，
 * 那么这两个点一定是在两个不同的图里面，可以理解为集群中的脑裂，还有在无向图中，从任意一个节点出发都能遍历完整个图的所有节点。所以最小生成树算法可以从任意一个点开始，如果是有向图就必须要给出起始的节点，那么在有向图中，
 * 将任意一个节点X作为起点，那么做种找到的最小生成树就是以X为起点的子图的最小生成树，所以有向图的最小生成树是一个相对的概念。
 *
 * 如果面试时要求找到最小生成树，那么K算法和P算法写其中一个即可。
 *
 * 课程：体系班课时149-151
 */
public class Prim {
    public static Set<Edge<String>> prim(Graph<String> graph) {
        Set<Edge<String>> resultEdgeSet = new HashSet<>();
        if(graph == null || graph.nodes.isEmpty()) {
            return resultEdgeSet;
        }

        // 存放已解锁的节点，在边解锁点时，这个点可能已经被解锁过了，由于是无向图，如果不把解锁过的节点存起来很有可能出现循环
        Set<GraphNode<String>> unlockedNodeSet = new HashSet<>();
        // 点解锁边之后，可能一个点解锁了很多边，下一步选哪条边解锁点？选已解锁的边中权重最小的去解锁点，这里用到小根堆将所有解锁的边放进去
        PriorityQueue<Edge<String>> smallHeap = new PriorityQueue<>(new Comparator<Edge<String>>() {
            @Override
            public int compare(Edge<String> o1, Edge<String> o2) {
                return o1.weight - o2.weight;
            }
        });

        for(GraphNode<String> node : graph.nodes.values()) {// 由于是无向图，所以从任意一个节点出发都能遍历完整个图的所有节点
            if(unlockedNodeSet.contains(node)) {
                continue;
            }

            unlockedNodeSet.add(node);
            smallHeap.addAll(node.edges);// 如果面试时忘记了smallHeap有一个addAll方法，也可以写一个for循环，再循环里去add每一个边
            while(!smallHeap.isEmpty()) {
                Edge<String> edge = smallHeap.poll();// 弹出来的是已解锁的边中权重最小的
                GraphNode<String> toNode = edge.to;// 为什么只看to节点不看from节点，因为在当初这条边被解锁的时候，解锁它的那个点就是它的from，所以不用看from
                if(unlockedNodeSet.contains(toNode)) {
                    continue;
                }

                /*
                注意：测试时遇到的问题：一定要在确定toNode没有被解锁过之后再把edge加入到结果集里面，如果顺序颠倒，结果集里面会添加两个一样的edge。
                原因就是它是一个无向图，在构造无向图的时候实际上节点a和b之间存在两个edge，权重一样，from和to互相指，见下面的main方法测试。
                这是两个不同的edge对象，所以最终返回的resultEdgeSet无法去重。
                 */
                resultEdgeSet.add(edge);
                unlockedNodeSet.add(toNode);// 将新解锁的点放到unlockedNodeSet里，注意toNode不可能为空，如果为空说明入参graph的结构就有问题，在做算法题时不用判空，实际做项目时为了严谨最好判一下空
                if(toNode.edges.isEmpty()) {
                    continue;
                }

                // 走到这里说明toNode后面还有边就将toNode后面的边全部加入小根堆
                smallHeap.addAll(toNode.edges);// 同理，如果现场写算法想不起来addAll方法，就循环add
            }

            /*
            前面也说了：无向图中从任意一个节点出发都能顺藤摸瓜遍历完每一个节点，所以最外层的for循环在第一次循环结束后就能得到最小生成树了，循环结束前直接break掉。
            那为什么还写最外层的循环呢？就是防止森林。因为在入参的graph中，里面的nodes组成的可能不是同一个图，不同的图之间没有任何edge关联，完全割裂的，就好比脑裂，
            这种情况下就要遍历完graph里面的所有节点了。但如果graph里面的节点组成的就是一个图，那么就不需要外面你的for循环了，或者在第一次循环结束前break掉。
             */
            // break;
        }

        return resultEdgeSet;
    }

    public static void main(String[] args) {
        GraphNode<String> a = new GraphNode<>("a");
        GraphNode<String> b = new GraphNode<>("b");
        GraphNode<String> c = new GraphNode<>("c");
        GraphNode<String> d = new GraphNode<>("d");
        GraphNode<String> e = new GraphNode<>("e");
        GraphNode<String> f = new GraphNode<>("f");
        a.nexts.add(b);
        a.nexts.add(c);
        a.nexts.add(d);
        b.nexts.add(a);
        b.nexts.add(c);
        b.nexts.add(d);
        b.nexts.add(e);
        b.nexts.add(f);
        c.nexts.add(a);
        c.nexts.add(b);
        c.nexts.add(e);
        d.nexts.add(a);
        d.nexts.add(b);
        d.nexts.add(f);
        e.nexts.add(b);
        e.nexts.add(c);
        e.nexts.add(f);
        f.nexts.add(b);
        f.nexts.add(d);
        f.nexts.add(e);

        Edge<String> a_c_7 = new Edge<>(7, a, c);
        Edge<String> a_b_1 = new Edge<>(1, a, b);
        Edge<String> a_d_3 = new Edge<>(3, a, d);
        a.edges.add(a_c_7);
        a.edges.add(a_b_1);
        a.edges.add(a_d_3);
        Edge<String> b_a_1 = new Edge<>(1, b, a);
        Edge<String> b_c_4 = new Edge<>(4, b, c);
        Edge<String> b_d_1 = new Edge<>(1, b, d);
        Edge<String> b_e_9 = new Edge<>(9, b, e);
        Edge<String> b_f_6 = new Edge<>(6, b, f);
        b.edges.add(b_a_1);
        b.edges.add(b_c_4);
        b.edges.add(b_d_1);
        b.edges.add(b_e_9);
        b.edges.add(b_f_6);
        Edge<String> c_a_7 = new Edge<>(7, c, a);
        Edge<String> c_b_4 = new Edge<>(4, c, b);
        Edge<String> c_e_10 = new Edge<>(10, c, e);
        c.edges.add(c_a_7);
        c.edges.add(c_b_4);
        c.edges.add(c_e_10);
        Edge<String> d_a_3 = new Edge<>(3, d, a);
        Edge<String> d_b_1 = new Edge<>(1, d, b);
        Edge<String> d_f_4 = new Edge<>(4, d, f);
        d.edges.add(d_a_3);
        d.edges.add(d_b_1);
        d.edges.add(d_f_4);
        Edge<String> e_c_10 = new Edge<>(10, e, c);
        Edge<String> e_b_9 = new Edge<>(9, e, b);
        Edge<String> e_f_1 = new Edge<>(1, e, f);
        e.edges.add(e_c_10);
        e.edges.add(e_b_9);
        e.edges.add(e_f_1);
        Edge<String> f_e_1 = new Edge<>(1, f, e);
        Edge<String> f_b_6 = new Edge<>(6, f, b);
        Edge<String> f_d_4 = new Edge<>(4, f, d);
        f.edges.add(f_e_1);
        f.edges.add(f_b_6);
        f.edges.add(f_d_4);

        Graph<String> graph = new Graph<>();
        Map<String, GraphNode<String>> nodes = graph.nodes;
        nodes.put("a", a);
        nodes.put("b", b);
        nodes.put("c", c);
        nodes.put("d", d);
        nodes.put("e", e);
        nodes.put("f", f);

        Set<Edge<String>> edges = graph.edges;
        edges.add(a_c_7);
        edges.add(a_b_1);
        edges.add(a_d_3);

        edges.add(b_a_1);
        edges.add(b_c_4);
        edges.add(b_d_1);
        edges.add(b_e_9);
        edges.add(b_f_6);

        edges.add(c_a_7 );
        edges.add(c_b_4 );
        edges.add(c_e_10);

        edges.add(d_a_3);
        edges.add(d_b_1);
        edges.add(d_f_4);

        edges.add(e_c_10);
        edges.add(e_b_9);
        edges.add(e_f_1);

        edges.add(f_e_1);
        edges.add(f_b_6);
        edges.add(f_d_4);

        Set<Edge<String>> edgeSet = prim(graph);
//        Set<Edge<String>> edgeSet = primMST(graph);
        System.out.println(edgeSet);
    }
}
