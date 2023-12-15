package com.mashibing.greed;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法第二个题目：有好多项目组都想开会，并且都给出了自己会议的开始时间和结束时间并提交到管理员那里，管理员收集上来之后将各个项目组的情况放到一个数组里。
 * 现在只有一个会议室，管理员来安排会议，一个会议室同一时间之内被一个项目组占用，返回这个会议室最多能够安排多少会议。
 * 解释：
 * 管理员收集上来的信息是这样的：[[start1, end1],[start2, end2],[start3, end3],...]
 * 每一个子数组都代表一个项目组开会的开始时间和结束时间，规定开始和结束时间都必须是非负数。
 * 思路：
 * 正确的贪心思路：
 * 1、所有会议按照结束时间从小到大排序
 * 2、用一个变量记录结束时间，遍历排序后的数组，每遍历一个元素都判一下该元素的开始时间是不是大于记录的结束时间，如果大于就可以安排，否则就跳过，
 * 然后设置一个变量用于记录可以安排的会议数量。
 *
 * 课程：体系班课时113-115
 */
public class BestArrange {
    static class Meeting {
        public int start;
        public int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static class MeetingComparator implements Comparator<Meeting> {
        @Override
        public int compare(Meeting o1, Meeting o2) {
            return o1.end - o2.end;// 此题贪心算法的难点就在于能够想到使用结束时间排序，而不是使用开始时间或者会议持续时长排序
        }
    }

    /**
     * 为了迎合对数器的测试，将入参直接设置为Meeting[]
     *
     * @param meetings
     * @return
     */
    public static int getMaxMeetingCountForDuiShuQi(Meeting[] meetings) {
        Arrays.sort(meetings, new MeetingComparator());

        int end = 0;
        int count = 0;
        for (Meeting meeting : meetings) {
            if (meeting.start >= end) {
                count++;
                end = meeting.end;
            }
        }

        return count;
    }

    /**
     * 题目要求的方法
     *
     * @param arr
     * @return
     */
    public static int getMaxMeetingCount(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        Meeting[] meetings = intArrToMeetingArr(arr);
        // 按照结束时间排序
        Arrays.sort(meetings, new MeetingComparator());

        int end = 0;
        int count = 0;
        for (Meeting meeting : meetings) {
            if (meeting.start >= end) {
                count++;
                end = meeting.end;
            }
        }

        return count;
    }

    private static Meeting[] intArrToMeetingArr(int[][] arr) {
        Meeting[] meetings = new Meeting[arr.length];
        for (int i = 0; i < arr.length; i++) {
            meetings[i] = new Meeting(arr[i][0], arr[i][1]);
        }

        return meetings;
    }

    // 以下是对数器
    public static Meeting[] generateMeetings(int MeetingSize, int timeMax) {
        Meeting[] ans = new Meeting[(int) (Math.random() * (MeetingSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Meeting(r1, r1 + 1);
            } else {
                ans[i] = new Meeting(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static int process(Meeting[] Meetings, int done, int timeLine) {
        if (Meetings.length == 0) {
            return done;
        }
        // 还剩下会议
        int max = done;
        // 当前安排的会议是什么会，每一个都枚举
        for (int i = 0; i < Meetings.length; i++) {
            if (Meetings[i].start >= timeLine) {
                Meeting[] next = copyButExcept(Meetings, i);
                max = Math.max(max, process(next, done + 1, Meetings[i].end));
            }
        }
        return max;
    }

    public static Meeting[] copyButExcept(Meeting[] Meetings, int i) {
        Meeting[] ans = new Meeting[Meetings.length - 1];
        int index = 0;
        for (int k = 0; k < Meetings.length; k++) {
            if (k != i) {
                ans[index++] = Meetings[k];
            }
        }
        return ans;
    }

    public static int bestArrange1(Meeting[] Meetings) {
        if (Meetings == null || Meetings.length == 0) {
            return 0;
        }
        return process(Meetings, 0, 0);
    }

    public static void main(String[] args) {
        int MeetingSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Meeting[] Meetings = generateMeetings(MeetingSize, timeMax);
            if (bestArrange1(Meetings) != getMaxMeetingCountForDuiShuQi(Meetings)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
