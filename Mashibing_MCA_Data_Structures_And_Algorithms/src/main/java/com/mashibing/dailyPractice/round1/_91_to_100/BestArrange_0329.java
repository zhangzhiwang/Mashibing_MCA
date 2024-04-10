package com.mashibing.dailyPractice.round1._91_to_100;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法第2个题目：
 * 有好多项目组都想开会，并且都给出了自己会议的开始时间和结束时间并提交到管理员那里，管理员收集上来之后将各个项目组的情况放到一个数组里。
 * 现在只有一个会议室，管理员来安排会议，一个会议室同一时间之内被一个项目组占用，返回这个会议室最多能够安排多少会议。
 */
public class BestArrange_0329 {
    static class Meeting {
        private int start;
        private int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    public static int bestArrange(Meeting[] meetings) {
        if(meetings == null || meetings.length == 0) {
            return 0;
        }

        Arrays.sort(meetings, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o1.end - o2.end;
            }
        });

        int lastEnd = 0;
        int count = 0;
        for (Meeting meeting : meetings) {
            if(meeting.start < lastEnd) {
                continue;
            }
            
            lastEnd = meeting.end;
            count++;
        }
        
        return count;
    }

    // 对数器

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

    private static Meeting[] intArrToMeetingArr(int[][] arr) {
        Meeting[] meetings = new Meeting[arr.length];
        for (int i = 0; i < arr.length; i++) {
            meetings[i] = new Meeting(arr[i][0], arr[i][1]);
        }

        return meetings;
    }

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
            Meeting[] meetings = generateMeetings(MeetingSize, timeMax);
            if (bestArrange(meetings) != getMaxMeetingCountForDuiShuQi(meetings)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
