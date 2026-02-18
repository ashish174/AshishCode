package algo.practice.a_interviewpractice.neetcode150.map;

/**
Approach: HashMap + Prefix sum


 Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 A subarray is a contiguous non-empty sequence of elements within an array.
 Example 1:
 Input: nums = [1,1,1], k = 2
 Output: 2

 Example 2:
 Input: nums = [1,2,3], k = 3
 Output: 2


 1 <= nums.length <= 2 * 104
 -1000 <= nums[i] <= 1000
 -107 <= k <= 107

 Use a HashMap to store the cumulative sum up to each index.
 For each element, calculate the running prefix sum.
 If (currentSum - k) is found in the map, it means there exists a subarray ending at the current index whose sum is k.
 Accumulate the count using this idea for the entire array.
 This yields an efficient O(n) solution.

**/

import java.util.HashMap;
import java.util.Map;

class SubArraySum {
    public int countSubArrayWithSumK(int[] nums, int k) {
        // Map to store (cumulative sum up to current index, number of occurrences)
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1); // Base case: There is one way to have sum = 0 (empty subarray)

        int currentSum = 0;
        int count = 0;

        for (int num : nums) {
            currentSum += num; // prefix sum up to current element

            // If (currentSum - k) exists, there is a subarray ending here which sums to k
            if (prefixSumCount.containsKey(currentSum - k)) {
                count += prefixSumCount.get(currentSum - k);
            }

            // Record the occurrence of currentSum
            prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }
}

