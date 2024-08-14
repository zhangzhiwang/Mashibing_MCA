package com.mashibing.dailyPractice.round4;

import com.mashibing.tree.NaryTreeNode;

/**
 * 一棵多叉树代表一个公司的组织架构，现在公司搞聚会，给员工发请柬，规则是如果给某个员工发了请柬那么就不能给他的直接上级和直接下级发请柬，
 * 每个员工都有自己的快乐值happy，收到请柬的员工去参加聚会，求参加聚会的员工快乐值总和的最大值，其中快乐值是非负数。
 * 注意：同级别的同事之间没有限制，即邀请了A，和他同级的B可能被邀请也可能不会被邀请。
 */
public class MaxHappy_0803 {
    static class MaxHappyInfo_0803 {
        private int invited;
        private int notInvited;

        public MaxHappyInfo_0803(int invited, int notInvited) {
            this.invited = invited;
            this.notInvited = notInvited;
        }
    }

    public static int maxHappy(NaryTreeNode<Integer> root) {
        if(root == null) {
            return 0;
        }

        MaxHappyInfo_0803 info = recurse(root);
        return Math.max(info.invited, info.notInvited);
    }

    private static MaxHappyInfo_0803 recurse(NaryTreeNode<Integer> root) {
        if(root == null) {
            return new MaxHappyInfo_0803(0, 0);
        }

        int invited = root.data;
        int notInvited = 0;
        for (NaryTreeNode<Integer> child : root.children) {
            MaxHappyInfo_0803 info = recurse(child);
            invited += info.notInvited;
            notInvited += Math.max(info.invited, info.notInvited);
        }

        return new MaxHappyInfo_0803(invited, notInvited);
    }

    // 对数器
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
