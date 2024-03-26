package com.mashibing.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 图的节点
 */
public class GraphNode<T> {
    public T data;// 节点数据
    public int in;// 入度（连接到该节点的边）数量
    public int out;// 出度（从该节点出来的边）数量
    public List<GraphNode<T>> nexts = new ArrayList<>();// 该节点直接连向的节点们
    public List<Edge<T>> edges = new ArrayList<>();// 从该节点出发的边

    public GraphNode(T data) {
        this.data = data;
    }

    public GraphNode(T data, int in, int out) {
        this.data = data;
        this.in = in;
        this.out = out;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
