package com.mashibing.dailyPractice.round3._No71_100;

import java.util.*;

/**
 * 图的拓扑排序
 */
public class TopologySort3_0716 {
    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    static class Record {
        private DirectedGraphNode node;
        private int depth;

        public Record(DirectedGraphNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        if (graph == null || graph.isEmpty()) {
            return graph;
        }

        Map<DirectedGraphNode, Record> map = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            recurse(node, map);
        }

        List<Record> list = new ArrayList<>(map.values());
        list.sort(new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return o2.depth - o1.depth;
            }
        });

        ArrayList<DirectedGraphNode> retList = new ArrayList<>();
        for (Record record : list) {
            retList.add(record.node);
        }

        return retList;
    }

    private Record recurse(DirectedGraphNode node, Map<DirectedGraphNode, Record> map) {
        if (map.containsKey(node)) {
            return map.get(node);
        }

        int depth = 0;
        for (DirectedGraphNode neighbor : node.neighbors) {
            depth = Math.max(depth, recurse(neighbor, map).depth);
        }

        Record record = new Record(node, depth + 1);
        map.put(node, record);
        return record;
    }
}
