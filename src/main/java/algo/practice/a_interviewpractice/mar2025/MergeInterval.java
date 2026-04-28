package algo.practice.a_interviewpractice.mar2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * Example 3:
 *
 * Input: intervals = [[4,7],[1,4]]
 * Output: [[1,7]]
 * Explanation: Intervals [1,4] and [4,7] are considered overlapping.
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
public class MergeInterval {
    public int[][] merge(int[][] intervals) {
        if(intervals==null || intervals.length==0){
            //return new int[0][2];
            throw new IllegalArgumentException("Invalid intervals");
        }
        List<int[]> mergedIntervals = new ArrayList<>();
        //sort by start time
        Arrays.sort(intervals, Comparator.comparing((int[] interval) -> interval[0]));
        mergedIntervals.add(intervals[0]);
        for(int j=1 ; j<intervals.length; j++){
            //if prev endTime >= current start time, then merge prev & current
            int[] prev = mergedIntervals.get(mergedIntervals.size()-1);
            if(prev[1] < intervals[j][0]){
                mergedIntervals.add(new int[]{intervals[j][0], intervals[j][1]});
            } else{
                //update finish time
                prev[1] = Math.max(prev[1], intervals[j][1]);
            }
        }
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }

    public static void main(String[] args){


    }


}
