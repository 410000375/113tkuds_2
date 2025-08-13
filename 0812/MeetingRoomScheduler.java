import java.util.*;

public class MeetingRoomScheduler {

    // ========= 部分 1：計算最少需要的會議室 =========
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        // 依開始時間排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Min Heap 儲存每個會議室的結束時間
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] interval : intervals) {
            // 如果最早結束的會議室已經空了，就可以直接用
            if (!minHeap.isEmpty() && minHeap.peek() <= interval[0]) {
                minHeap.poll();
            }
            minHeap.offer(interval[1]);
        }
        return minHeap.size();
    }

    // ========= 部分 2：限制 N 個會議室時最大化總會議時間 =========
    public static int maxMeetingTimeWithNRooms(int[][] intervals, int N) {
        // 先按照結束時間排序（區間排程常用排序）
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        // 如果 N == 1，就是經典的最大化不重疊會議總時長（Greedy）
        if (N == 1) {
            int totalTime = 0;
            int lastEnd = 0;
            for (int[] interval : intervals) {
                if (interval[0] >= lastEnd) {
                    totalTime += (interval[1] - interval[0]);
                    lastEnd = interval[1];
                }
            }
            return totalTime;
        }

        // 如果 N > 1，用多會議室版本的 Greedy（同時追蹤每個會議室的結束時間）
        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        int totalTime = 0;

        for (int[] interval : intervals) {
            // 檢查有沒有空房間（最早結束的 <= interval[0]）
            if (!rooms.isEmpty() && rooms.peek() <= interval[0]) {
                rooms.poll();
            }
            if (rooms.size() < N) {
                rooms.offer(interval[1]);
                totalTime += (interval[1] - interval[0]);
            }
        }
        return totalTime;
    }