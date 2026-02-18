package algo.practice.a_interviewpractice.neetcode150.intervals;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time interval objects consisting of start and end times [[start_1,end_1],[start_2,end_2],...] (start_i < end_i), find the minimum number of rooms required to schedule all meetings without any conflicts.
 *
 * Note: (0,8),(8,10) is NOT considered a conflict at 8.
 *
 * Example 1:
 * Input: intervals = [(0,40),(5,10),(15,20)]
 * Output: 2
 * Explanation:
 * day1: (0,40)
 * day2: (5,10),(15,20)
 *
 * Example 2:
 * Input: intervals = [(4,9)]
 * Output: 1
 *
 * Approach:
 * - Sort all intervals by start time.
 * - Use a min-heap (priority queue) to track meeting end times.
 * - For each meeting, if its start time is >= the earliest end time in the heap, reuse the room (remove from heap).
 * - Otherwise, allocate a new room (add to heap).
 * - The size of the heap at any time is the number of rooms required.
 * This ensures we always minimize the room count by reusing rooms when possible.
 * Time Complexity: O(n log n) due to sorting and heap operations.
 */
public class MeetingRooms2 {
    public int minMeetingRooms(List<Interval> intervals) {
        // Edge case: No meetings
        if (intervals == null || intervals.size() == 0) return 0;

        // Sort intervals by start time
        Collections.sort(intervals, Comparator.comparingInt(a -> a.start));

        // Min-heap (priority queue) to keep track of end times of rooms in use
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (Interval interval : intervals) {
            // If room is free (meeting has ended), remove it from heap
            if (!minHeap.isEmpty() && interval.start >= minHeap.peek()) {
                minHeap.poll();
            }
            // Allocate a new room (add end time to the heap)
            minHeap.offer(interval.end);
        }

        // The size of the heap is the minimum number of rooms required
        return minHeap.size();

    }

    public class Interval {
        public int start, end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // Simple test
    public static void main(String[] args) {
        MeetingRooms2 solution = new MeetingRooms2();
        List<Interval> intervals1 = List.of(
                solution.new Interval(0, 40),
                solution.new Interval(5, 10),
                solution.new Interval(15, 20));
        List<Interval> intervals2 = List.of(solution.new Interval(4, 9));

        System.out.println("Output 1: " + solution.minMeetingRooms(intervals1)); // 2
        System.out.println("Output 2: " + solution.minMeetingRooms(intervals2)); // 1
    }
}
