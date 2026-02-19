package algo.practice.a_interviewpractice.neetcode150.intervals;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 *
 *
 * Given an array of meeting time interval objects consisting of start and end times [[start_1,end_1],[start_2,end_2],...] (start_i < end_i),
 * determine if a person could add all meetings to their schedule without any conflicts.
 *
 * Note: (0,8),(8,10) is not considered a conflict at 8
 *
 * Example 1:
 * Input: intervals = [(0,30),(5,10),(15,20)]
 * Output: false
 * Explanation:
 * (0,30) and (5,10) will conflict
 * (0,30) and (15,20) will conflict
 *
 * Example 2:
 * Input: intervals = [(5,8),(9,15)]
 * Output: true
 *
 * When to use sort by start time and when to use sort by end time
 *  * 1. Sort by Start Time
 *  * Use when:
 *  * You need to compare overlapping/contiguous intervals in their chronological order.
 *  * You want to process intervals in the order they begin.
 *  * Example Problems:
 *  * Meeting Rooms/Can Attend All Meetings: You need to check if any meeting starts before the previous one ends. So, sort by start time.
 *  * Merging Intervals: Process in order of start so you can merge as you go.
 *  * Why:
 *  * Sorting by start time naturally lines up intervals so that adjacent intervals are the only ones that can possibly overlap.
 *  *
 *  * 2. Sort by End Time
 *  * Use when:
 *  * You want to maximize or count non-overlapping intervals (greedy selection).
 *  * You want to always pick the interval that finishes earliest to free up resources/time.
 *  * Example Problems:
 *  * Interval Scheduling/Remove Overlapping Intervals: E.g., to find the maximum set of non-overlapping intervals, or to minimize removals for non-overlap, sort by end time.
 *  * Minimum Number of Arrows to Burst Balloons: Find the minimum number of points/lines to cover all intervals.
 *  * Why:
 *  * Sorting by end time allows a greedy approach: by always picking the interval that ends earliest, you maximize room for subsequent (possibly non-overlapping) intervals.
 *
 *
 * Approach:
 * Sort the list of intervals based on their start time.
 * Iterate through intervals and check if the start time of the current meeting
 * is earlier than the end time of the previous meeting. If so, there's a conflict and return false.
 * If no conflicts are found during the iteration, return true.
 * Time Complexity: O(n log n) due to sorting.
 */
public class MeetingRooms {
    public boolean canAttendMeetings(List<Interval> intervals) {
        // Edge case: 0 or 1 meeting, can always attend
        if (intervals == null || intervals.size() <= 1) return true;

        // Sort intervals based on start time
        Collections.sort(intervals, Comparator.comparingInt(a -> a.start));

        // Check for any overlap between consecutive meetings
        for (int i = 1; i < intervals.size(); i++) {
            // If current meeting starts before previous meeting ends, conflict found
            if (intervals.get(i).start < intervals.get(i-1).end) {
                return false;
            }
        }
        // No conflicts detected
        return true;

    }

    public class Interval {
      public int start, end;
      public Interval(int start, int end) {
          this.start = start;
          this.end = end;
      }
    }

}
