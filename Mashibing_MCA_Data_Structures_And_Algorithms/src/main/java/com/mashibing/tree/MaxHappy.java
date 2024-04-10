package com.mashibing.tree;

/**
 * 题目：一棵多叉树代表一个公司的组织架构，现在公司搞聚会，给员工发请柬，规则是如果给某个员工发了请柬那么就不能给他的直接上级和直接下级发请柬，
 * 每个员工都有自己的快乐值happy，收到请柬的员工去参加聚会，求参加聚会的员工快乐值总和的最大值，其中快乐值是非负数。
 * 解释：每一个员工可以看做是一个节点，员工的快乐值就是节点的数据data，公司给员工发请柬会有多种方案，求这些方案里面哪一个方案请到的员工快乐值总和最大。
 * 思路：
 * 使用通用递归套路，任意找一个节点x，结果分为两个大的情况：
 * 情况一：结果和x无关
 * 这种情况就是不给x发请柬，把x看做是以x为头结点的子树，也就是不邀请头结点，那么它的直接子节点可能被邀请也可能不被邀请，
 * 那么就分别求出它子节点被邀请时快乐值总和的最大值，和不被邀请时快乐值总和的最大值，这两个最大值求max。
 * 情况二：结果和x有关
 * 也就是邀请头结点，按照规则它的直接子节点就不能被邀请，那么快乐值总和肯定包括x自己的快乐值，即x.data，然后再加上直接子节点不被邀请时的最大快乐值总和即可。
 * 然后整体情况一和情况二求max。
 * 通过以上分析，头结点收集子节点的信息就包括两个：被邀请时的快乐值总和，和不被邀请时的快乐值总和。
 */
public class MaxHappy {
    static class MaxHappyInfo {
        public int invite;// 假设当前节点是x，以当前节点为头的子树是T，invite表示当x节点被邀请时整棵树T的最大sum(happy)值
        public int notInvite;// notInvite表示当x节点不被邀请时整棵树T的最大sum(happy)值

        public MaxHappyInfo(int invite, int notInvite) {
            this.invite = invite;
            this.notInvite = notInvite;
        }
    }

    /**
     *
     * @param head NaryTreeNode中的data就相当于本题的happy值
     * @return
     */
    public static int maxHappy(NaryTreeNode<Integer> head) {
        if(head == null) {
            return 0;
        }

        return Math.max(recurse(head).invite, recurse(head).notInvite);
    }

    public static MaxHappyInfo recurse(NaryTreeNode<Integer> x) {
        if(x == null) {
            return new MaxHappyInfo(0, 0);
        }

        // 先求x的invite，再求x的notInvite，然后返回max(invite, notInvite)
        /*
        1、假设以x为头的子树是T，invite表示当x节点被邀请时整棵树T的最大sum(happy)值。
        既然x被邀请，那么invite一定是包含x自己的快乐值的，所以invite的初始值就是x自己的快乐值。
        如果x被邀请，那么x的直接下级就不能被邀请了。
        2、notInvite表示x不被邀请时，T的最大sum(happy)值，既然x没有被邀请，那么notInvite自然不包含x自己的快乐值，所以初始值是0。
        x不被邀请，x的直接下级可能被邀请也可能不被邀请。
         */
        int invite = x.data;
        int notInvite = 0;
        for(NaryTreeNode<Integer> child : x.children) {
            // 向孩子收集信息
            MaxHappyInfo childInfo = recurse(child);
            invite += childInfo.notInvite;
            notInvite += Math.max(childInfo.invite, childInfo.notInvite);
        }

        return new MaxHappyInfo(invite, notInvite);
    }

    // 以下是对数器
    public static int maxHappy1(NaryTreeNode<Integer> boss) {
        if (boss == null) {
            return 0;
        }
        return process1(boss, false);
    }

    public static int process1(NaryTreeNode<Integer> cur, boolean up) {
        if (up) { // 如果cur的上级来的话，cur没得选，只能不来
            int ans = 0;
            for (NaryTreeNode<Integer> next : cur.children) {
                ans += process1(next, false);
            }
            return ans;
        } else { // 如果cur的上级不来的话，cur可以选，可以来也可以不来
            int p1 = cur.data;
            int p2 = 0;
            for (NaryTreeNode<Integer> next : cur.children) {
                p1 += process1(next, true);
                p2 += process1(next, false);
            }
            return Math.max(p1, p2);
        }
    }

    // for test
    public static NaryTreeNode<Integer> genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        NaryTreeNode<Integer> boss = new NaryTreeNode<Integer>((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(NaryTreeNode<Integer> e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            NaryTreeNode<Integer> next = new NaryTreeNode<Integer>((int) (Math.random() * (maxHappy + 1)));
            e.children.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            NaryTreeNode<Integer> boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy1(boss) != maxHappy(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
