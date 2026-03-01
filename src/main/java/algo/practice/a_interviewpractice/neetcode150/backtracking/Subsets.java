package algo.practice.a_interviewpractice.neetcode150.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of unique integers, return all possible subsets of nums.
 * The solution set must not contain duplicate subsets. You may return the solution in any order.
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * Example 2:
 * Input: nums = [7]
 * Output: [[],[7]]
 *
 *
 *
 *
 * Solves the problem of finding all possible subsets (the power set) of a set of unique integers.
 * Example:
 *   Input:  nums = [1,2,3]
 *   Output: [[], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3]]
 *
 * Approach:
 *   Uses backtracking to explore each possibility—at every integer, you can either include it in the subset or skip it.
 *   The algorithm recursively generates all subsets by:
 *     1. Adding the current subset to the results.
 *     2. Iterating from the current start index, adding each integer, backtracking, and then removing the integer (undoing the choice).
 *
 * This ensures all combinations are explored without duplicates.
 *
 *
 *
 */
public class Subsets {
    /**
     * Returns all possible subsets of the given unique integer array.
     *
     * @param nums An array of unique integers.
     * @return A list of all possible subsets (the power set) of nums.
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums); // Sorting is optional here (helps if input can have duplicates, but not needed otherwise)
        backtrack(list, new ArrayList<>(), nums, 0); // Start backtracking from index 0
        return list;
    }

    /**
     * Helper method to generate all subsets using backtracking.
     *
     * @param list The main result list holding all subsets.
     * @param tempList The current subset being constructed.
     * @param nums The original input array.
     * @param start The current starting index (all indices before this are fixed and not reconsidered).
     */
    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList)); // Add the current subset to the results (deep copy to avoid mutation)
        for (int i = start; i < nums.length; i++) {
            //include i, and explore all elements
            tempList.add(nums[i]); // Choose nums[i] for the current subset
            backtrack(list, tempList, nums, i + 1); // Explore further with nums[i] included (move to next index)
            //once we completely explored including i.
            //Remove i and now we have to explore skipping i
            tempList.remove(tempList.size() - 1); // Backtrack: remove the last element and try the next candidate
        }
    }


    private void backtrackV2(List<List<Integer>> list, List<Integer> tempList, int[] nums, int index) {
        //recursive base case
        if (index == nums.length) {
            list.add(new ArrayList<>(tempList));
            return;
        }
        //include i, and explore all elements
        tempList.add(nums[index]); // Choose nums[i] for the current subset
        backtrack(list, tempList, nums, index + 1); // Explore further with nums[i] included (move to next index)
        //once we completely explored including i.
        //Remove i and now we have to explore skipping i
        tempList.remove(tempList.size() - 1); // Backtrack: remove the last element and try the next candidate
        backtrack(list, tempList, nums, index + 1); // Explore further without nums[i] included (move to next index)
    }

    public static void main(String[] args) {
        // Sample input
        int[] nums = {1, 2, 3};

        // Create an instance of Subsets and call the subsets method
        Subsets solution = new Subsets();
        List<List<Integer>> result = solution.subsets(nums);

        // Print the result
        System.out.println("All possible subsets:");
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }
}
