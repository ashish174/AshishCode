package algo.practice.dp.neetcode.singleDimension;

import lombok.extern.slf4j.Slf4j;

/**
 * You are given an integer array nums where nums[i] represents the amount of money the ith house has.
 * The houses are arranged in a straight line, i.e. the ith house is the neighbor of the (i-1)th and (i+1)th house.
 *
 * You are planning to rob money from the houses, but you cannot rob two adjacent houses because the
 * security system will automatically alert the police if two adjacent houses were both broken into.
 *
 * Return the maximum amount of money you can rob without alerting the police.
 *
 * Example 1:
 *
 * Input: nums = [1,1,3,3]
 *
 * Output: 4
 * Explanation: nums[0] + nums[2] = 1 + 3 = 4.
 *
 * Example 2:
 *
 * Input: nums = [2,9,8,3,6]
 *
 * Output: 16
 * Explanation: nums[0] + nums[2] + nums[4] = 2 + 8 + 6 = 16.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 *
 *
 *
 * dp[n] = max loot you can get from first n houses [0,1,..(n-1)]
 * dp[n] = Max{
 *              dp[n-1] // dont rob nth house
 *              , nums[n] + dp[n-2] // rob nth house
*              }
*
 *
 *
 */
@Slf4j
public class HouseRobber {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n+1];
        return findMaxLoot(nums, dp, n);
    }

    private int findMaxLoot(int[] nums, int[] dp, int n) {
        if(n==1){
            return nums[0];
        }
        if(n==2){
            return Math.max(nums[0], nums[1]);
        }
        //if not initialized
        if(dp[n]==0){
            dp[n] = Math.max(
                    findMaxLoot(nums, dp, n-1),     // don't rob house n-1
                    nums[n-1]+findMaxLoot(nums, dp, n-2));  // rob house n-1
        }
        return dp[n];
    }

    public static void main(String[] args){
        int[] nums = new int[] {2,9,8,3,6};
        log.info("Max Loot possible: {}", new HouseRobber().rob(nums));
    }
}
