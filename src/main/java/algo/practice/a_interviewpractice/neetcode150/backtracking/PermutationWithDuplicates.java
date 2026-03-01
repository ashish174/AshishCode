package algo.practice.a_interviewpractice.neetcode150.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PermutationWithDuplicates
 *
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 *
 * Example 1:
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * Example 2:
 * Input: nums = [1,2,3]
 * Output:
 * [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Approach:
 * - Backtracking is used to explore all possible orderings.
 * - The array is sorted so that duplicates are adjacent and can be detected.
 * - A boolean array 'used' tracks which elements are included in the current recursion path.
 * - To prevent duplicate permutations, we skip choosing a duplicate number if the previous identical number has not been used in the current recursion branch.
 * - This ensures only unique permutations are generated.
 *
 * Constraints:
 *   1 <= nums.length <= 8
 *   -10 <= nums[i] <= 10
 */
public class PermutationWithDuplicates {

    /**
     * Returns all unique permutations of the given array that may contain duplicates.
     * nums - The array of integers (may contain duplicates).
     * Returns a list of unique permutations.
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums); // Sort the array to group duplicates together.
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    /**
     * Helper method that performs the backtracking.
     * list      The result list to store unique permutations.
     * tempList  The current permutation path being built.
     * nums      Input array of integers (sorted).
     * used      Boolean array indicating which elements are already used in the current path.
     */
    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used){
        // If the current permutation is complete, add a copy to the result.
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else {
            // Try every unused element at this recursive depth
            for(int i = 0; i < nums.length; i++){
                // Skip if element is already used in this path
                // Also, skip if current number is a duplicate and the previous duplicate was not used (avoid redundant branches).
                if(
                        used[i] ||
                                (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])
                ) continue;

                // Mark as used, add to path, recurse
                used[i] = true;
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, used);

                // Backtrack: remove the element and mark as unused for next iterations
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
