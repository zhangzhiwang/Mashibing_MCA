package com.mashibing.dailyPractice.round2._71_to_100;

import com.mashibing.dailyPractice.round1._81_to_90.TopologySort3_0321;

import java.util.ArrayList;
import java.util.List;

/**
 * 图的拓扑排序
 * 
 * @Auth zhangzhiwang
 * @Date 2024/4/11 08:44
 */
public class TopologySort3_0411 {
    /**
     * lintcode（不是leetcode）
     */
    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }
}
