package com.mashibing.dailyPractice.round2._71_to_100._0711;

import com.mashibing.dailyPractice.round2._71_to_100._0708.BestArrange_0708;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 贪心算法第2个题目：有好多项目组都想开会，并且都给出了自己会议的开始时间和结束时间并提交到管理员那里，管理员收集上来之后将各个项目组的情况放到一个数组里。
 * 现在只有一个会议室，管理员来安排会议，一个会议室同一时间只能被一个项目组占用，返回这个会议室最多能够安排多少会议。
 */
public class BestArrange_0711 {
    static class Meeting_0711 {
        private int start;
        private int end;

        public Meeting_0711(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // 对数器的入参不是int[][]，这里转换一下，此方法纯属迎合对数器，非本题需要
    public static int bestArrangeTrans(Meeting_0711[] meetings) {
        int[][] arr = new int[meetings.length][2];
        for (int i = 0; i < meetings.length; i++) {
            arr[i][0] = meetings[i].start;
            arr[i][1] = meetings[i].end;
        }

        return bestArrange(arr);
    }

    public static int bestArrange(int[][] arr) {
        if(arr == null || arr.length == 0) {
            return 0;
        }
        
        List<Meeting_0711> list = new ArrayList<>();
        for (int[] a : arr) {
            list.add(new Meeting_0711(a[0], a[1]));
        }
        list.sort(new Comparator<Meeting_0711>() {
            @Override
            public int compare(Meeting_0711 o1, Meeting_0711 o2) {
                return o1.end - o2.end;
            }
        });
        
        int end = 0;
        int count = 0;
        for (Meeting_0711 meeting : list) {
            if (meeting.start >= end) {
                count++;
                end = meeting.end;
            }
        }
        
        return count;
    }

    // 对数器
    static class MeetingComparator implements Comparator<Meeting_0711> {
        @Override
        public int compare(Meeting_0711 o1, Meeting_0711 o2) {
            return o1.end - o2.end;// 此题贪心算法的难点就在于能够想到使用结束时间排序，而不是使用开始时间或者会议持续时长排序
        }
    }
    /**
     * 为了迎合对数器的测试，将入参直接设置为Meeting[]
     *
     * @param meetings
     * @return
     */
    public static int getMaxMeetingCountForDuiShuQi(Meeting_0711[] meetings) {
        Arrays.sort(meetings, new MeetingComparator());

        int end = 0;
        int count = 0;
        for (Meeting_0711 meeting : meetings) {
            if (meeting.start >= end) {
                count++;
                end = meeting.end;
            }
        }

        return count;
    }

    private static Meeting_0711[] intArrToMeetingArr(int[][] arr) {
        Meeting_0711[] meetings = new Meeting_0711[arr.length];
        for (int i = 0; i < arr.length; i++) {
            meetings[i] = new Meeting_0711(arr[i][0], arr[i][1]);
        }

        return meetings;
    }

    public static Meeting_0711[] generateMeetings(int MeetingSize, int timeMax) {
        Meeting_0711[] ans = new Meeting_0711[(int) (Math.random() * (MeetingSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Meeting_0711(r1, r1 + 1);
            } else {
                ans[i] = new Meeting_0711(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static int process(Meeting_0711[] Meetings, int done, int timeLine) {
        if (Meetings.length == 0) {
            return done;
        }
        // 还剩下会议
        int max = done;
        // 当前安排的会议是什么会，每一个都枚举
        for (int i = 0; i < Meetings.length; i++) {
            if (Meetings[i].start >= timeLine) {
                Meeting_0711[] next = copyButExcept(Meetings, i);
                max = Math.max(max, process(next, done + 1, Meetings[i].end));
            }
        }
        return max;
    }

    public static Meeting_0711[] copyButExcept(Meeting_0711[] Meetings, int i) {
        Meeting_0711[] ans = new Meeting_0711[Meetings.length - 1];
        int index = 0;
        for (int k = 0; k < Meetings.length; k++) {
            if (k != i) {
                ans[index++] = Meetings[k];
            }
        }
        return ans;
    }

    public static int bestArrange1(Meeting_0711[] Meetings) {
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
            Meeting_0711[] meetings = generateMeetings(MeetingSize, timeMax);
            int correctAns = bestArrange1(meetings);
            int myAns = bestArrangeTrans(meetings);
            if (correctAns != myAns) {
                System.out.println("correct ans:" + correctAns);
                System.out.println("my ans:" + myAns);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finish!");
    }
}
