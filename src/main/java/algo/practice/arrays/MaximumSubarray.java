package algo.practice.arrays;

/**
 * Maximum Subarray Sum
 * Question: Given an integer array nums, find the contiguous subarray
 * (containing at least one number) which has the largest sum and return its sum.
 *
 * Input: nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 * Output: 6
 * Explanation: [4, -1, 2, 1] has the largest sum = 6.
 *
 *
 * Approach:
 * This solution uses Kadane's Algorithm to find the maximum sum of a contiguous subarray.
 * It iterates through the array, at each step deciding whether to extend the current subarray
 * or start a new one, and keeps track of the maximum sum found.
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }

    public static void main(String[] args) {
        MaximumSubarray solution = new MaximumSubarray();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSum = solution.maxSubArray(nums);
        System.out.println("Maximum subarray sum: " + maxSum);
    }
}