package algo.practice.a_interviewpractice.neetcode150.intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an array of non-overlapping intervals intervals where
 * intervals[i] = [start_i, end_i] represents the start and the end time of the ith interval.
 * intervals is initially sorted in ascending order by start_i.
 *
 * You are given another interval newInterval = [start, end].
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by start_i
 * and also intervals still does not have any overlapping intervals.
 * You may merge the overlapping intervals if needed.
 *
 * Return intervals after adding newInterval.
 *
 * Example 1:
 * Input: intervals = [[1,3],[4,6]], newInterval = [2,5]
 * Output: [[1,6]]
 *
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[9,10]], newInterval = [6,7]
 * Output: [[1,2],[3,5],[6,7],[9,10]]
 *
 * Approach:
 * Traverse the list of intervals and add all intervals ending before the new interval starts.
 * Merge all overlapping intervals with the new interval as necessary.
 * Add the new merged interval.
 * Finally, add all remaining intervals.
 * This maintains sorted order and ensures no overlaps, in O(n) time.
 */
public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0, n = intervals.length;

        // 1. Add all intervals ending before newInterval starts (no overlap)
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // 2. Merge overlapping intervals with newInterval
        // Continue merging as long as intervals start before or at newInterval ends
        while (i < n && intervals[i][0] <= newInterval[1]) {
            // Merge by taking min start and max end
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        // Add the merged interval
        result.add(newInterval);

        // 3. Add the rest of the intervals
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        // Convert list to array
        return result.toArray(new int[result.size()][]);
    }

    // Optional: main method for simple test
    public static void main(String[] args) {
        InsertInterval solution = new InsertInterval();
        int[][] intervals1 = {{1,3},{4,6}};
        int[] newInterval1 = {2,5};
        int[][] result1 = solution.insert(intervals1, newInterval1);

        int[][] intervals2 = {{1,2},{3,5},{9,10}};
        int[] newInterval2 = {6,7};
        int[][] result2 = solution.insert(intervals2, newInterval2);

        // Print
        System.out.print("Result 1: ");
        for (int[] iv : result1)
            System.out.print("[" + iv[0] + "," + iv[1] + "] ");
        System.out.println();

        System.out.print("Result 2: ");
        for (int[] iv : result2)
            System.out.print("[" + iv[0] + "," + iv[1] + "] ");
        System.out.println();
    }
}
