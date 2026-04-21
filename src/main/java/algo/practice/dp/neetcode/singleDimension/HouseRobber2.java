package algo.practice.dp.neetcode.singleDimension;

/**
 * You are given an integer array nums where nums[i] represents the amount of money the ith house has.
 * The houses are arranged in a circle, i.e. the first house and the last house are neighbors.
 *
 * You are planning to rob money from the houses, but you cannot rob two adjacent houses because the
 * security system will automatically alert the police if two adjacent houses were both broken into.
 *
 * Return the maximum amount of money you can rob without alerting the police.
 *
 * Example 1:
 *
 * Input: nums = [3,4,3]
 *
 * Output: 4
 * Explanation: You cannot rob nums[0] + nums[2] = 6 because nums[0] and nums[2] are adjacent houses.
 * The maximum you can rob is nums[1] = 4.
 *
 * Example 2:
 *
 * Input: nums = [2,9,8,3,6]
 *
 * Output: 15
 * Explanation: You cannot rob nums[0] + nums[2] + nums[4] = 16 because nums[0] and nums[4] are adjacent houses.
 * The maximum you can rob is nums[1] + nums[4] = 15.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 200
 *
 *  * Idea:
 *  *  - Break the circular problem into two linear problems:
 *  *      Case 1: Rob from index 0 to n-2 (exclude last house).
 *  *      Case 2: Rob from index 1 to n-1 (exclude first house).
 *  *  - For each linear range, this becomes the standard House Robber I.
 *
 * Case 1: Rob from index 0 to n‑2 (exclude last house).
 * Case 2: Rob from index 1 to n‑1 (exclude first house).
 *
 * For each linear case, the standard recurrence is exactly the same as in HouseRobber 1
 * dp[i] = max loot from i houses i.e. 0,1..i-1
 * dp[i] = Max{
 *              dp[i-1], // don't rob house ith house
 *              dp[i-2]+nums[i-1] //rob ith house
 *              }
 * For circular case, the solution will come from
 * Ans = max {
 *     maxLootLinear(nums, 0, n-2),  // exclude last
 *     maxLootLinear(nums, 1, n-1)   // exclude first
 * }
 *
 */
public class HouseRobber2 {
    /**
     * Returns the maximum amount of money you can rob from circular houses.
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        // Case 1: Rob houses from index 0 to n-2 (exclude last house)
        int lootExcludingLast = maxLootLinear(nums, 0, n - 2);

        // Case 2: Rob houses from index 1 to n-1 (exclude first house)
        int lootExcludingFirst = maxLootLinear(nums, 1, n - 1);

        // Best of the two scenarios
        return Math.max(lootExcludingLast, lootExcludingFirst);
    }

    /**
     * Solves the standard House Robber I problem on a linear subarray nums[start..end].
     *
     * This uses an O(1)-space DP:
     *  - prev2 = dp[i-2]
     *  - prev1 = dp[i-1]
     *  - curr  = dp[i]
     */
    private int maxLootLinear(int[] nums, int start, int end) {
        // If the range is invalid, no houses to rob
        if (start > end) {
            return 0;
        }

        // If there's only one house in this range
        if (start == end) {
            return nums[start];
        }

        int len = end - start + 1;
        int[] tabulation = new int[len];

        //just first house
        tabulation[0] = nums[start];
        //just first & second house
        tabulation[1] = Math.max(nums[start], nums[start + 1]);

        //Calculate in bottoms up
        for (int i = 2; i < len; i++) {
            int idx = start + i;
            tabulation[i] = Math.max(
                    tabulation[i - 1],                 // don't rob this house
                    tabulation[i - 2] + nums[idx] // rob this house
            );
        }

        return tabulation[len - 1];
    }

    // Optional: simple manual test
    public static void main(String[] args) {
        HouseRobber2 solver = new HouseRobber2();

        int[] nums1 = {3, 4, 3};
        System.out.println("Max loot (3,4,3): " + solver.rob(nums1)); // 4

        int[] nums2 = {2, 9, 8, 3, 6};
        System.out.println("Max loot (2,9,8,3,6): " + solver.rob(nums2)); // 15
    }
}
