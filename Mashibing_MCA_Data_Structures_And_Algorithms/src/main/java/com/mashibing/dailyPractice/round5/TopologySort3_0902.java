package com.mashibing.dailyPractice.round5;

import java.util.*;

/**
 * 图的拓扑排序
 */
public class TopologySort3_0902 {
    class DirectedGraphNode {
      int label;
      List<DirectedGraphNode> neighbors;
      DirectedGraphNode(int x) {
          label = x;
          neighbors = new ArrayList<DirectedGraphNode>();
      }
    }

    class DirectedGraphNodeInfo {
        private DirectedGraphNode node;
        private int depth;

        public DirectedGraphNodeInfo(DirectedGraphNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        if(graph == null || graph.isEmpty()) {
            return graph;
        }

        Map<DirectedGraphNode, DirectedGraphNodeInfo> map = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            recurse(node, map);
        }

        ArrayList<DirectedGraphNodeInfo> list = new ArrayList<>(map.values());
        list.sort(new Comparator<DirectedGraphNodeInfo>() {
            @Override
            public int compare(DirectedGraphNodeInfo o1, DirectedGraphNodeInfo o2) {
                return o2.depth - o1.depth;
            }
        });

        ArrayList<DirectedGraphNode> retList = new ArrayList<>();
        for (DirectedGraphNodeInfo info : list) {
            retList.add(info.node);
        }
        return retList;
    }

    private DirectedGraphNodeInfo recurse(DirectedGraphNode x, Map<DirectedGraphNode, DirectedGraphNodeInfo> map) {
        if(map.containsKey(x)) {
            return map.get(x);
        }

        int maxDepth = 0;
        for (DirectedGraphNode neighbor : x.neighbors) {
            maxDepth = Math.max(recurse(neighbor, map).depth, maxDepth);
        }

        DirectedGraphNodeInfo info = new DirectedGraphNodeInfo(x, maxDepth + 1);
        map.put(x, info);
        return info;
    }

    public static void main(String[] args) {
        // lintcode:https://www.lintcode.com/problem/127/
    }
}
