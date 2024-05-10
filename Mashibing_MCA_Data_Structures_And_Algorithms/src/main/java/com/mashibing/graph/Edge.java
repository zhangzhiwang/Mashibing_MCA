package com.mashibing.graph;

/**
 * 节点之间的连线
 */
public class Edge<T> {
    public int weight;// 权重，可以看做连线的长度
    public GraphNode<T> from;// 这条边从from节点连向to节点
    public GraphNode<T> to;

    public Edge(int weight) {
        this.weight = weight;
    }

    public Edge(int weight, GraphNode<T> from, GraphNode<T> to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    // toString()方法可写可不写，这里主要是为了打印测试方便
    @Override
    public String toString() {
        return weight + "";
    }
}
