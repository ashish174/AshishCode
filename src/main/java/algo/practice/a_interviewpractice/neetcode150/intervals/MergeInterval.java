package algo.practice.a_interviewpractice.neetcode150.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [start_i, end_i],
 * merge all overlapping intervals, and return an array of the non-overlapping intervals
 * that cover all the intervals in the input.
 *
 * You may return the answer in any order.
 *
 * Note: Intervals are non-overlapping if they have no common point.
 * For example, [1, 2] and [3, 4] are non-overlapping, but [1, 2] and [2, 3] are overlapping.
 *
 * Example 1:
 * Input: intervals = [[1,3],[1,5],[6,7]]
 * Output: [[1,5],[6,7]]
 *
 * Example 2:
 * Input: intervals = [[1,2],[2,3]]
 * Output: [[1,3]]
 *
 * Approach: Since these intervals are not sorted, sorting by start value will help.
 * First, sort the intervals by their start time.
 * Then iterate through the intervals, merging overlapping ones by updating the end time whenever an overlap is found.
 * Add a new interval to the result list only when the current interval does not overlap with the previous.
 * This ensures the returned intervals are non-overlapping and cover all input intervals.
 */
public class MergeInterval {
    public int[][] merge(int[][] intervals) {
        // Handle edge case: empty or single interval
        if (intervals.length <= 1) return intervals;

        // Sort intervals based on the start value
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> merged = new ArrayList<>();

        // Start with first interval
        int[] current = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            // If intervals overlap, merge them (update end)
            if (intervals[i][0] <= current[1]) {
                current[1] = Math.max(current[1], intervals[i][1]);
            } else {
                // No overlap, add the current and start a new one
                merged.add(current);
                current = intervals[i];
            }
        }
        // Add the last interval
        merged.add(current);

        // Convert result to array for output
        return merged.toArray(new int[merged.size()][]);
    }

    // Simple test
    public static void main(String[] args) {
        MergeInterval mi = new MergeInterval();
        int[][] intervals1 = {{1,3},{1,5},{6,7}};
        int[][] result1 = mi.merge(intervals1);
        System.out.print("Result 1: ");
        for (int[] iv : result1) System.out.print("[" + iv[0] + "," + iv[1] + "] ");
        System.out.println();

        int[][] intervals2 = {{1,2},{2,3}};
        int[][] result2 = mi.merge(intervals2);
        System.out.print("Result 2: ");
        for (int[] iv : result2) System.out.print("[" + iv[0] + "," + iv[1] + "] ");
        System.out.println();
    }

}
