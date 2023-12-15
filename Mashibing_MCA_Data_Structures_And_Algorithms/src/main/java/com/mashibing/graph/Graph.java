package com.mashibing.graph;

import java.util.*;

/**
 * 图
 */
public class Graph<T> {
    public Map<T, GraphNode<T>> nodes = new HashMap<>();// 整个图拥有的节点，key为节点的值，value为节点对象，这里用Map建立关联
    public Set<Edge<T>> edges = new HashSet<>();// 整个图拥有的边
}
