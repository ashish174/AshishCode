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
 */
@Slf4j
public class MaxSumSubArray {

    public int maxSubArray(int[] nums) {
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
        log.info("MaxSum : {}", maxSumSubArray.maxSubArray(nums));

    }
}
