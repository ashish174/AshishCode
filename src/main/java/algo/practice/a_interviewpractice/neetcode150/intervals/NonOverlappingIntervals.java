package algo.practice.a_interviewpractice.neetcode150.intervals;

import java.util.Arrays;

/**
 *  Sort by endTime
 *
 * Given an array of intervals intervals where intervals[i] = [start_i, end_i], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 * Note: Intervals are non-overlapping even if they have a common point. For example, [1, 3] and [2, 4] are overlapping, but [1, 2] and [2, 3] are non-overlapping.
 *
 * Example 1:
 * Input: intervals = [[1,2],[2,4],[1,4]]
 * Output: 1
 * Explanation: After [1,4] is removed, the rest of the intervals are non-overlapping.
 *
 * Example 2:
 * Input: intervals = [[1,2],[2,4]]
 * Output: 0
 *
 * Approach:
 * 1. Sort intervals by their end time to maximize the number of non-overlapping intervals that can be kept.
 * 2. Using a greedy approach, iterate through each interval:
 *    - If the current interval starts after or at the end of the last picked interval, keep it.
 *    - Otherwise, it overlaps, so increment the removal counter.
 * 3. The result is the minimum number of removals required for non-overlapping intervals.
 * Time complexity: O(n log n) due to sorting.
 */
public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;

        // Sort intervals by the end time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int removeCount = 0;
        int lastEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            // If overlapping, increment removal count
            if (intervals[i][0] < lastEnd) {
                removeCount++;
            } else {
                // If not overlapping, update the end time
                lastEnd = intervals[i][1];
            }
        }
        return removeCount;
    }

    // Simple test cases
    public static void main(String[] args) {
        NonOverlappingIntervals sol = new NonOverlappingIntervals();
        int[][] ex1 = {{1,2},{2,4},{1,4}};
        int[][] ex2 = {{1,2},{2,4}};
        System.out.println("Output 1: " + sol.eraseOverlapIntervals(ex1)); // 1
        System.out.println("Output 2: " + sol.eraseOverlapIntervals(ex2)); // 0
    }
}
