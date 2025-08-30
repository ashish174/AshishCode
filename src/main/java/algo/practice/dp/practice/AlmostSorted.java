package algo.practice.dp.practice;

/**
 * An array of integers is almost sorted if at most one element can be deleted from it to make it perfectly sorted, ascending.
 * For example, arrays [2, 1, 7], [13], [9, 2]  and [1, 5, 6] are almost sorted because they have 0 or 1 elements out of place.  The arrays [4, 2, 1], [1, 2, 6, 4, 3] are not because they have more than one element out of place.
 * Given an array of n unique integers, determine the minimum number of elements to remove so it becomes almost sorted.
 *
 * Example : arr = [3, 4, 2, 5, 1]
 * Remove 2  to get arr' = [3, 4, 5, 1] or remove 1 to get arr' = [3, 4, 2, 5], both of which are almost sorted.  The minimum number of elements that must be removed in this case is 1.
 *
 * dp(i) = dp(i-1)
 *
 */
public class AlmostSorted {

    public static int minDeletions(int[] arr) {
        return 0;
    }
}
