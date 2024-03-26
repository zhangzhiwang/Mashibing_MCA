package com.mashibing.graph;

import java.util.*;

/**
 * 拓扑序（1）
 * 解释：拓扑序就是从头结点开始能够顺利走到尾结点，每一个节点都不缺少依赖，拓扑序是有序且无环的。
 * 比如java文件编译成class文件就是一个拓扑序。A类型需要引入B类，B类需要引入C类和D类，D类需要引入E类，编译的顺序是：
 * 先编译E类，这样D类的依赖就编译完了，下面可以编译D了，D的前面是B类，但是B还依赖C类，要把C类编译完才能编译B类，最后在、再编译A类，即：
 * E->D->C->B->A或者C->E->D->B->A，从头结点走到了尾结点，每一个节点都不缺少依赖，整个过程有序且无环。
 * 注意：拓扑序必须无环，否则无法遍历。
 *
 * 思路：
 * 1、找到入度是0的节点，这些节点的任意一个都可以作为开始节点，既然要输出拓扑序，说明给定的图肯定不会有环，所以肯定有入度为0的节点
 * 2、打印完一个节点，就将该节点的影响消除，影响就是该节点出度连着的那些邻居的入度要减1，如果哪个邻居节点的入度减为了0，那么它就是下一个开始节点。
 *
 * 课程：体系班课时143-145
 */
public class TopologySort1 {
    public static List<GraphNode<String>> topologySort(Graph<String> graph) {
        List<GraphNode<String>> list = new ArrayList<>();
        if(graph == null) {
            return list;
        }

        // key为结点，value为该节点剩余的入度数
        Map<GraphNode<String>, Integer> nodeLastInMap = new HashMap<>();
        // 剩余入度数已经为0的节点
        Queue<GraphNode<String>> zeroInNodeQueue = new LinkedList<>();

        for(GraphNode<String> node : graph.nodes.values()) {// 遍历整个图里面的所有节点
            nodeLastInMap.put(node, node.in);
            if(node.in == 0) {
                zeroInNodeQueue.add(node);
            }
        }

        while(!zeroInNodeQueue.isEmpty()) {
            GraphNode<String> node = zeroInNodeQueue.poll();
            list.add(node);
            // 去掉该node的影响
            for(GraphNode<String> next : node.nexts) {
                // 注意：使用nodeLastInMap操作节点的剩余in数量的目的是维护原始图结构不变，如果用next.in = --next.in;这样就破坏了原始图结构
                nodeLastInMap.put(next, nodeLastInMap.get(next) - 1);
                if(nodeLastInMap.get(next) == 0) {
                    zeroInNodeQueue.add(next);
                }
            }
        }

        return list;
    }

    public static void main(String[] args) {
        GraphNode<String> a = new GraphNode<>("a");
        GraphNode<String> b = new GraphNode<>("b");
        GraphNode<String> c = new GraphNode<>("c");
        GraphNode<String> d = new GraphNode<>("d");
        GraphNode<String> e = new GraphNode<>("e");
        a.nexts.add(b);
        e.nexts.add(b);
        b.nexts.add(c);
        c.nexts.add(d);
        b.in = 2;
        c.in = 1;
        d.in = 1;

        Graph<String> graph = new Graph<>();
        graph.nodes.put("a", a);
        graph.nodes.put("b", b);
        graph.nodes.put("c", c);
        graph.nodes.put("d", d);
        graph.nodes.put("e", e);

        List<GraphNode<String>> graphNodes = topologySort(graph);
        System.out.println(graphNodes);
    }
}
