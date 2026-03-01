package algo.practice.a_interviewpractice.neetcode150.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an array nums of integers, which may contain duplicates. Return all possible subsets.
 * The solution must not contain duplicate subsets. You may return the solution in any order.
 * Example 1:
 * Input: nums = [1,2,1]
 * Output: [[],[1],[1,2],[1,1],[1,2,1],[2]]
 *
 * Example 2:
 * Input: nums = [7,7]
 * Output: [[],[7], [7,7]]
 *
 * Approach:
 *   - Uses backtracking to explore all combinations.
 *   - The input array is sorted to group duplicates together.
 *   - At each recursive step, we skip choosing a number if it's the same as the previous
 *     *and* if it is the first time at this recursive level (to avoid duplicate subsets).
 *   - At each position, we either include the current element and recurse, or skip to the next distinct element.
 */
public class SubsetsWithDuplicates {
    /**
     * Returns all possible unique subsets of the given integer array nums.
     *
     * @param nums The input array which may contain duplicates.
     * @return A list of all unique subsets.
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums); // Sort the array to handle duplicates easily
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    /**
     * Backtracking helper function to build subsets.
     *
     * @param list      The master list to hold all unique subsets.
     * @param tempList  The current working subset.
     * @param nums      The sorted input array.
     * @param start     The current index in nums to consider.
     */
    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        // Add the current subset to the results (deep copy to prevent mutation).
        list.add(new ArrayList<>(tempList));

        // Iterate through each candidate starting from 'start'
        for (int i = start; i < nums.length; i++) {
            // If the current number is the same as the previous AND we're at the same tree depth,
            // skip it to prevent duplicate subsets.
            if (i > start && nums[i] == nums[i - 1]) continue;

            // Choice 1: Include nums[i] in the current subset
            // and proceed exploring all possible options in that subtree
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1); // Recurse to build bigger subsets

            // Choice 2: Exclude nums[i] from the subset (backtrack)
            // Remove the last element and move to the next candidate
            tempList.remove(tempList.size() - 1);
        }
    }
}
