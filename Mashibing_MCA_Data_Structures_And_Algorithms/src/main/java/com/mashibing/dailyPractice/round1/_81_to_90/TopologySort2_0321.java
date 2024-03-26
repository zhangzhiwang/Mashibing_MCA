package com.mashibing.dailyPractice.round1._81_to_90;

import java.util.*;

/**
 * 图的拓扑排序（按点次）
 */
public class TopologySort2_0321 {
    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    class Record {
        private DirectedGraphNode node;
        private long count;

        public Record(DirectedGraphNode node, long count) {
            this.node = node;
            this.count = count;
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Record> map = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            recurse(node, map);
        }

        List<Record> recordList = new ArrayList<>(map.values());
        recordList.sort(new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
//                return o2.count - o1.count;
                return o1.count == o2.count ? 0 : (o1.count > o2.count ? -1 : 1);
            }
        });

        ArrayList<DirectedGraphNode> retList = new ArrayList<>();
        for (Record r : recordList) {
            retList.add(r.node);
        }

        return retList;
    }

    private Record recurse(DirectedGraphNode x, Map<DirectedGraphNode, Record> map) {
        if (map.containsKey(x)) {
            return map.get(x);
        }

        long count = 0;
        for (DirectedGraphNode neighbor : x.neighbors) {
            count += recurse(neighbor, map).count;
        }

        Record record = new Record(x, count + 1);
        map.put(x, record);
        return record;
    }
}
