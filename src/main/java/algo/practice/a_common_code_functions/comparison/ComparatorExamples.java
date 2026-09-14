package algo.practice.a_common_code_functions.comparison;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorExamples {

    public void compare(){
        //Given an array of intervals where intervals[i] = [start_i, end_i]
        int[][] intervals = {{2,3}, {3,5}, {2,5} };
        //sort by end & then start
        Arrays.sort(intervals, Comparator.comparingInt((int[] a) -> a[1]).thenComparingInt(a -> a[0]));

        //sort by start
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

    }

}
