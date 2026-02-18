package algo.practice.dp;

import lombok.extern.slf4j.Slf4j;

/**
 * Given an array of integers nums, find the subarray with the largest sum and return the sum.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Example 1:
 *
 * Input: nums = [2,-3,4,-2,2,1,-1,4]
 *
 * Output: 8
 * Explanation: The subarray [4,-2,2,1,-1,4] has the largest sum 8.
 *
 * Example 2:
 *
 * Input: nums = [-1]
 *
 * Output: -1
 *
 *
 * Solution:
 * CurrSum[i] = Max {CurrSum[i-1]+nums[i], nums[i]}
 *
 * Kadane algo:-
 * CurrSum[i] be the maximum subarray sum ending at index i (i.e., the best sum for all subarrays that must end at nums[i])
 * You have two choices at each index:
 * Start a new subarray at i (so take only nums[i])
 * Extend the previous best subarray ending at i-1 by adding nums[i] (so CurrSum[i-1] + nums[i])
 * So,
 * CurrSum[i] = Max{CurrSum[i-1] + nums[i] , nums[i]}
 * i.e.
 * Recurrence Relation : max_ending_at_i = max(nums[i], nums[i] + max_ending_at_i_minus_1)
 *
 * Note: This is not a dp problem
 *
 */
@Slf4j
public class MaxSumSubArray {

    //Kadane algo
    public int maxSumSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;
        for(int num : nums) {
            currSum = Math.max(currSum+num, num);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }
    public static void main(String[] args){
        MaxSumSubArray maxSumSubArray = new MaxSumSubArray();
        int[] nums = {2,-3,4,-2,2,1,-1,4};
        log.info("MaxSum : {}", maxSumSubArray.maxSumSubArray(nums));

    }
}
