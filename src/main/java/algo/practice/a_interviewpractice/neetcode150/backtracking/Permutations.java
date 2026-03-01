package algo.practice.a_interviewpractice.neetcode150.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array nums of unique integers, return all the possible permutations. You may return the answer in any order.
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Example 2:
 * Input: nums = [7]
 * Output: [[7]]
 *
 * Approach:
 *   - Uses backtracking to generate all possible orderings of the array.
 *   - At each step, iterate over all numbers and, if the current number is not already in the temporary permutation (tempList),
 *     add it and recursively build the next position.
 *   - Once the current permutation reaches full length, add it to the result list.
 */
public class Permutations {
    /**
     * Returns all possible permutations for the given array of unique integers.
     *
     * @param nums Input array of unique integers.
     * @return List of all unique permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    /**
     * Helper backtracking method for generating permutations.
     *
     * @param list     The list holding all permutations found so far.
     * @param tempList The current permutation being constructed.
     * @param nums     The original input array.
     */
    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        // Base condition: if the temporary permutation is full length, add its copy to the results.
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList)); // Add a deep copy to avoid mutation.
        } else {
            // Try every unused number as the next candidate in the current permutation.
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) // If this number is already used in this permutation, skip it.
                    continue;

                // Choose: add nums[i] to the current permutation.
                tempList.add(nums[i]);

                // Explore: build the rest of the permutation recursively.
                backtrack(list, tempList, nums);

                // Un-choose (backtrack): remove the last added number and try another.
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
